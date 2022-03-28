package com.hackathon.command_line.subcommands;

import java.util.concurrent.Callable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.hackathon.command_line.model.Category;
import com.hackathon.command_line.model.Developer;
import com.hackathon.command_line.model.MwcDays;
import com.hackathon.command_line.util.ColorANSI;
import com.hackathon.command_line.util.InputsValidation;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Unmatched;

@CommandLine.Command(name = "add", aliases = { "create",
		"plus" }, version = "1.0.0", mixinStandardHelpOptions = true, requiredOptionMarker = '*', description = "This is a Sub Command to 'mwc' and add a new developer to the list", header = "Add new developer", optionListHeading = "%nOptions are:%n", footerHeading = "%nCopyright", footer = "%nDeveloped by Aliuvys Ojeda")
public class AddDeveloperCommand implements Callable<Integer> {

	@Option(names = { "-n", "--name" }, description = "Provide name", required = true, arity = "0..1", interactive = true)
	String n;

	@Option(names = { "-e", "--email" }, description = "Provide an email", required = true, arity = "0..1", interactive = true)
	String mail;

	@Option(names = { "-c", "--ctg" }, description = "Provide a category", required = true, arity = "0..1", interactive = true)
	String ctg;

	@Option(names = { "-tel", "--phone" }, description = "Provide a telephone number [example: +34672123321]", required = true, arity = "0..1", interactive = true)
	String tel;

	@Option(names = { "-d", "--date" }, description = "Provide a date [example: \"Mar 1, 2021\"]", required = true, arity = "0..1", interactive = true)
	String day;

	@Override
	public Integer call() throws Exception {
		doLogic();
		return 0;

	}

	public void doLogic() {

		String name = InputsValidation.readName(n);
		String email = InputsValidation.readEmail(mail);
		String phone = InputsValidation.readPhone(tel);
		Category category = InputsValidation.readCategory(ctg);
		MwcDays date = null;
		try {
			date = InputsValidation.readDate(day);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Developer developer = new Developer(name, email, category, phone, date);
		addDeveloperToDatabase(developer);
	}

	public Developer addDeveloperToDatabase(Developer developer) {

		Developer newDeveloper = null;

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Developer.class)
				.buildSessionFactory();
		Session session = factory.openSession();
		
		try {

			Transaction t = session.beginTransaction();

			session.save(developer);

			if (!t.getStatus().equals(TransactionStatus.ACTIVE)) {
				t.commit();
			}

			System.out.println(ColorANSI.ANSI_GREEN + "Record inserted successfully" + ColorANSI.ANSI_RESET);
			System.out.println(ColorANSI.ANSI_GREEN + "Register with id: " + developer.getId() + ColorANSI.ANSI_RESET);

			newDeveloper = session.get(Developer.class, developer.getId());
			System.out.println(ColorANSI.ANSI_GREEN + "Inserted " + newDeveloper + ColorANSI.ANSI_RESET);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

		return newDeveloper;

	}

}
