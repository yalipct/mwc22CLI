package com.hackathon.command_line.subcommands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.command_line.AppCommandLine;
import com.hackathon.command_line.model.Developer;
import com.hackathon.command_line.util.ColorANSI;

import picocli.CommandLine;
import picocli.CommandLine.ParentCommand;

@CommandLine.Command(name = "read", version = "1.0.0", mixinStandardHelpOptions = true, requiredOptionMarker = '*', description = "This is a Sub Command to 'mwc' to read json file and add it to Database", header = "Read json file and add it to Database", optionListHeading = "%nOptions are:%n", footerHeading = "%nCopyright", footer = "%nDeveloped by Aliuvys Ojeda")

public class ReadJSONMwcCommand implements Runnable {

	@ParentCommand
	private AppCommandLine mainCmd;

	
	@Override
	public void run() {
		saveJsonToDatabase();
//		System.out.println("Successfully saved");
	}
	
	

	public void saveJsonToDatabase() {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Developer.class)
				.buildSessionFactory();
		Session session = factory.openSession();

		try {
			ObjectMapper mapper = new ObjectMapper();

			// get file data
			InputStream inputStream = new FileInputStream(new File("mwc22.json"));

			// indicate the json data structure
			TypeReference<List<Developer>> typeReference = new TypeReference<List<com.hackathon.command_line.model.Developer>>() {
			};

			// read and get list of person objects
			List<Developer> developers = mapper.readValue(inputStream, typeReference);

			Transaction t = session.beginTransaction();
			
			//check if the records exist in the database
			List<Developer> listFromDatabase = new ArrayList<>();
			listFromDatabase = session.createQuery("FROM Developer", Developer.class).list();
			
			if(listFromDatabase.size() >= 100) {
				System.out.println(ColorANSI.ANSI_RED + "The records already exist in the database" + ColorANSI.ANSI_RESET);
			}else {
				// save data
				for (Developer dev : developers) {
					session.save(dev);
				}

				if (!t.getStatus().equals(TransactionStatus.ACTIVE)) {
					t.commit();
				}		
				System.out.println(ColorANSI.ANSI_GREEN + "Records saved successfully" + ColorANSI.ANSI_RESET);
			}		

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
