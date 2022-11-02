package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class step7 {

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
    public void testGetOptionObjectWithLowerBoundaryOfLowerCaseChar() throws Exception {
        // test case 1.c.1
        assertEquals("valuea", cmd.getOptionObject('a'));
    }

    @Test
    public void testGetOptionObjectNominalLowerCaseChar() throws Exception {
        // test case 1.c.2
        assertEquals("valuef", cmd.getOptionObject('f'));
    }

    @Test
    public void testGetOptionObjectUpperBoundaryOfLowerCaseChar() throws Exception {
        // test case 1.c.3
        assertEquals("valuez", cmd.getOptionObject('z'));
    }

    @Test
    public void testGetOptionObjectWithLowerBoundaryOfUpperCaseChar() throws Exception {
        // test case 1.c.4
        assertEquals("valueA", cmd.getOptionObject('A'));
    }

    @Test
    public void testGetOptionObjectWithNominalUpperCaseChar() throws Exception {
        // test case 1.c.5
        assertEquals("valueG", cmd.getOptionObject('G'));
    }

    @Test
    public void testGetOptionObjectWithUpperBoundaryOfUpperCaseChar() throws Exception {
        // test case 1.c.6
        assertEquals("valueZ", cmd.getOptionObject('Z'));
    }

//     @Test (expected = NullPointerException.class)
//     public void testGetOptionObjectWithNullString() throws Exception {
//         // test case 1.c.7
//         assertEquals(null, cmd.getOptionObject((String) null));
//     }

    @Test
    public void testGetOptionObjectWithEmptyString() throws Exception {
        // test case 1.c.8
        assertEquals(null, cmd.getOptionObject(""));
    }

    @Test
    public void testGetOptionObjectWithStringThatHasLengthOne() throws Exception {
        // test case 1.c.9
        assertEquals(null, cmd.getOptionObject("v"));
    }

    @Test
    public void testGetParsedOptionWithStringThatHasLengthLargerThanOne() throws Exception {
        // test case 1.c.10
        assertEquals("valuetest", cmd.getOptionObject("test"));
    }
}
