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
    // test case 2.1
    public void testHasOptionWithLowerBoundaryOfLowerCaseChar() throws Exception {
        assertEquals(true, cmd.hasOption('a'));
    }

    @Test
    // test case 2.2
    public void testHasOptionWithNominalLowerCaseChar() throws Exception {
        assertEquals(false, cmd.hasOption('f'));
    }

    @Test
    // test case 2.3
    public void testHasOptionWithUpperBoundaryOfLowerCaseChar() throws Exception {
        assertEquals(true, cmd.hasOption('z'));
    }

    @Test
    // test case 2.4
    public void testHasOptionWithLowerBoundaryOfUpperCaseChar() throws Exception {
        assertEquals(false, cmd.hasOption('A'));
    }

    @Test
    // test case 2.5
    public void testHasOptionWithNominalUpperCaseChar() throws Exception {
        assertEquals(true, cmd.hasOption('G'));
    }

    @Test
    // test case 2.6
    public void testHasOptionWithUpperBoundaryOfUpperCaseChar() throws Exception {
        assertEquals(true, cmd.hasOption('Z'));
    }

    // @Test (expected = NullPointerException.class)
    // // test case 2.7
    // public void testHasOptionWithNullString() throws Exception {
    //     assertEquals(false, cmd.hasOption((String) null));
    // }

    @Test
    // test case 2.8
    public void testHasOptionWithEmptyString() throws Exception {
        assertEquals(false, cmd.hasOption(""));
    }

    @Test
    // test case 2.9
    public void testHasOptionWithStringThatHasLengthOne() throws Exception {
        assertEquals(false, cmd.hasOption("v"));
    }

    @Test
    // test case 2.10
    public void testHasOptionWithStringThatHasLengthLargerThanOne() throws Exception {
        assertEquals(true, cmd.hasOption("test"));
    }
}
