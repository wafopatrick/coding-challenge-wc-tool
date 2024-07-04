package com.cc;

import org.apache.commons.cli.CommandLine;

public class DefaultCommand implements CountCommand {

    private final CommandLine cmd;

    DefaultCommand(CommandLine cmd) {
        this.cmd = cmd;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void execute() {
        CountCommand countLinesCommand = new CountLinesCommand(cmd);
        CountCommand countWordsCommand = new CountWordsCommand(cmd);
        CountCommand countBytesCommand = new CountBytesCommand(cmd);
        System.out.println( countLinesCommand.count() + " " + countWordsCommand.count() + " " + countBytesCommand.count() + " " + CountCommandHelper.getFilename());
    }
}
