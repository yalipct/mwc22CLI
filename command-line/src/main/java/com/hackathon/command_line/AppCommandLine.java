package com.hackathon.command_line;

import java.util.concurrent.Callable;
import org.fusesource.jansi.AnsiConsole;

import com.hackathon.command_line.subcommands.AddDeveloperCommand;
import com.hackathon.command_line.subcommands.InformationMwcCommand;
import com.hackathon.command_line.subcommands.ListDeveloperCommand;
import com.hackathon.command_line.subcommands.ReadJSONMwcCommand;
import com.hackathon.command_line.util.PrintExceptionMessageHandler;

import picocli.CommandLine;

@CommandLine.Command(name = "mwc", version = "1.0.0", mixinStandardHelpOptions = true, requiredOptionMarker = '*', description = "This is a mwc Tool which will help us to manage mwc activities.", header = "Mwc CLI", optionListHeading = "%nOptions are:%n", footerHeading = "%nCopyright", footer = "%nDeveloped by Aliuvys Ojeda", subcommandsRepeatable = true, commandListHeading = "%nSubCommands are: %n", subcommands = {
		ReadJSONMwcCommand.class, AddDeveloperCommand.class, ListDeveloperCommand.class, InformationMwcCommand.class })
public class AppCommandLine implements Callable<Integer> {

	final Integer SUCCESS = 0;
	final Integer FAILURE = 1;	
	
	
	public static void main(String[] args) {
		AnsiConsole.systemInstall();
		int exitStatus = new CommandLine(new AppCommandLine()).setCaseInsensitiveEnumValuesAllowed(true).setExecutionExceptionHandler(new PrintExceptionMessageHandler()).execute(args);
		System.exit(exitStatus);
	}

	
	@Override
	public Integer call() throws Exception {
		System.out.println("[mwc] Welcome to Mobile Worl Congress");
		return SUCCESS;
	}

}
