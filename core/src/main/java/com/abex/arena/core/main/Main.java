package com.abex.arena.core.main;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.abex.arena.core.main.task.DeleteTask;
import com.abex.arena.core.main.task.SaveTask;
import com.abex.arena.core.main.task.Task;
import com.abex.arena.core.service.AccountService;
import com.abex.arena.core.service.PersistentObjectFactoryService;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private final ApplicationContext context;

	public static void main(String[] args) throws ParseException {
		Main main = new Main();
		main.run(args);
	}

	public Main() throws ParseException {
		this.context = new ClassPathXmlApplicationContext(new String[] { "spring.xml" });
	}

	private void run(String[] args) throws ParseException {
		Task task = this.parse(args);
		if (task != null) {
			Main.logger.info(task.toString());
			task.run((PersistentObjectFactoryService) context.getBean(PersistentObjectFactoryService.class),
					(AccountService) context.getBean(AccountService.class));
		}
	}

	private Task parse(String[] args) throws ParseException {
		Task task = null;
		Options options = new Options();
		options.addOption("h", "help", false, "shows this message.");
		options.addOption("s", "save", true, "save object.");
		options.addOption("d", "delete", true, "Delete user");
		options.addOption("v", "validate", true, "Validate user");

		CommandLineParser parser = new DefaultParser();
		CommandLine cmdLine = parser.parse(options, args);

		if (cmdLine.hasOption("help") == true) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("Initialize", options);
		} else {
			if (cmdLine.hasOption("s") == true) {
				String result = cmdLine.getArgList().stream().reduce("", String::concat);
				task = new SaveTask(cmdLine.getOptionValue("s"), Arrays.asList(result));
			} else if (cmdLine.hasOption("d") == true) {
				String result = cmdLine.getArgList().stream().reduce("", String::concat);
				task = new DeleteTask(cmdLine.getOptionValue("d"), Arrays.asList(result));
			} else if (cmdLine.hasOption("v") == true) {
				Main.logger.info("Validate user: " + cmdLine.getOptionValue("d"));
			}
		}
		return task;
	}

	public ApplicationContext getContext() {
		return context;
	}

}
