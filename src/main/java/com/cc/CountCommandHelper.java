package com.cc;

import org.apache.commons.cli.CommandLine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class CountCommandHelper {

    private static String filename;
    private static byte[] inputData;

    static InputStream getInputStream(CommandLine cmd) throws IOException {
        if (System.in.available() > 0) {
            Path tempStdinFile = Files.createTempFile("stdin", ".tmp");
            try (OutputStream tempOut = Files.newOutputStream(tempStdinFile)) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while ((bytesRead = System.in.read(buffer)) != -1) {
                    tempOut.write(buffer, 0, bytesRead);
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                inputData = byteArrayOutputStream.toByteArray();
            }
        } else if (inputData != null) {
            return new ByteArrayInputStream(inputData);
        } else if (!cmd.getArgList().isEmpty()) {
            File file = new File(cmd.getArgList().getFirst());
            if (file.exists()) {
                filename = file.getName();
                try (InputStream is = new FileInputStream(file)) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, bytesRead);
                    }
                    inputData = byteArrayOutputStream.toByteArray();
                }
            } else {
                throw new FileNotFoundException("Error: File not found");
            }
        } else {
            throw new IOException("Error: No input provided");
        }
        return new ByteArrayInputStream(inputData);
    }

    public static String getFilename() {
        return Optional.ofNullable(filename).orElse("");
    }

    public static void resetInput() {
        inputData = null;
    }
}