package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CommandLineCapabilityTwoTest {

    private CommandLine cmd;

    @Before
    public void setUp() throws Exception {
        final String[] args = {"-a", "valuea", "-z", "valuez", "-G", "valueG", "-Z", "valueZ", "-test", "valuetest"};

        final Options options = new Options();
        options.addOption(Option.builder("a").hasArg().build());
        options.addOption(Option.builder("z").hasArg().build());
        options.addOption(Option.builder("G").hasArg().build());
        options.addOption(Option.builder("Z").hasArg().build());
        options.addOption(Option.builder("test").hasArg().build());

        final CommandLineParser parser = new DefaultParser();
        cmd = parser.parse(options, args);
    }

    @Test
    public void testHasOptionWithLowerBoundaryOfLowerCaseChar() throws Exception {
        // test case 2.1
        assertEquals(true, cmd.hasOption('a'));
    }

    @Test
    public void testHasOptionWithNominalLowerCaseChar() throws Exception {
        // test case 2.2
        assertEquals(false, cmd.hasOption('f'));
    }

    @Test
    public void testHasOptionWithUpperBoundaryOfLowerCaseChar() throws Exception {
        // test case 2.3
        assertEquals(true, cmd.hasOption('z'));
    }

    @Test
    public void testHasOptionWithLowerBoundaryOfUpperCaseChar() throws Exception {
        // test case 2.4
        assertEquals(false, cmd.hasOption('A'));
    }

    @Test
    public void testHasOptionWithNominalUpperCaseChar() throws Exception {
        // test case 2.5
        assertEquals(true, cmd.hasOption('G'));
    }

    @Test
    public void testHasOptionWithUpperBoundaryOfUpperCaseChar() throws Exception {
        // test case 2.6
        assertEquals(true, cmd.hasOption('Z'));
    }

    // @Test (expected = NullPointerException.class)
    // public void testHasOptionWithNullString() throws Exception {
    //     // test case 2.7
    //     assertEquals(false, cmd.hasOption((String) null));
    // }

    @Test
    public void testHasOptionWithEmptyString() throws Exception {
        // test case 2.8
        assertEquals(false, cmd.hasOption(""));
    }

    @Test
    public void testHasOptionWithStringThatHasLengthOne() throws Exception {
        // test case 2.9
        assertEquals(false, cmd.hasOption("v"));
    }

    @Test
    public void testHasOptionWithStringThatHasLengthLargerThanOne() throws Exception {
        // test case 2.10
        assertEquals(true, cmd.hasOption("test"));
    }
}
