package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * 	Capability 1b: Test getOptionValue(char)
 *  Capability 2: Test hasOption(char)
 *  Capability 1c: Test getParsedOptionValue(char)
 *  Capability 1b: Test getOptionValues(char)
 */

public class Step1Test {

    private CommandLine cl;

    /* ----------- Capability 1b getOptionValue ----------- */

    public void capability1bSetUp() throws Exception {
        final String[] args = {"-a", "valuea", "-z", "valuez", "-G", "valueG", "-Z", "valueZ", "-test", "valuetest", "--property", "foo=bar"};

        final Options options = new Options();
        options.addOption(Option.builder("a").hasArg().build());
        options.addOption(Option.builder("z").hasArg().build());
        options.addOption(Option.builder("G").hasArg().build());
        options.addOption(Option.builder("Z").hasArg().build());
        options.addOption(Option.builder("test").hasArg().build());
        options.addOption(OptionBuilder.withValueSeparator().hasArgs(2).withLongOpt("property").create());

        final CommandLineParser parser = new DefaultParser();
        cl = parser.parse(options, args);
    }

    @Test
    // test case 1.b.42
    public void testGetOptionValueWithLowerBoundaryOfLowerCaseChar() throws Exception {
        capability1bSetUp();
        assertEquals("valuea", cl.getOptionValue('a'));
    }

    @Test
    // test case 1.b.43
    public void testGetOptionValueWithNominalLowerCaseChar() throws Exception {
        capability1bSetUp();
        assertEquals("", cl.getOptionValue('c'));
    }

    @Test
    // test case 1.b.44
    public void testGetOptionValueWithUpperBoundaryOfLowerCaseChar() throws Exception {
        capability1bSetUp();
        assertEquals("valuez", cl.getOptionValue('z'));
    }

    @Test
    // test case 1.b.45
    public void testGetOptionValueWithLowerBoundaryOfUpperCaseChar() throws Exception {
        capability1bSetUp();
        assertEquals("", cl.getOptionValue('A'));
    }

    @Test
    // test case 1.b.46
    public void testGetOptionValueWithNominalUpperCaseChar() throws Exception {
        capability1bSetUp();
        assertEquals("", cl.getOptionValue('F'));
    }

    @Test
    // test case 1.b.47
    public void testGetOptionValueWithUpperBoundaryOfUpperCaseChar() throws Exception {
        capability1bSetUp();
        assertEquals("valueZ", cl.getOptionValue('Z'));
    }

    /* ----------- Capability 2 hasOption ----------- */

    public void capability2SetUp() throws Exception {
        final String[] args = {"-a", "valuea", "-z", "valuez", "-G", "valueG", "-Z", "valueZ", "-test", "valuetest"};

        final Options options = new Options();
        options.addOption(Option.builder("a").hasArg().build());
        options.addOption(Option.builder("z").hasArg().build());
        options.addOption(Option.builder("G").hasArg().build());
        options.addOption(Option.builder("Z").hasArg().build());
        options.addOption(Option.builder("test").hasArg().build());

        final CommandLineParser parser = new DefaultParser();
        cl = parser.parse(options, args);
    }

    @Test
    // test case 2.1
    public void testHasOptionWithLowerBoundaryOfLowerCaseChar() throws Exception {
        capability2SetUp();
        assertEquals(true, cl.hasOption('a'));
    }

    @Test
    // test case 2.2
    public void testHasOptionWithNominalLowerCaseChar() throws Exception {
        capability2SetUp();
        assertEquals(false, cl.hasOption('f'));
    }

    @Test
    // test case 2.3
    public void testHasOptionWithUpperBoundaryOfLowerCaseChar() throws Exception {
        capability2SetUp();
        assertEquals(true, cl.hasOption('z'));
    }

    @Test
    // test case 2.4
    public void testHasOptionWithLowerBoundaryOfUpperCaseChar() throws Exception {
        capability2SetUp();
        assertEquals(false, cl.hasOption('A'));
    }

    @Test
    // test case 2.5
    public void testHasOptionWithNominalUpperCaseChar() throws Exception {
        capability2SetUp();
        assertEquals(true, cl.hasOption('G'));
    }

    @Test
    // test case 2.6
    public void testHasOptionWithUpperBoundaryOfUpperCaseChar() throws Exception {
        capability2SetUp();
        assertEquals(true, cl.hasOption('Z'));
    }

    /* ----------- Capability 1c getParsedOptionValue ----------- */

    public void capability1cSetUp() throws Exception {
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
        cl = parser.parse(options, args);
    }

    @Test
    // test case 1.c.1
    public void testGetParsedOptionValueWithLowerBoundaryOfLowerCaseChar() throws Exception {
        capability1cSetUp();
        assertEquals("valuea", cl.getParsedOptionValue('a'));
    }

    @Test
    // test case 1.c.2
    public void testGetParsedOptionValueNominalLowerCaseChar() throws Exception {
        capability1cSetUp();
        assertEquals("valuef", cl.getParsedOptionValue('f'));
    }

    @Test
    // test case 1.c.3
    public void testGetParsedOptionValueUpperBoundaryOfLowerCaseChar() throws Exception {
        capability1cSetUp();
        assertEquals("valuez", cl.getParsedOptionValue('z'));
    }

    @Test
    // test case 1.c.4
    public void testGetParsedOptionValueWithLowerBoundaryOfUpperCaseChar() throws Exception {
        capability1cSetUp();
        assertEquals("valueA", cl.getParsedOptionValue('A'));
    }

    @Test
    // test case 1.c.5
    public void testGetParsedOptionValueWithNominalUpperCaseChar() throws Exception {
        capability1cSetUp();
        assertEquals("valueG", cl.getParsedOptionValue('G'));
    }

    @Test
    // test case 1.c.6
    public void testGetParsedOptionValueWithUpperBoundaryOfUpperCaseChar() throws Exception {
        capability1cSetUp();
        assertEquals("valueZ", cl.getParsedOptionValue('Z'));
    }

    /* ----------- Capability 1b getOptionValues ----------- */

    @Test
    // test case 1.b.52
    public void testGetOptionValuesWithLowerBoundaryOfLowerCaseChar() throws Exception {
        capability1bSetUp();
        String[] expected = new String[1];
        expected[0] = "valuea";
        assertArrayEquals(expected, cl.getOptionValues('a'));
    }

    @Test
    // test case 1.b.53
    public void testGetOptionValuesWithNominalLowerCaseChar() throws Exception {
        capability1bSetUp();
        assertArrayEquals(null, cl.getOptionValues('c'));
    }

    @Test
    // test case 1.b.54
    public void testGetOptionValuesWithUpperBoundaryOfLowerCaseChar() throws Exception {
        capability1bSetUp();
        String[] expected = new String[1];
        expected[0] = "valuez";
        assertArrayEquals(expected, cl.getOptionValues('z'));
    }

    @Test
    // test case 1.b.55
    public void testGetOptionValuesWithLowerBoundaryOfUpperCaseChar() throws Exception {
        capability1bSetUp();
        assertArrayEquals(null, cl.getOptionValues('A'));
    }

    @Test
    // test case 1.b.56
    public void testGetOptionValuesWithNominalUpperCaseChar() throws Exception {
        capability1bSetUp();
        assertArrayEquals(null, cl.getOptionValues('F'));
    }

    @Test
    // test case 1.b.57
    public void testGetOptionValuesWithUpperBoundaryOfUpperCaseChar() throws Exception {
        capability1bSetUp();
        String[] expected = new String[1];
        expected[0] = "valueZ";
        assertArrayEquals(expected, cl.getOptionValues('Z'));
    }

}
