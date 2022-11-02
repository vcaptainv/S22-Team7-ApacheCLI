package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class CommandLineCapabilityOneATest {

    private CommandLine cl;

    @Before
    public void setUp() throws Exception {
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
    //     final Properties props = cl.getOptionProperties((String) null);
    //     assertNotNull("null properties", props);
    //     assertEquals("number of properties in " + props, 0, props.size());
    // }

    @Test
    // test case 1.a.2
    public void testGetOptionPropertiesWithEmptyString() throws Exception {
        final Properties props = cl.getOptionProperties("");
        assertNotNull("null properties", props);
        assertEquals("number of properties in " + props, 0, props.size());
    }

    @Test
    // test case 1.a.3
    public void testGetOptionPropertiesWithStringThatHasLengthOne() throws Exception {
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
        final Properties props = cl.getOptionProperties("property");
        assertNotNull("null properties", props);
        assertEquals("number of properties in " + props, 1, props.size());
        assertEquals("property foo", "bar", props.getProperty("foo"));
    }
}