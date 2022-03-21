package com.hackathon.command_line.subcommands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.hackathon.command_line.model.Developer;
import com.hackathon.command_line.util.ColorANSI;

import picocli.CommandLine;

@CommandLine.Command(name = "list",
aliases = {"ls", "show"},
version = "1.0.0",
mixinStandardHelpOptions = true,
requiredOptionMarker = '*',
description = "This is a Sub Command to 'mwc' and lists all developers",
header = "List all developers",
optionListHeading = "%nOptions are:%n",
footerHeading = "%nCopyright",
footer = "%nDeveloped by Aliuvys Ojeda")


public class ListDeveloperCommand implements Callable<Integer>{
		
	@Override
	public Integer call() throws Exception {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Developer.class)
				.buildSessionFactory();
		Session session = factory.openSession();
		
		List<Developer> developersList = new ArrayList<>();
		
		try {
			Transaction t = session.beginTransaction();
			
			developersList = session.createQuery("FROM Developer", Developer.class).list();
			
			if (!t.getStatus().equals(TransactionStatus.ACTIVE)) {
				t.commit();
			}
			session.close();
			
			//developersList.forEach(System.out::println);
			if(developersList.isEmpty()) {
				System.out.println(ColorANSI.ANSI_RED + "There are not records in the database." + ColorANSI.ANSI_RESET);				
			}else {
				System.out.println(ColorANSI.ANSI_GREEN + developersList + ColorANSI.ANSI_RESET);
			}
			
			
		}finally {			
			factory.close();
		}
				
		
		return 0;
	}

}
