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
    // test case 1.c.1
    public void testGetParsedOptionValueWithLowerBoundaryOfLowerCaseChar() throws Exception {
        assertEquals("valuea", cmd.getParsedOptionValue('a'));
    }

    @Test
    // test case 1.c.2
    public void testGetParsedOptionValueNominalLowerCaseChar() throws Exception {
        assertEquals("valuef", cmd.getParsedOptionValue('f'));
    }

    @Test
    // test case 1.c.3
    public void testGetParsedOptionValueUpperBoundaryOfLowerCaseChar() throws Exception {
        assertEquals("valuez", cmd.getParsedOptionValue('z'));
    }

    @Test
    // test case 1.c.4
    public void testGetParsedOptionValueWithLowerBoundaryOfUpperCaseChar() throws Exception {
        assertEquals("valueA", cmd.getParsedOptionValue('A'));
    }

    @Test
    // test case 1.c.5
    public void testGetParsedOptionValueWithNominalUpperCaseChar() throws Exception {
        assertEquals("valueG", cmd.getParsedOptionValue('G'));
    }

    @Test
    // test case 1.c.6
    public void testGetParsedOptionValueWithUpperBoundaryOfUpperCaseChar() throws Exception {
        assertEquals("valueZ", cmd.getParsedOptionValue('Z'));
    }

    // @Test (expected = NullPointerException.class)
    // // test case 1.c.7
    // public void testGetParsedOptionValueWithNullString() throws Exception {
    //     assertEquals(null, cmd.getParsedOptionValue((String) null));
    // }

    @Test
    // test case 1.c.8
    public void testGetParsedOptionValueWithEmptyString() throws Exception {
        assertEquals(null, cmd.getParsedOptionValue(""));
    }

    @Test
    // test case 1.c.9
    public void testGetParsedOptionValueWithStringThatHasLengthOne() throws Exception {
        assertEquals(null, cmd.getParsedOptionValue("v"));
    }

    @Test
    // test case 1.c.10
    public void testGetParsedOptionWithStringThatHasLengthLargerThanOne() throws Exception {
        assertEquals("valuetest", cmd.getParsedOptionValue("test"));
    }
}
