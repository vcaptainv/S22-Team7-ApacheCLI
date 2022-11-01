package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CommandLineCapabilityOneCTest {

    private CommandLine cmd;

    @Before
    public void setUp() throws Exception {
        final String[] args = {"-a", "valuea", "-f", "valuef", "-z", "valuez", "-A", "valueA", "-G", "valueG", "-Z", "valueZ", "-test", "valuetest"};

        final Options options = new Options();
        options.addOption(Option.builder("a").hasArg().build());
        options.addOption(Option.builder("f").hasArg().build());
        options.addOption(Option.builder("z").hasArg().build());
        options.addOption(Option.builder("A").hasArg().build());
        options.addOption(Option.builder("G").hasArg().build());
        options.addOption(Option.builder("Z").hasArg().build());
        options.addOption(Option.builder("test").hasArg().build());

        final CommandLineParser parser = new DefaultParser();
        cmd = parser.parse(options, args);
    }
    
    @Test
    public void testGetParsedOptionValueWithLowerBoundaryOfLowerCaseChar() throws Exception {
        // test case 1.c.1
        assertEquals("valuea", cmd.getParsedOptionValue('a'));
    }

    @Test
    public void testGetParsedOptionValueNominalLowerCaseChar() throws Exception {
        // test case 1.c.2
        assertEquals("valuef", cmd.getParsedOptionValue('f'));
    }

    @Test
    public void testGetParsedOptionValueUpperBoundaryOfLowerCaseChar() throws Exception {
        // test case 1.c.3
        assertEquals("valuez", cmd.getParsedOptionValue('z'));
    }

    @Test
    public void testGetParsedOptionValueWithLowerBoundaryOfUpperCaseChar() throws Exception {
        // test case 1.c.4
        assertEquals("valueA", cmd.getParsedOptionValue('A'));
    }

    @Test
    public void testGetParsedOptionValueWithNominalUpperCaseChar() throws Exception {
        // test case 1.c.5
        assertEquals("valueG", cmd.getParsedOptionValue('G'));
    }

    @Test
    public void testGetParsedOptionValueWithUpperBoundaryOfUpperCaseChar() throws Exception {
        // test case 1.c.6
        assertEquals("valueZ", cmd.getParsedOptionValue('Z'));
    }

    // @Test (expected = NullPointerException.class)
    // public void testGetParsedOptionValueWithNullString() throws Exception {
    //     // test case 1.c.7
    //     assertEquals(null, cmd.getParsedOptionValue((String) null));
    // }

    @Test
    public void testGetParsedOptionValueWithEmptyString() throws Exception {
        // test case 1.c.8
        assertEquals(null, cmd.getParsedOptionValue(""));
    }

    @Test
    public void testGetParsedOptionValueWithStringThatHasLengthOne() throws Exception {
        // test case 1.c.9
        assertEquals(null, cmd.getParsedOptionValue("v"));
    }

    @Test
    public void testGetParsedOptionWithStringThatHasLengthLargerThanOne() throws Exception {
        // test case 1.c.10
        assertEquals("valuetest", cmd.getParsedOptionValue("test"));
    }
}
