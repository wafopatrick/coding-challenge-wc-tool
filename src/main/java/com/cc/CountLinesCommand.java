package com.cc;

import org.apache.commons.cli.CommandLine;

import java.io.*;

public class CountLinesCommand implements CountCommand {

    private final CommandLine cmd;

    CountLinesCommand(CommandLine cmd) {
        this.cmd = cmd;
    }

    @Override
    public long count() {
        long lines = 0;
        try (InputStream is = CountCommandHelper.getInputStream(cmd);
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isr)) {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

    @Override
    public void execute() {
        System.out.println(count() + " " + CountCommandHelper.getFilename());
    }
}
