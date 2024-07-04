package com.cc;

import org.apache.commons.cli.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CountWordsCommand implements CountCommand {

    private final CommandLine cmd;

    CountWordsCommand(CommandLine cmd) {
        this.cmd = cmd;
    }

    @Override
    public long count() {
        int words = 0;
        try (InputStream is = CountCommandHelper.getInputStream(cmd);
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                words += Arrays.stream(line.trim().split("\\s+")).filter(w -> !w.isEmpty()).mapToInt(w -> 1).sum();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return words;
    }

    @Override
    public void execute() {
        System.out.println(count() + " " + CountCommandHelper.getFilename());
    }
}
