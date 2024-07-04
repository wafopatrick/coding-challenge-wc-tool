package com.cc;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CountBytesCommandTest {

    private final InputStream originalIn = System.in;

    @BeforeEach
    void beforeEach() {
        CountCommandHelper.resetInput();
    }

    @AfterEach
    void afterEach() {
        System.setIn(originalIn);
    }

    @Test
    void countReturnsNumberOfBytesInInputStream() throws Exception {
        String simulatedInput = "Hello, World!";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        CommandLine cmd = new DefaultParser().parse(new Options(), new String[]{});
        CountBytesCommand command = new CountBytesCommand(cmd);
        long count = command.count();
        assertEquals(13, count);
    }

    @Test
    void countReturnsZeroWhenInputStreamIsEmpty() throws Exception {
        System.setIn(new ByteArrayInputStream(new byte[0]));
        Options options = new Options();
        CommandLine cmd = new DefaultParser().parse(options, new String[]{});
        CountBytesCommand command = new CountBytesCommand(cmd);
        long count = command.count();
        assertEquals(0, count);
    }

    @Test
    void executePrintsNumberOfBytesInInputStream() throws Exception {
        String simulatedInput = "Hello, World!";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Options options = new Options();
        CommandLine cmd = new DefaultParser().parse(options, new String[]{});
        CountBytesCommand command = new CountBytesCommand(cmd);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        command.execute();
        assertEquals("13 \n", outContent.toString());
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    void executePrintsZeroWhenInputStreamIsEmpty() throws Exception {
        System.setIn(new ByteArrayInputStream(new byte[0]));
        Options options = new Options();
        CommandLine cmd = new DefaultParser().parse(options, new String[]{});
        CountBytesCommand command = new CountBytesCommand(cmd);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        command.execute();
        assertEquals("Error: No input provided\n0 \n", outContent.toString());
        System.setIn(System.in);
        System.setOut(System.out);
    }
}