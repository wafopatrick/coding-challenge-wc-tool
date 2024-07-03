package com.cc;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.Arrays;

public class Main {
    private static String filename = "";

    public static void main(String[] args) {
        // create Options object
        Options options = new Options();

        // add option "-c"
        options.addOption("c", true, "count bytes in file");
        // add option "-l"
        options.addOption("l", true, "count lines in file");
        // add option "-w"
        options.addOption("w", true, "count words in file");
        // add option "-m"
        options.addOption("m", true, "count characters in file");

        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine cmd = parser.parse(options, args);

            // get option values
            if (cmd.hasOption("c")) {
                System.out.println(calculateNumberOfBytes(cmd) + " " + filename);
            }

            if (cmd.hasOption("l")) {
                System.out.println(calculateNumberOfLines(cmd) + " " + filename);
            }

            if (cmd.hasOption("w")) {
                System.out.println(calculateNumberOfWords(cmd) + " " + filename);
            }

            if (cmd.hasOption("m")) {
                System.out.println(calculateNumberOfChars(cmd) + " " + filename);
            }

            if (cmd.getOptions().length == 0) {
                System.out.println(calculateNumberOfLines(cmd) + " " + calculateNumberOfWords(cmd) + " " + calculateNumberOfBytes(cmd) + " " + filename);
            }

        } catch (ParseException | IOException e) {
            System.out.println("Error: " + e.getMessage());
            // automatically generate the help statement
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("ccwc", options);
        }
    }

    private static long calculateNumberOfBytes(CommandLine cmd) throws IOException {
        InputStream is = getInputStream(cmd);
        long totalBytes = 0;
        byte[] buffer = new byte[8192]; // 8 KB
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            totalBytes += bytesRead;
        }
        return totalBytes;
    }

    private static int calculateNumberOfLines(CommandLine cmd) throws IOException {
        int lines = 0;
        try (InputStream is = getInputStream(cmd);
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isr)) {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lines;
    }

    private static int calculateNumberOfWords(CommandLine cmd) throws IOException {
        int words = 0;
        try (InputStream is = getInputStream(cmd);
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                words += Arrays.stream(line.trim().split("\\s+")).filter(w -> !w.isEmpty()).mapToInt(w -> 1).sum();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return words;
    }

    private static int calculateNumberOfChars(CommandLine cmd) throws IOException {
        int chars = 0;
        try (InputStream is = getInputStream(cmd);
             InputStreamReader isr = new InputStreamReader(is)) {
            while (isr.read() != -1) {
                chars++;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return chars;
    }

    private static InputStream getInputStream(CommandLine cmd) throws IOException {
        InputStream is = null;
        if (System.in.available() > 0) {
            is = System.in;
        } else if (cmd.getOptions().length > 0) {
            File file = new File(cmd.getOptions()[0].getValue(0));
            if (file.exists()) {
                filename = file.getName();
                is = new FileInputStream(file);
            } else {
                System.out.println("Error: File not found");
            }
        } else {
            File file = new File(cmd.getArgList().getFirst());
            if (file.exists()) {
                filename = file.getName();
                is = new FileInputStream(file);
            } else {
                System.out.println("Error: File not found");
            }
        }
        return is;
    }
}