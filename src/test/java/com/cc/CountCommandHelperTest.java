package com.cc;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class CountCommandHelperTest {

    private CommandLine cmd;

    @BeforeEach
    void setUp() throws Exception {
        Options options = new Options();
        cmd = new DefaultParser().parse(options, new String[]{});
        CountCommandHelper.resetInput();
    }

    @Test
    void getInputStreamReturnsInputStreamWhenFileIsProvided() throws Exception {
        Options options = new Options();
        cmd = new DefaultParser().parse(options, new String[]{"src/test/resources/test.txt"});
        InputStream is = CountCommandHelper.getInputStream(cmd);
        assertNotNull(is);
    }

    @Test
    void getInputStreamThrowsIOExceptionWhenNoInputIsProvided() {
        assertThrows(IOException.class, () -> CountCommandHelper.getInputStream(cmd));
    }

    @Test
    void getInputStreamThrowsIOExceptionWhenFileDoesNotExist() throws Exception {
        Options options = new Options();
        cmd = new DefaultParser().parse(options, new String[]{"nonexistent.txt"});
        assertThrows(IOException.class, () -> CountCommandHelper.getInputStream(cmd));
    }

    @Test
    void getFilenameReturnsEmptyStringWhenNoFileIsProvided() {
        String filename = CountCommandHelper.getFilename();
        assertEquals("", filename);
    }

    @Test
    void getFilenameReturnsFilenameWhenFileIsProvided() throws Exception {
        Options options = new Options();
        cmd = new DefaultParser().parse(options, new String[]{"src/test/resources/test.txt"});
        CountCommandHelper.getInputStream(cmd);
        String filename = CountCommandHelper.getFilename();
        assertEquals("test.txt", filename);
    }
}