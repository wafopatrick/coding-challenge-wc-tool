package com.cc;

import org.apache.commons.cli.CommandLine;

import java.io.IOException;
import java.io.InputStream;

public class CountBytesCommand implements CountCommand {

    private final CommandLine cmd;

    CountBytesCommand(CommandLine cmd) {
        this.cmd = cmd;
    }

    @Override
    public long count() {
        long totalBytes = 0;
        try (InputStream is = CountCommandHelper.getInputStream(cmd)) {
            byte[] buffer = new byte[8192]; // 8 KB
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                totalBytes += bytesRead;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return totalBytes;
    }

    @Override
    public void execute() {
        System.out.println(count() + " " + CountCommandHelper.getFilename());
    }
}
