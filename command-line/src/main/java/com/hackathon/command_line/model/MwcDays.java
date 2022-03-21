package com.hackathon.command_line.model;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MwcDays {

	DAY_ONE("Feb 28, 2021"), DAY_TWO("Mar 1, 2021"), DAY_THREE("Mar 2, 2021"), DAY_FOUR("Mar 3, 2021");
	
	private String date;

	private MwcDays(String date) {
		this.date = date;
	}

	@JsonValue
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public static MwcDays from(String value) {
		return Stream.of(MwcDays.values()).filter(targetEnum -> targetEnum.getDate().equalsIgnoreCase(value))
				.findFirst().orElse(null);

	}
	
	
}
