package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public class CommandLineCapabilityOneBTest {

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
    public void testGetOptionValueWithNullString() throws Exception {
        // test case 1.1
        assertEquals(null, cmd.getOptionValue((String) null));
    }

    @Test
    public void testGetOptionValueWithEmptyString() throws Exception {
        // test case 1.1
        assertEquals("", cmd.getOptionValue(""));
    }

    @Test
    public void testGetOptionValueWithStringThatHasLengthOne() throws Exception {
        // test case 1.1
        assertEquals("", cmd.getOptionValue("D"));
    }

    @Test
    public void testGetOptionValueWithStringThatHasLengthLargerThanOne() throws Exception {
        // test case 1.1
        assertEquals("valuetest", cmd.getOptionValue("test"));
    }

    @Test
    public void testGetOptionValueWithLowerBoundaryOfLowerCaseChar() throws Exception {
        // test case 2.1
        assertEquals("valuea", cmd.getOptionValue('a'));
    }

    @Test
    public void testGetOptionValueWithNominalLowerCaseChar() throws Exception {
        // test case 2.2
        assertEquals("", cmd.getOptionValue('f'));
    }

    @Test
    public void testGetOptionValueWithUpperBoundaryOfLowerCaseChar() throws Exception {
        // test case 2.3
        assertEquals("valuez", cmd.getOptionValue('z'));
    }

    @Test
    public void testGetOptionValueWithLowerBoundaryOfUpperCaseChar() throws Exception {
        // test case 2.4
        assertEquals("", cmd.getOptionValue('A'));
    }

    @Test
    public void testGetOptionValueWithNominalUpperCaseChar() throws Exception {
        // test case 2.5
        assertEquals("", cmd.getOptionValue('F'));
    }

    @Test
    public void testGetOptionValueWithUpperBoundaryOfUpperCaseChar() throws Exception {
        // test case 2.6
        assertEquals("valueZ", cmd.getOptionValue('Z'));
    }

    @Test
    public void testGetOptionValuesWithNullString() throws Exception {
        // test case 1.1
        assertArrayEquals(null, cmd.getOptionValues((String) null));
    }

    @Test
    public void testGetOptionValuesWithEmptyString() throws Exception {
        // test case 1.1
        String[] expected = new String[1];
        assertArrayEquals(expected, cmd.getOptionValues(""));
    }

    @Test
    public void testGetOptionValuesWithStringThatHasLengthOne() throws Exception {
        // test case 1.1
        String[] expected = new String[1];
        assertArrayEquals(expected, cmd.getOptionValues("D"));
    }

    @Test
    public void testGetOptionValuesWithStringThatHasLengthLargerThanOne() throws Exception {
        // test case 1.1
        String[] expected = new String[1];
        expected[0] = "valuetest";
        assertArrayEquals(expected, cmd.getOptionValues("test"));
    }

    @Test
    public void testGetOptionValuesWithLowerBoundaryOfLowerCaseChar() throws Exception {
        // test case 2.1
        String[] expected = new String[1];
        expected[0] = "valuea";
        assertArrayEquals(expected, cmd.getOptionValues('a'));
    }

    @Test
    public void testGetOptionValuesWithNominalLowerCaseChar() throws Exception {
        // test case 2.2
        String[] expected = new String[1];
        assertArrayEquals(expected, cmd.getOptionValues('f'));
    }

    @Test
    public void testGetOptionValuesWithUpperBoundaryOfLowerCaseChar() throws Exception {
        // test case 2.3
        String[] expected = new String[1];
        expected[0] = "valuez";
        assertArrayEquals(expected, cmd.getOptionValues('z'));
    }

    @Test
    public void testGetOptionValuesWithLowerBoundaryOfUpperCaseChar() throws Exception {
        // test case 2.4
        String[] expected = new String[1];
        assertArrayEquals(expected, cmd.getOptionValues('A'));
    }

    @Test
    public void testGetOptionValuesWithNominalUpperCaseChar() throws Exception {
        // test case 2.5
        String[] expected = new String[1];
        assertArrayEquals(expected, cmd.getOptionValues('F'));
    }

    @Test
    public void testGetOptionValuesWithUpperBoundaryOfUpperCaseChar() throws Exception {
        // test case 2.6
        String[] expected = new String[1];
        expected[0] = "valueZ";
        assertArrayEquals(expected, cmd.getOptionValues('Z'));
    }
}
