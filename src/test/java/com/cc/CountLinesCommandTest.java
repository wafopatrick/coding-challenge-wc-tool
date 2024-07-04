package com.cc;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountLinesCommandTest {

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
    void countLinesInEmptyString() throws Exception {
        String input = "";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CommandLine cmd = new DefaultParser().parse(new Options(), new String[]{});
        CountLinesCommand countLinesCommand = new CountLinesCommand(cmd);
        assertEquals(0, countLinesCommand.count());
    }

    @Test
    void countLinesInSingleLineString() throws Exception {
        String input = "Hello, World!";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CommandLine cmd = new DefaultParser().parse(new Options(), new String[]{});
        CountLinesCommand countLinesCommand = new CountLinesCommand(cmd);
        assertEquals(1, countLinesCommand.count());
    }

    @Test
    void countLinesInMultiLineString() throws Exception {
        String input = "Hello,\nWorld!";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CommandLine cmd = new DefaultParser().parse(new Options(), new String[]{});
        CountLinesCommand countLinesCommand = new CountLinesCommand(cmd);
        assertEquals(2, countLinesCommand.count());
    }
}