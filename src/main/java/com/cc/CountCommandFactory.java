package com.cc;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

public class CountCommandFactory {

    public static Options createOptions() {
        Options options = new Options();
        options.addOption("c", false, "number of bytes in file");
        options.addOption("l", false, "number of lines in file");
        options.addOption("w", false, "number of words in file");
        options.addOption("m", false, "number of chars in file");
        return options;
    }

    public static CountCommand createCommand(CommandLine cmd) {
        if (cmd.hasOption("c")) {
            return new CountBytesCommand(cmd);
        } else if (cmd.hasOption("l")) {
            return new CountLinesCommand(cmd);
        } else if (cmd.hasOption("w")) {
            return new CountWordsCommand(cmd);
        } else if (cmd.hasOption("m")) {
            return new CountCharsCommand(cmd);
        } else {
            return new DefaultCommand(cmd);
        }
    }
}
