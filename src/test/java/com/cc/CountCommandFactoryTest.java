package com.cc;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountCommandFactoryTest {

    @Test
    void createOptionsReturnsOptionsWithAllOptions() {
        Options options = CountCommandFactory.createOptions();
        assertTrue(options.hasOption("c"));
        assertTrue(options.hasOption("l"));
        assertTrue(options.hasOption("w"));
        assertTrue(options.hasOption("m"));
    }

    @Test
    void createCommandReturnsCountBytesCommandWhenOptionCIsProvided() throws Exception {
        Options options = CountCommandFactory.createOptions();
        CommandLine cmd = new DefaultParser().parse(options, new String[]{"-c"});
        CountCommand command = CountCommandFactory.createCommand(cmd);
        assertInstanceOf(CountBytesCommand.class, command);
    }

    @Test
    void createCommandReturnsCountLinesCommandWhenOptionLIsProvided() throws Exception {
        Options options = CountCommandFactory.createOptions();
        CommandLine cmd = new DefaultParser().parse(options, new String[]{"-l"});
        CountCommand command = CountCommandFactory.createCommand(cmd);
        assertInstanceOf(CountLinesCommand.class, command);
    }

    @Test
    void createCommandReturnsCountWordsCommandWhenOptionWIsProvided() throws Exception {
        Options options = CountCommandFactory.createOptions();
        CommandLine cmd = new DefaultParser().parse(options, new String[]{"-w"});
        CountCommand command = CountCommandFactory.createCommand(cmd);
        assertInstanceOf(CountWordsCommand.class, command);
    }

    @Test
    void createCommandReturnsCountCharsCommandWhenOptionMIsProvided() throws Exception {
        Options options = CountCommandFactory.createOptions();
        CommandLine cmd = new DefaultParser().parse(options, new String[]{"-m"});
        CountCommand command = CountCommandFactory.createCommand(cmd);
        assertInstanceOf(CountCharsCommand.class, command);
    }

    @Test
    void createCommandReturnsDefaultCommandWhenNoOptionIsProvided() throws Exception {
        Options options = new Options();
        CommandLine cmd = new DefaultParser().parse(options, new String[]{});
        CountCommand command = CountCommandFactory.createCommand(cmd);
        assertInstanceOf(DefaultCommand.class, command);
    }

}