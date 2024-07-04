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

class CountWordsCommandTest {

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
    void countWordsInEmptyString() throws Exception {
        String input = "";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CommandLine cmd = new DefaultParser().parse(new Options(), new String[]{});
        CountWordsCommand countWordsCommand = new CountWordsCommand(cmd);
        assertEquals(0, countWordsCommand.count());
    }

    @Test
    void countWordsInSingleWordString() throws Exception {
        String input = "Hello";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CommandLine cmd = new DefaultParser().parse(new Options(), new String[]{});
        CountWordsCommand countWordsCommand = new CountWordsCommand(cmd);
        assertEquals(1, countWordsCommand.count());
    }

    @Test
    void countWordsInMultiWordString() throws Exception {
        String input = "Hello, World!";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CommandLine cmd = new DefaultParser().parse(new Options(), new String[]{});
        CountWordsCommand countWordsCommand = new CountWordsCommand(cmd);
        assertEquals(2, countWordsCommand.count());
    }
}