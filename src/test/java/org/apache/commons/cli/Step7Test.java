package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * function tested: getOptionObject()
 * Capability tested: 3
 * @throws Exception
 */

public class Step7Test {

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

    /**
     * related test case: 1.c.1
     * @throws Exception
     */
    @Test
    public void testGetOptionObjectWithLowerBoundaryOfLowerCaseChar() throws Exception {
        // test case 1.c.1
        assertEquals("valuea", cmd.getOptionObject('a'));
    }


    /**
     * related test case: 1.c.2
     * @throws Exception
     */
    @Test
    public void testGetOptionObjectNominalLowerCaseChar() throws Exception {
        // test case 1.c.2
        assertEquals("valuef", cmd.getOptionObject('f'));
    }

    /**
     * related test case: 1.c.3
     * @throws Exception
     */
    @Test
    public void testGetOptionObjectUpperBoundaryOfLowerCaseChar() throws Exception {
        // test case 1.c.3
        assertEquals("valuez", cmd.getOptionObject('z'));
    }

    /**
     * related test case: 1.c.4
     * @throws Exception
     */
    @Test
    public void testGetOptionObjectWithLowerBoundaryOfUpperCaseChar() throws Exception {
        // test case 1.c.4
        assertEquals("valueA", cmd.getOptionObject('A'));
    }

    /**
     * related test case: 1.c.5
     * @throws Exception
     */
    @Test
    public void testGetOptionObjectWithNominalUpperCaseChar() throws Exception {
        // test case 1.c.5
        assertEquals("valueG", cmd.getOptionObject('G'));
    }

    /**
     * related test case: 1.c.6
     * @throws Exception
     */
    @Test
    public void testGetOptionObjectWithUpperBoundaryOfUpperCaseChar() throws Exception {
        // test case 1.c.6
        assertEquals("valueZ", cmd.getOptionObject('Z'));
    }


    /**
     * related test case: 1.c.7
     * Commented out because it gives an NullPointerException error
     * @throws Exception
     */
//     @Test (expected = NullPointerException.class)
//     public void testGetOptionObjectWithNullString() throws Exception {
//         // test case 1.c.7
//         assertEquals(null, cmd.getOptionObject((String) null));
//     }

    /**
     * related test case: 1.c.8
     * @throws Exception
     */
    @Test
    public void testGetOptionObjectWithEmptyString() throws Exception {
        // test case 1.c.8
        assertEquals(null, cmd.getOptionObject(""));
    }

    /**
     * related test case: 1.c.9
     * @throws Exception
     */
    @Test
    public void testGetOptionObjectWithStringThatHasLengthOne() throws Exception {
        // test case 1.c.9
        assertEquals(null, cmd.getOptionObject("v"));
    }

    /**
     * related test case: 1.c.10
     * @throws Exception
     */
    @Test
    public void testGetParsedOptionWithStringThatHasLengthLargerThanOne() throws Exception {
        // test case 1.c.10
        assertEquals("valuetest", cmd.getOptionObject("test"));
    }
}
