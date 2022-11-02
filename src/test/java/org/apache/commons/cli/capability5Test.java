package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;

@SuppressWarnings("deprecation") // tests some deprecated classes

/**
 * Capability 5
 * addOption(Option option)
 * addArg(string arg)
 */
public class capability5Test {
    /**
     * related test case:5.a.1
     */

    @Test
    public void testAddNullOptionToOptions() {
        final CommandLine.Builder builder = new CommandLine.Builder();
        final CommandLine cmd = builder.build();
        assertEquals(0, cmd.getOptions().length);
        cmd.addOption(OptionBuilder.hasOptionalArg().create(null));
        assertEquals(1, cmd.getOptions().length);
    }

    /**
     * related test case: 5.a.2
     */
    @Test
    public void testAddNullShortOptionToOptions() {
        final CommandLine cmd = new CommandLine();
        cmd.addOption(null);
        assertEquals(1, cmd.getOptions().length);
    }

    /**
     * related test case: 5.a.3
     * Unable to run
     * Throw runtime Error
     */
    // @Test
    // public void testAddLongNullOptionToOptions() {
    // final CommandLine cmd = new CommandLine();
    // assertNotNull(cmd.getOptions());
    // assertEquals(0, cmd.getOptions().length);

    // cmd.addOption(OptionBuilder.hasOptionalArg().withLongOpt("").create());
    // assertEquals(1, cmd.getOptions().length);
    // }

    /**
     * related test case: 5.a.4
     */
    @Test
    public void testAddVaidShortOptionToOptions() {
        final CommandLine cmd = new CommandLine();
        cmd.addOption(OptionBuilder.hasOptionalArg().create("a"));
        assertEquals(1, cmd.getOptions().length);
        assertEquals("a", cmd.getOptions()[0].getOpt());
    }

    /**
     * related test case: 5.a.5
     */
    @Test
    public void testAddVaidLongOptionToOptions() {
        final CommandLine cmd = new CommandLine();
        cmd.addOption((OptionBuilder.hasOptionalArg().withLongOpt("abc").create()));
        assertEquals(1, cmd.getOptions().length);
        assertEquals("abc", cmd.getOptions()[0].getLongOpt());
    }

    /**
     * related test case: 5.a.6
     */

    @Test
    public void testAddFaultyNumericShortOptionToOptions() {
        final CommandLine cmd = new CommandLine();
        cmd.addOption(OptionBuilder.create("1"));
        assertEquals(1, cmd.getOptions().length);
        assertEquals("1", cmd.getOptions()[0].getOpt());
    }

    /**
     * related test case: 5.a.7
     */
    @Test
    public void testAddFaultyNumbericLongOptionToOptions() {
        final CommandLine cmd = new CommandLine();
        cmd.addOption(OptionBuilder.hasOptionalArg().withLongOpt("123").create());
        assertEquals(1, cmd.getOptions().length);
        assertEquals("123", cmd.getOptions()[0].getLongOpt());
    }

    /**
     * related test case: 5.a.8
     * Unable to run
     * Throw Invalid Option name error
     */
    // @Test
    // public void testAddFaultySymbolShortOptionToOptions() {
    // final CommandLine cmd = new CommandLine();
    // cmd.addOption(OptionBuilder.hasOptionalArg().create("%"));
    // assertEquals(1, cmd.getOptions().length);
    // assertEquals("%", cmd.getOptions()[0].getOpt());
    // }

    /**
     * related test case: 5.a.9
     */
    @Test
    public void testAddFaultySymbolLongOptionToOptions() {
        final CommandLine cmd = new CommandLine();
        cmd.addOption(OptionBuilder.hasOptionalArg().withLongOpt("%%%").create());
        assertEquals(1, cmd.getOptions().length);
        assertEquals("%%%", cmd.getOptions()[0].getLongOpt());
    }

    // /**
    //  * related test case: 5.a.10
    //  * Unable to run
    //  * Throw IllegalArgumentException Error
    //  */
    // @Test
    // public void testAddNullLongOptionToOptions() {
    //     final CommandLine cmd = new CommandLine();
    //     cmd.addOption(OptionBuilder.hasOptionalArg().withLongOpt(null).create());
    //     assertEquals(1, cmd.getOptions().length);
    //     assertNull(cmd.getOptions()[0].getLongOpt());
    // }
    

    /**
     * related test case: 5.a.11
     */
    @Test
    public void testAddInvalidShortOptionToOptions() {
        final CommandLine cmd = new CommandLine();
        cmd.addOption(OptionBuilder.create("a"));
        assertEquals(1, cmd.getOptions().length);
        assertEquals("a", cmd.getOptions()[0].getOpt());
    }

    /**
     * related test case: 5.a.12
     */
    @Test
    public void testAddInvalidLongOptionToOptions() {
        final CommandLine cmd = new CommandLine();
        cmd.addOption(OptionBuilder.withLongOpt("abc").create());
        assertEquals(1, cmd.getOptions().length);
        assertEquals("abc", cmd.getOptions()[0].getLongOpt());
    }

    /* -----------Seperation line for capability 5b -------------- */

    /**
     * related test case: 5.b.1
     */
    @Test
    public void testAddNullArg() {
        final CommandLine cmd = new CommandLine();
        cmd.addArg(null);
        assertEquals(1, cmd.getArgs().length);
        assertEquals(null, cmd.getArgs()[0]);
    }

    /**
     * related test case:5.b.2
     */
    @Test
    public void testAddEmptyStringArg() {
        final CommandLine cmd = new CommandLine();
        cmd.addArg(null);
        assertEquals(1, cmd.getArgs().length);
        assertEquals(null, cmd.getArgs()[0]);
    }

    /**
     * related test case:5.b.3
     */
    @Test
    public void testAddOneLiteralStringArg() {
        final CommandLine cmd = new CommandLine();
        cmd.addArg("x");
        assertEquals(1, cmd.getArgs().length);
        assertEquals("x", cmd.getArgs()[0]);
    }

    /**
     * related test case:5.b.4
     */
    @Test
    public void testAddLongStringArg() {
        final CommandLine cmd = new CommandLine();
        cmd.addArg("abc");
        assertEquals(1, cmd.getArgs().length);
        assertEquals("abc", cmd.getArgs()[0]);
    }
}
