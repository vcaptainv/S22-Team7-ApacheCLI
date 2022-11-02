package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;

/**
 * Capability 6
 * builder() in CommandLineBuilder
 */

@SuppressWarnings("deprecation") // tests some deprecated classes
public class Step5Test {
    /**
     * related test case:6.1
     */
    @Test
    public void testBuilderWithEmptyOptionsAndEmptyArgs() {
        final CommandLine.Builder builder = new CommandLine.Builder();
        CommandLine cmd = builder.build();
        assertEquals(0, cmd.getOptions().length);
        assertEquals(0, cmd.getArgs().length);
        assertEquals(0, cmd.getArgList().size());
    }

    /**
     * related test case:6.2
     */
    @Test
    public void testBuilderWithOneElementOptionsAndNullArgs() {
        final CommandLine.Builder builder = new CommandLine.Builder();
        builder.addOption(OptionBuilder.hasOptionalArg().create("a"));
        CommandLine cmd = builder.build();
        assertEquals(1, cmd.getOptions().length);
        assertEquals(0, cmd.getArgs().length);
        assertEquals(0, cmd.getArgList().size());
    }

    /**
     * related test case:6.3
     */
    @Test
    public void testBuilderWithMultipleElementsOptionsAndNullArgs() {
        final CommandLine.Builder builder = new CommandLine.Builder();
        builder.addOption(OptionBuilder.hasOptionalArg().create("a"));
        builder.addOption(OptionBuilder.hasOptionalArg().create("b"));
        CommandLine cmd = builder.build();
        assertEquals(2, cmd.getOptions().length);
        assertEquals(0, cmd.getArgs().length);
        assertEquals(0, cmd.getArgList().size());
    }

    /**
     * related test case:6.4
     */
    @Test
    public void testBuilderWithEmptyOptionsAndOneElementArgs() {
        final CommandLine.Builder builder = new CommandLine.Builder();
        builder.addArg("a");
        CommandLine cmd = builder.build();
        assertEquals(0, cmd.getOptions().length);
        assertEquals(1, cmd.getArgs().length);
        assertEquals(1, cmd.getArgList().size());
    }

    /**
     * related test case:6.5
     */
    @Test
    public void testBuilderWithOneElementOptionsAndOneElementArgs() {
        final CommandLine.Builder builder = new CommandLine.Builder();
        builder.addOption(OptionBuilder.hasOptionalArg().create("a"));
        builder.addArg("a");
        CommandLine cmd = builder.build();
        assertEquals(1, cmd.getOptions().length);
        assertEquals(1, cmd.getArgs().length);
        assertEquals(1, cmd.getArgList().size());
    }

    /**
     * related test case:6.6
     */
    @Test
    public void testBuilderWithMultipleElementsOptionsAndOneElementArgs() {
        final CommandLine.Builder builder = new CommandLine.Builder();
        builder.addOption(OptionBuilder.hasOptionalArg().create("a"));
        builder.addOption(OptionBuilder.hasOptionalArg().create("b"));
        builder.addArg("a");
        CommandLine cmd = builder.build();
        assertEquals(2, cmd.getOptions().length);
        assertEquals(1, cmd.getArgs().length);
        assertEquals(1, cmd.getArgList().size());
    }

    /**
     * related test case:6.7
     */
    @Test
    public void testBuilderWithEmptyOptionsAndMultipleElementArgs() {
        final CommandLine.Builder builder = new CommandLine.Builder();
        builder.addArg("a");
        builder.addArg("b");
        CommandLine cmd = builder.build();
        assertEquals(0, cmd.getOptions().length);
        assertEquals(2, cmd.getArgs().length);
        assertEquals(2, cmd.getArgList().size());
    }

    /**
     * related test case:6.5
     */
    @Test
    public void testBuilderWithOneElementOptionsAndMultipleElementArgs() {
        final CommandLine.Builder builder = new CommandLine.Builder();
        builder.addOption(OptionBuilder.hasOptionalArg().create("a"));
        builder.addArg("a");
        builder.addArg("b");
        CommandLine cmd = builder.build();
        assertEquals(1, cmd.getOptions().length);
        assertEquals(2, cmd.getArgs().length);
        assertEquals(2, cmd.getArgList().size());
    }

    /**
     * related test case:6.6
     */
    @Test
    public void testBuilderWithMultipleElementsOptionsAndMultipleElementArgs() {
        final CommandLine.Builder builder = new CommandLine.Builder();
        builder.addOption(OptionBuilder.hasOptionalArg().create("a"));
        builder.addOption(OptionBuilder.hasOptionalArg().create("b"));
        builder.addArg("a");
        builder.addArg("b");
        CommandLine cmd = builder.build();
        assertEquals(2, cmd.getOptions().length);
        assertEquals(2, cmd.getArgs().length);
        assertEquals(2, cmd.getArgList().size());
    }
}
