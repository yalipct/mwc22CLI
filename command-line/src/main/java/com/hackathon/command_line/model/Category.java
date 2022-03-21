package com.hackathon.command_line.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Category {

	FRONT("Front"), BACK("Back"), MOBILE("Mobile"), DATA("Data");

	private Category(String category) {
		this.category = category;
	}

	private String category;

	@JsonValue
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public static Category from(String value) {
		return Stream.of(Category.values()).filter(targetEnum -> targetEnum.getCategory().equalsIgnoreCase(value))
				.findFirst().orElse(null);

	}

}
