package com.cc;

import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) {
        Options options = CountCommandFactory.createOptions();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            CountCommand command = CountCommandFactory.createCommand(cmd);
            command.execute();
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
            // automatically generate the help statement
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("ccwc", options);
        }
    }
}