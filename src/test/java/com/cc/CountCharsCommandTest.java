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

class CountCharsCommandTest {

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
    void countCharsInEmptyString() throws Exception {
        String input = "";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CommandLine cmd = new DefaultParser().parse(new Options(), new String[]{});
        CountCommandHelper.resetInput();
        CountCharsCommand countCharsCommand = new CountCharsCommand(cmd);
        assertEquals(0, countCharsCommand.count());
    }

    @Test
    void countCharsInSingleCharString() throws Exception {
        String input = "H";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CommandLine cmd = new DefaultParser().parse(new Options(), new String[]{});
        CountCharsCommand countCharsCommand = new CountCharsCommand(cmd);
        assertEquals(1, countCharsCommand.count());
    }

    @Test
    void countCharsInMultiCharString() throws Exception {
        String input = "Hello, World!";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CommandLine cmd = new DefaultParser().parse(new Options(), new String[]{});
        CountCharsCommand countCharsCommand = new CountCharsCommand(cmd);
        assertEquals(13, countCharsCommand.count());
    }
}