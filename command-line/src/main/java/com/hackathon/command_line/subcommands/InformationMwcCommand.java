package com.hackathon.command_line.subcommands;

import java.util.concurrent.Callable;

import com.hackathon.command_line.model.MwcDays;
import com.hackathon.command_line.util.ColorANSI;

import picocli.CommandLine;

@CommandLine.Command(name = "info",
version = "1.0.0",
mixinStandardHelpOptions = true,
requiredOptionMarker = '*',
description = "This is a Sub Command to 'mwc' and shows the days that the MWC is available",
header = "Days that the MWC is available",
optionListHeading = "%nOptions are:%n",
footerHeading = "%nCopyright",
footer = "%nDeveloped by Aliuvys Ojeda")

public class InformationMwcCommand implements Callable<Integer>{
	
	@Override
	public Integer call() throws Exception {
				
		String days = "The MWC will be available on the days: \n"
						+ MwcDays.DAY_ONE.getDate() + ", \n"
						+ MwcDays.DAY_TWO.getDate() + ", \n"
						+ MwcDays.DAY_THREE.getDate() + ", \n"
						+ MwcDays.DAY_FOUR.getDate();
		
		System.out.println(ColorANSI.ANSI_GREEN + days + ColorANSI.ANSI_RESET);
		
		return 0;
	}

}
