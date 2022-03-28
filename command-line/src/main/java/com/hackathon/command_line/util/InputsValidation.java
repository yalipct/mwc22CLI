package com.hackathon.command_line.util;

import java.util.Scanner;

import com.hackathon.command_line.model.Category;
import com.hackathon.command_line.model.MwcDays;

public class InputsValidation {

	static Scanner in = new Scanner(System.in);

	public static String readName(String text) {

		String name = "";
		boolean right = text.matches("^[A-Za-z]*$");

		if (!text.equals("")) {
			name = text;
		} else {
			right = false;
		}

		while (!right) {
			System.out.println("Introduce a valid name:");
			name = in.nextLine();
			if (!name.matches("^[A-Za-z]*$") || name.equals("")) {
				right = false;
			} else {
				right = true;
			}
		}

		return name;
	}

	public static String readEmail(String text) {
		String email = "";
		boolean right = text.matches("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]{2,3}+))+");

		if (!text.isEmpty()) {
			email = text;
		} else {
			right = false;
		}

		while (!right) {
			System.out.println("Introduce a valid email:");
			email = in.nextLine();
			if (!email.matches("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]{2,3}+))+")) {
				right = false;
			} else {
				right = true;
			}
		}

		return email;
	}

	public static String readPhone(String text) {
		String phone = "";
		boolean valid = text.matches("(\\+34|0034|34)?[-]*(6|7)[-]*([0-9][-]*){8}");

		if (!text.isEmpty()) {				
			phone = text;
		} else {
			valid = false;
		}

		while (!valid) {
			System.out.println("Introduce a valid telephone number [+34672123321]:");
			phone = in.nextLine();
			if (!phone.matches("(\\+34|0034|34)?[-]*(6|7)[-]*([0-9][-]*){8}")) {
				valid = false;
			} else {
				valid = true;
			}
		}
		return phone;

	}

	public static Category readCategory(String text) {

		Category category = null;
		boolean valid = validEnumCategory(text);

		if (valid) {
			category = Category.from(text);
		}

		while (!valid) {
			System.out.println("Please enter a valid category:");
			String ctg = in.nextLine().toUpperCase();
			if (Category.from(ctg) != null) {
				category = Category.from(ctg);
				valid = true;
			}
		}

		return category;
	}

	public static boolean validEnumCategory(String text) {

		boolean right = false;

		switch (text.toUpperCase()) {
		case "FRONT":
			right = true;
			break;

		case "BACK":
			right = true;
			break;

		case "MOBILE":
			right = true;
			break;

		case "DATA":
			right = true;
			break;

		default:
			right = false;
		}
		return right;
	}

	public static MwcDays readDate(String text) {
		MwcDays date = null;
		boolean valid = validEnumMwcDays(text);

		if (valid) {
			date = MwcDays.from(text);
		}

		while (!valid) {
			System.out.println("Please enter a valid date [Mar 1, 2021]:");
			String day = in.nextLine();
			if (MwcDays.from(day) != null) {
				date = MwcDays.from(day);
				valid = true;
			}
		}

		return date;
	}

	public static boolean validEnumMwcDays(String text) {

		boolean right = false;

		switch (text) {
		case "Feb 28, 2021":
			right = true;
			break;

		case "Mar 1, 2021":
			right = true;
			break;

		case "Mar 2, 2021":
			right = true;
			break;

		case "Mar 3, 2021":
			right = true;
			break;

		default:
			right = false;
		}
		return right;
	}

	// EMAIL->
	// "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"

}
