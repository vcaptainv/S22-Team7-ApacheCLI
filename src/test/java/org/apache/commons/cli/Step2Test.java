package org.apache.commons.cli;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.junit.Test;

/**
 *  Capability 1a: Test getOptionProperties(String)
 *  Capability 1b: Test getOptionValues(String)
 *  Capability 1b: Test getOptionValue(String)
 *  Capability 2: Test hasOption(String)
 *  Capability 1c:  Test getParsedOptionValue(String)
 */

public class Step2Test {

    private CommandLine cl;

    /* ----------- Capability 1a getOptionProperties ----------- */

    public void capability1aSetUp() throws Exception {
        final String[] args = {"-Dparam1=value1", "-Dparam2=value2", "-Dparam3", "-Dparam4=value4", "-D", "--property", "foo=bar"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withValueSeparator().hasOptionalArgs(2).create('D'));
        options.addOption(OptionBuilder.withValueSeparator().hasArgs(2).withLongOpt("property").create());

        final Parser parser = new GnuParser();
        cl = parser.parse(options, args);
    }

    // @Test (expected = NullPointerException.class)
    // // test case 1.a.1
    // public void testGetOptionPropertiesWithNullString() throws Exception {
    //     capability1aSetUp();

    //     final Properties props = cl.getOptionProperties((String) null);
    //     assertNotNull("null properties", props);
    //     assertEquals("number of properties in " + props, 0, props.size());
    // }

    @Test
    // test case 1.a.2
    public void testGetOptionPropertiesWithEmptyString() throws Exception {
        capability1aSetUp();

        final Properties props = cl.getOptionProperties("");
        assertNotNull("null properties", props);
        assertEquals("number of properties in " + props, 0, props.size());
    }

    @Test
    // test case 1.a.3
    public void testGetOptionPropertiesWithStringThatHasLengthOne() throws Exception {
        capability1aSetUp();

        final Properties props = cl.getOptionProperties("D");
        assertNotNull("null properties", props);
        assertEquals("number of properties in " + props, 4, props.size());
        assertEquals("property 1", "value1", props.getProperty("param1"));
        assertEquals("property 2", "value2", props.getProperty("param2"));
        assertEquals("property 3", "true", props.getProperty("param3"));
        assertEquals("property 4", "value4", props.getProperty("param4"));
    }

    @Test
    // test case 1.a.4
    public void testGetOptionPropertiesWithStringThatHasLengthGreaterThanOne() throws Exception {
        capability1aSetUp();

        final Properties props = cl.getOptionProperties("property");
        assertNotNull("null properties", props);
        assertEquals("number of properties in " + props, 1, props.size());
        assertEquals("property foo", "bar", props.getProperty("foo"));
    }

    /* ----------- Capability 1b getOptionValues ----------- */

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

    // @Test
    // // test case 1.b.48
    // public void testGetOptionValuesWithNullString() throws Exception {
    //     getOptionValuesSetUp();
    //     assertArrayEquals(null, cl.getOptionValues((String) null));
    // }

    @Test
    // test case 1.b.49
    public void testGetOptionValuesWithEmptyString() throws Exception {
        capability1bSetUp();
        assertArrayEquals(null, cl.getOptionValues(""));
    }

    @Test
    // test case 1.b.50
    public void testGetOptionValuesWithStringThatHasLengthOne() throws Exception {
        capability1bSetUp();
        assertArrayEquals(null, cl.getOptionValues("D"));
    }

    @Test
    // test case 1.b.51
    public void testGetOptionValuesWithStringThatHasLengthLargerThanOne() throws Exception {
        capability1bSetUp();
        String[] expected = new String[1];
        expected[0] = "valuetest";
        assertArrayEquals(expected, cl.getOptionValues("test"));
    }

    /* ----------- Capability 1b getOptionValue ----------- */

    @Test
    // test case 1.b.38
    public void testGetOptionValueWithNullString() throws Exception {
        capability1bSetUp();
        assertEquals(null, cl.getOptionValue((String) null));
    }

    @Test
    // test case 1.b.39
    public void testGetOptionValueWithEmptyString() throws Exception {
        capability1bSetUp();
        assertEquals("", cl.getOptionValue(""));
    }

    @Test
    // test case 1.b.40
    public void testGetOptionValueWithStringThatHasLengthOne() throws Exception {
        capability1bSetUp();
        assertEquals("", cl.getOptionValue("D"));
    }

    @Test
    // test case 1.b.41
    public void testGetOptionValueWithStringThatHasLengthLargerThanOne() throws Exception {
        capability1bSetUp();
        assertEquals("bar", cl.getOptionValue("foo"));
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

    // @Test (expected = NullPointerException.class)
    // // test case 2.7
    // public void testHasOptionWithNullString() throws Exception {
    //     capability2SetUp();
    //     assertEquals(false, cl.hasOption((String) null));
    // }

    @Test
    // test case 2.8
    public void testHasOptionWithEmptyString() throws Exception {
        capability2SetUp();
        assertEquals(false, cl.hasOption(""));
    }

    @Test
    // test case 2.9
    public void testHasOptionWithStringThatHasLengthOne() throws Exception {
        capability2SetUp();
        assertEquals(false, cl.hasOption("v"));
    }

    @Test
    // test case 2.10
    public void testHasOptionWithStringThatHasLengthLargerThanOne() throws Exception {
        capability2SetUp();
        assertEquals(true, cl.hasOption("test"));
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

    // @Test (expected = NullPointerException.class)
    // // test case 1.c.7
    // public void testGetParsedOptionValueWithNullString() throws Exception {
    //     capability1cSetUp();
    //     assertEquals(null, cl.getParsedOptionValue((String) null));
    // }

    @Test
    // test case 1.c.8
    public void testGetParsedOptionValueWithEmptyString() throws Exception {
        capability1cSetUp();
        assertEquals(null, cl.getParsedOptionValue(""));
    }

    @Test
    // test case 1.c.9
    public void testGetParsedOptionValueWithStringThatHasLengthOne() throws Exception {
        capability1cSetUp();
        assertEquals(null, cl.getParsedOptionValue("v"));
    }

    @Test
    // test case 1.c.10
    public void testGetParsedOptionWithStringThatHasLengthLargerThanOne() throws Exception {
        capability1cSetUp();
        assertEquals("valuetest", cl.getParsedOptionValue("test"));
    }

}
