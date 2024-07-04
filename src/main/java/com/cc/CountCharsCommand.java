package com.cc;

import org.apache.commons.cli.CommandLine;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CountCharsCommand implements CountCommand {

    private final CommandLine cmd;

    CountCharsCommand(CommandLine cmd) {
        this.cmd = cmd;
    }

    @Override
    public long count() {
        long chars = 0;
        try (InputStream is = CountCommandHelper.getInputStream(cmd);
             InputStreamReader isr = new InputStreamReader(is)) {
            while (isr.read() != -1) {
                chars++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return chars;
    }

    @Override
    public void execute() {
        System.out.println(count() + " " + CountCommandHelper.getFilename());
    }
}
