package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public class CommandLineCapabilityOneBTest {

    private CommandLine cmd;

    @Before
    public void setUp() throws Exception {
        final String[] args = {"-a", "valuea", "-z", "valuez", "-G", "valueG", "-Z", "valueZ", "-test", "valuetest", "--property", "foo=bar"};

        final Options options = new Options();
        options.addOption(Option.builder("a").hasArg().build());
        options.addOption(Option.builder("z").hasArg().build());
        options.addOption(Option.builder("G").hasArg().build());
        options.addOption(Option.builder("Z").hasArg().build());
        options.addOption(Option.builder("test").hasArg().build());
        options.addOption(OptionBuilder.withValueSeparator().hasArgs(2).withLongOpt("property").create());

        final CommandLineParser parser = new DefaultParser();
        cmd = parser.parse(options, args);
    }

    @Test
    // test case 1.b.38
    public void testGetOptionValueWithNullString() throws Exception {
        assertEquals(null, cmd.getOptionValue((String) null));
    }

    @Test
    // test case 1.b.39
    public void testGetOptionValueWithEmptyString() throws Exception {
        assertEquals("", cmd.getOptionValue(""));
    }

    @Test
    // test case 1.b.40
    public void testGetOptionValueWithStringThatHasLengthOne() throws Exception {
        assertEquals("", cmd.getOptionValue("D"));
    }

    @Test
    // test case 1.b.41
    public void testGetOptionValueWithStringThatHasLengthLargerThanOne() throws Exception {
        assertEquals("bar", cmd.getOptionValue("foo"));
    }

    @Test
    // test case 1.b.42
    public void testGetOptionValueWithLowerBoundaryOfLowerCaseChar() throws Exception {
        assertEquals("valuea", cmd.getOptionValue('a'));
    }

    @Test
    // test case 1.b.43
    public void testGetOptionValueWithNominalLowerCaseChar() throws Exception {
        assertEquals("", cmd.getOptionValue('c'));
    }

    @Test
    // test case 1.b.44
    public void testGetOptionValueWithUpperBoundaryOfLowerCaseChar() throws Exception {
        assertEquals("valuez", cmd.getOptionValue('z'));
    }

    @Test
    // test case 1.b.45
    public void testGetOptionValueWithLowerBoundaryOfUpperCaseChar() throws Exception {
        assertEquals("", cmd.getOptionValue('A'));
    }

    @Test
    // test case 1.b.46
    public void testGetOptionValueWithNominalUpperCaseChar() throws Exception {
        assertEquals("", cmd.getOptionValue('F'));
    }

    @Test
    // test case 1.b.47
    public void testGetOptionValueWithUpperBoundaryOfUpperCaseChar() throws Exception {
        assertEquals("valueZ", cmd.getOptionValue('Z'));
    }

    // @Test
    // // test case 1.b.48
    // public void testGetOptionValuesWithNullString() throws Exception {
    //     assertArrayEquals(null, cmd.getOptionValues((String) null));
    // }

    @Test
    // test case 1.b.49
    public void testGetOptionValuesWithEmptyString() throws Exception {
        assertArrayEquals(null, cmd.getOptionValues(""));
    }

    @Test
    // test case 1.b.50
    public void testGetOptionValuesWithStringThatHasLengthOne() throws Exception {
        assertArrayEquals(null, cmd.getOptionValues("D"));
    }

    @Test
    // test case 1.b.51
    public void testGetOptionValuesWithStringThatHasLengthLargerThanOne() throws Exception {
        String[] expected = new String[1];
        expected[0] = "valuetest";
        assertArrayEquals(expected, cmd.getOptionValues("test"));
    }

    @Test
    // test case 1.b.52
    public void testGetOptionValuesWithLowerBoundaryOfLowerCaseChar() throws Exception {
        String[] expected = new String[1];
        expected[0] = "valuea";
        assertArrayEquals(expected, cmd.getOptionValues('a'));
    }

    @Test
    // test case 1.b.53
    public void testGetOptionValuesWithNominalLowerCaseChar() throws Exception {
        assertArrayEquals(null, cmd.getOptionValues('c'));
    }

    @Test
    // test case 1.b.54
    public void testGetOptionValuesWithUpperBoundaryOfLowerCaseChar() throws Exception {
        String[] expected = new String[1];
        expected[0] = "valuez";
        assertArrayEquals(expected, cmd.getOptionValues('z'));
    }

    @Test
    // test case 1.b.55
    public void testGetOptionValuesWithLowerBoundaryOfUpperCaseChar() throws Exception {
        assertArrayEquals(null, cmd.getOptionValues('A'));
    }

    @Test
    // test case 1.b.56
    public void testGetOptionValuesWithNominalUpperCaseChar() throws Exception {
        assertArrayEquals(null, cmd.getOptionValues('F'));
    }

    @Test
    // test case 1.b.57
    public void testGetOptionValuesWithUpperBoundaryOfUpperCaseChar() throws Exception {
        String[] expected = new String[1];
        expected[0] = "valueZ";
        assertArrayEquals(expected, cmd.getOptionValues('Z'));
    }
}
