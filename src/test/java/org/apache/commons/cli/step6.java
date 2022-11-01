/*
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package org.apache.commons.cli;

import java.util.Properties;

import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("deprecation") // tests some deprecated classes
public class step6 {

    @Test
    public void testBuilder() {
        final CommandLine.Builder builder = new CommandLine.Builder();
        builder.addArg("foo").addArg("bar");
        builder.addOption(Option.builder("T").build());
        final CommandLine cmd = builder.build();

        assertEquals("foo", cmd.getArgs()[0]);
        assertEquals("bar", cmd.getArgList().get(1));
        assertEquals("T", cmd.getOptions()[0].getOpt());
    }

    @Test
    public void testGetOptionProperties() throws Exception {
        final String[] args = {"-Dparam1=value1", "-Dparam2=value2", "-Dparam3", "-Dparam4=value4", "-D", "--property", "foo=bar"};

        final Options options = new Options();
        options.addOption(OptionBuilder.withValueSeparator().hasOptionalArgs(2).create('D'));
        options.addOption(OptionBuilder.withValueSeparator().hasArgs(2).withLongOpt("property").create());

        final Parser parser = new GnuParser();
        final CommandLine cl = parser.parse(options, args);

        final Properties props = cl.getOptionProperties("D");
        assertNotNull("null properties", props);
        assertEquals("number of properties in " + props, 4, props.size());
        assertEquals("property 1", "value1", props.getProperty("param1"));
        assertEquals("property 2", "value2", props.getProperty("param2"));
        assertEquals("property 3", "true", props.getProperty("param3"));
        assertEquals("property 4", "value4", props.getProperty("param4"));

        assertEquals("property with long format", "bar", cl.getOptionProperties("property").getProperty("foo"));
    }

    @Test
    public void testGetOptionPropertiesWithOption() throws Exception {
        final String[] args = {"-Dparam1=value1", "-Dparam2=value2", "-Dparam3", "-Dparam4=value4", "-D", "--property", "foo=bar"};

        final Options options = new Options();
        final Option optionD = OptionBuilder.withValueSeparator().hasOptionalArgs(2).create('D');
        final Option optionProperty = OptionBuilder.withValueSeparator().hasArgs(2).withLongOpt("property").create();
        options.addOption(optionD);
        options.addOption(optionProperty);

        final Parser parser = new GnuParser();
        final CommandLine cl = parser.parse(options, args);

        final Properties props = cl.getOptionProperties(optionD);
        assertNotNull("null properties", props);
        assertEquals("number of properties in " + props, 4, props.size());
        assertEquals("property 1", "value1", props.getProperty("param1"));
        assertEquals("property 2", "value2", props.getProperty("param2"));
        assertEquals("property 3", "true", props.getProperty("param3"));
        assertEquals("property 4", "value4", props.getProperty("param4"));

        assertEquals("property with long format", "bar", cl.getOptionProperties(optionProperty).getProperty("foo"));
    }

    @Test
    public void testGetOptions() {
        final CommandLine cmd = new CommandLine();
        assertNotNull(cmd.getOptions());
        assertEquals(0, cmd.getOptions().length);

        cmd.addOption(new Option("a", null));
        cmd.addOption(new Option("b", null));
        cmd.addOption(new Option("c", null));

        assertEquals(3, cmd.getOptions().length);
    }

    @Test
    public void testGetParsedOptionValue() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().withType(Number.class).create("i"));
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-i", "123", "-f", "foo"});

        assertEquals(123, ((Number) cmd.getParsedOptionValue("i")).intValue());
        assertEquals("foo", cmd.getParsedOptionValue("f"));
    }

    @Test
    public void testGetParsedOptionValueWithChar() throws Exception {
        final Options options = new Options();
        options.addOption(Option.builder("i").hasArg().type(Number.class).build());
        options.addOption(Option.builder("f").hasArg().build());

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-i", "123", "-f", "foo"});

        assertEquals(123, ((Number) cmd.getParsedOptionValue('i')).intValue());
        assertEquals("foo", cmd.getParsedOptionValue('f'));
    }

    @Test
    public void testGetParsedOptionValueWithOption() throws Exception {
        final Options options = new Options();
        final Option optI = Option.builder("i").hasArg().type(Number.class).build();
        final Option optF = Option.builder("f").hasArg().build();
        options.addOption(optI);
        options.addOption(optF);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-i", "123", "-f", "foo"});

        assertEquals(123, ((Number) cmd.getParsedOptionValue(optI)).intValue());
        assertEquals("foo", cmd.getParsedOptionValue(optF));
    }

    @Test
    public void testNullhOption() throws Exception {
        final Options options = new Options();
        final Option optI = Option.builder("i").hasArg().type(Number.class).build();
        final Option optF = Option.builder("f").hasArg().build();
        options.addOption(optI);
        options.addOption(optF);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-i", "123", "-f", "foo"});
        assertNull(cmd.getOptionValue((Option) null));
        assertNull(cmd.getParsedOptionValue((Option) null));
    }


    @Test
    public void testDeprecatedGetOptionValueFunction(){
        final CommandLine cmd = new CommandLine();

        cmd.addOption(new Option("a", null));
        cmd.getOptionObject("a");
    }

    @Test
    public void testCapability1b1() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-f", "foo"});

        assertEquals(null, cmd.getOptionValue((Option) null, null));
    }

    @Test
    public void testCapability1b2() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-f", "foo"});

        assertEquals("", cmd.getOptionValue((Option) null, ""));
    }

    @Test
    public void testCapability1b3() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-f", "foo"});

        assertEquals("1", cmd.getOptionValue((Option) null, "1"));
    }

    @Test
    public void testCapability1b4() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-f", "foo"});

        assertEquals("13", cmd.getOptionValue((Option) null, "13"));
    }

    @Test
    public void testCapability1b5() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("");
        options.addOption(opt);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"", "foo"});

        assertEquals(null, cmd.getOptionValue("", null));
    }

    @Test
    public void testCapability1b6() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"", "foo"});

        assertEquals("", cmd.getOptionValue("", ""));
    }

    @Test
    public void testCapability1b7() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"", "foo"});

        assertEquals("O", cmd.getOptionValue("", "O"));
    }

    @Test
    public void testCapability1b8() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"", "foo"});

        assertEquals("foo", cmd.getOptionValue("", "foo"));
    }

    @Test
    public void testCapability1b9() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("a");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-a", "foo"});

        assertEquals("foo", cmd.getOptionValue("a", null));
    }

    @Test
    public void testCapability1b10() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("b");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-b", "foo"});

        assertEquals("foo", cmd.getOptionValue("b", ""));
    }

    @Test
    public void testCapability1b11() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("b");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-b", "foo"});

        assertEquals("foo", cmd.getOptionValue("b", "9"));
    }

    @Test
    public void testCapability1b12() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("b");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-b", "foo"});

        assertEquals("foo", cmd.getOptionValue("b", "6784"));
    }

    @Test
    public void testCapability1b13() throws Exception {
        final Options options = new Options();
        final Option opt = OptionBuilder.withValueSeparator().hasArgs(1).withLongOpt("parameter").create();
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-parameter", "foo"});

        assertEquals("foo", cmd.getOptionValue("parameter", null));
    }

    @Test
    public void testCapability1b14() throws Exception {
        final Options options = new Options();
        final Option opt = OptionBuilder.withValueSeparator().hasArgs(1).withLongOpt("parameter").create();
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-parameter", "foo"});

        assertEquals("foo", cmd.getOptionValue("parameter", ""));
    }

    @Test
    public void testCapability1b15() throws Exception {
        final Options options = new Options();
        final Option opt = OptionBuilder.withValueSeparator().hasArgs(1).withLongOpt("ii").create();
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-ii", "foo"});

        assertEquals("foo", cmd.getOptionValue("ii", "2"));
    }

    @Test
    public void testCapability1b16() throws Exception {
        final Options options = new Options();
        final Option opt = OptionBuilder.withValueSeparator().hasArgs(1).withLongOpt("ii").create();
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-ii", "foo"});

        assertEquals("foo", cmd.getOptionValue("ii", "23"));
    }

    @Test
    public void testCapability1b17() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("i");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-i", "foo"});

        assertEquals("foo", cmd.getOptionValue('i', "2345"));
    }

    @Test
    public void testCapability1b18() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("i");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-i", "foo"});

        assertEquals("foo", cmd.getOptionValue('i', null));
    }

    @Test
    public void testCapability1b19() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("b");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-b", "foo"});

        assertEquals("foo", cmd.getOptionValue('b', ""));
    }

    @Test
    public void testCapability1b20() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("B");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-B", "foo"});

        assertEquals("foo", cmd.getOptionValue('B', "1"));
    }

    @Test
    public void testCapability1b21() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("A");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-A", "foo"});

        assertEquals("foo", cmd.getOptionValue('A', "2343"));
    }

    @Test
    public void testCapability1b22() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("z");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-z", "foo"});

        assertEquals("foo", cmd.getOptionValue('z', "345"));
    }

    @Test
    public void testCapability1b23() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("a");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-a", "foo"});

        assertEquals("foo", cmd.getOptionValue('a', "a"));
    }

    @Test
    public void testCapability1b24() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("Z");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-Z", "foo"});

        assertEquals("foo", cmd.getOptionValue('Z', "a"));
    }

    @Test
    public void testCapability1b25() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("y");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-y", "foo"});
        assertEquals("foo", cmd.getOptionValue('y', "a"));
    }

    @Test
    public void testCapability1b30() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("q");
        options.addOption(opt);
        final CommandLine cmd = new CommandLine();

        assertEquals("123", cmd.getOptionValue(opt, "123"));
    }

    @Test
    public void testCapability1b31() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("s");
        options.addOption(opt);

        final CommandLine cmd = new CommandLine();

        assertEquals(null, cmd.getOptionValue(opt, null));
    }

    @Test
    public void testCapability1b32() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("a");
        options.addOption(opt);
        final CommandLine cmd = new CommandLine();

        assertEquals("", cmd.getOptionValue(opt, ""));
    }

    @Test
    public void testCapability1b33() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("z");
        options.addOption(opt);
        final CommandLine cmd = new CommandLine();

        assertEquals("1", cmd.getOptionValue(opt, "1"));
    }

    @Test
    public void testCapability1b34() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("q");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-q", "foo"});

        assertEquals("foo", cmd.getOptionValue(opt, "123"));
    }

    @Test
    public void testCapability1b35() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("s");
        options.addOption(opt);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-s", "foo"});

        assertEquals("foo", cmd.getOptionValue(opt, null));
    }

    @Test
    public void testCapability1b36() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("a");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-a", "foo"});

        assertEquals("foo", cmd.getOptionValue(opt, ""));
    }

    @Test
    public void testCapability1b37() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("z");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-z", "foo"});

        assertEquals("foo", cmd.getOptionValue(opt, "1"));
    }

}
