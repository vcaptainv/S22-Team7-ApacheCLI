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

/**
 * function tested: getOptionValue(opt, defaultValue)
 * Capability tested: 1b
 * @throws Exception
 */

@SuppressWarnings("deprecation") // tests some deprecated classes
public class step6 {

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithNullStringOptionAndNullDefault() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-f", "foo"});

        assertEquals(null, cmd.getOptionValue((String) null, null));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithNullStringOptionAndEmptyDefault() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-f", "foo"});

        assertEquals("", cmd.getOptionValue((String) null, ""));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithNullStringOptionAndDefaultLengthOne() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-f", "foo"});

        assertEquals("1", cmd.getOptionValue((String) null, "1"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithNullStringOptionAndDefaultLengthMoreThanOne() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-f", "foo"});

        assertEquals("13", cmd.getOptionValue((String) null, "13"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithEmptyOptionAndNullDefault() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("");
        options.addOption(opt);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"", "foo"});

        assertEquals(null, cmd.getOptionValue("", null));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithEmptyOptionAndEmptyDefault() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"", "foo"});

        assertEquals("", cmd.getOptionValue("", ""));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithEmptyOptionAndNullDefaultOfLengthOne() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"", "foo"});

        assertEquals("O", cmd.getOptionValue("", "O"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithEmptyOptionAndNullDefaultOfLengthThree() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"", "foo"});

        assertEquals("foo", cmd.getOptionValue("", "foo"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithOptionOfLengthOneAndNullDefault() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("a");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-a", "foo"});

        assertEquals("foo", cmd.getOptionValue("a", null));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithOptionOfLengthOneAndEmptyDefault() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("b");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-b", "foo"});

        assertEquals("foo", cmd.getOptionValue("b", ""));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithOptionOfLengthOneAndDefaultOfLengthOne() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("c");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-c", "foo"});

        assertEquals("foo", cmd.getOptionValue("c", "9"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithOptionOfLengthOneAndDefaultOfLengthFour() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("d");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-d", "foo"});

        assertEquals("foo", cmd.getOptionValue("d", "6784"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithLongOptionAndNullDefault() throws Exception {
        final Options options = new Options();
        final Option opt = OptionBuilder.withValueSeparator().hasArgs(1).withLongOpt("parameter").create();
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-parameter", "foo"});

        assertEquals("foo", cmd.getOptionValue("parameter", null));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithLongOptionAndEmptyDefault() throws Exception {
        final Options options = new Options();
        final Option opt = OptionBuilder.withValueSeparator().hasArgs(1).withLongOpt("time").create();
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-time", "foo"});

        assertEquals("foo", cmd.getOptionValue("time", ""));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithLongOptionAndDefaultOfLengthOne() throws Exception {
        final Options options = new Options();
        final Option opt = OptionBuilder.withValueSeparator().hasArgs(1).withLongOpt("ii").create();
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-ii", "foo"});

        assertEquals("foo", cmd.getOptionValue("ii", "2"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithLongOptionAndDefaultOfLengthTwo() throws Exception {
        final Options options = new Options();
        final Option opt = OptionBuilder.withValueSeparator().hasArgs(1).withLongOpt("interval").create();
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-interval", "foo"});

        assertEquals("foo", cmd.getOptionValue("interval", "23"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithCharOptionAndDefaultOfLengthFour() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("i");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-i", "foo"});

        assertEquals("foo", cmd.getOptionValue('i', "2345"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithCharOptionAndNullDefault() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("v");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-v", "foo"});

        assertEquals("foo", cmd.getOptionValue('v', null));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithCharOptionAndEmptyDefault() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("b");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-b", "foo"});

        assertEquals("foo", cmd.getOptionValue('b', ""));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithCharOptionAndDefaultOfLengthOne() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("y");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-y", "foo"});

        assertEquals("foo", cmd.getOptionValue('y', "1"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithBoundaryCharOptionAndDefaultOfLengthFour() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("a");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-a", "foo"});

        assertEquals("foo", cmd.getOptionValue('a', "2343"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithBoundaryCharOptionAndDefaultOfLengthThree() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("z");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-z", "foo"});

        assertEquals("foo", cmd.getOptionValue('z', "345"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithBoundaryCharOptionAndDefaultOfLengthOne() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("A");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-A", "foo"});

        assertEquals("foo", cmd.getOptionValue('A', "a"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithBoundaryCharOptionAndDefault() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("Z");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-Z", "foo"});

        assertEquals("foo", cmd.getOptionValue('Z', "a"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithCharOptionAndDefault() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("B");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-B", "foo"});
        assertEquals("foo", cmd.getOptionValue('B', "a"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithNullOptionAndNullDefault() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-f", "foo"});

        assertEquals(null, cmd.getOptionValue((Option) null, null));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithNullOptionAndEmptyDefault() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-f", "foo"});

        assertEquals("", cmd.getOptionValue((Option) null, ""));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithNullOptionAndDefaultLengthOne() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-f", "foo"});

        assertEquals("1", cmd.getOptionValue((Option) null, "1"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithNullOptionAndDefaultLengthMoreThanOne() throws Exception {
        final Options options = new Options();
        options.addOption(OptionBuilder.hasArg().create("f"));

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-f", "foo"});

        assertEquals("13", cmd.getOptionValue((Option) null, "13"));
    }
    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithInvalidOptionObjectAndDefaultOfLengthThree() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("q");
        options.addOption(opt);
        final CommandLine cmd = new CommandLine();

        assertEquals("123", cmd.getOptionValue(opt, "123"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithInvalidOptionObjectAndNullDefault() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("s");
        options.addOption(opt);

        final CommandLine cmd = new CommandLine();

        assertEquals(null, cmd.getOptionValue(opt, null));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithInvalidOptionObjectAndEmptyDefault() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("a");
        options.addOption(opt);
        final CommandLine cmd = new CommandLine();

        assertEquals("", cmd.getOptionValue(opt, ""));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithInvalidOptionObjectAndDefaultOfLengthOne() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("z");
        options.addOption(opt);
        final CommandLine cmd = new CommandLine();

        assertEquals("1", cmd.getOptionValue(opt, "1"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithValidOptionObjectAndDefaultOfLengthThree() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("q");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-q", "foo"});

        assertEquals("foo", cmd.getOptionValue(opt, "123"));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithValidOptionObjectAndNullDefault() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("s");
        options.addOption(opt);

        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-s", "foo"});

        assertEquals("foo", cmd.getOptionValue(opt, null));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithValidOptionObjectAndEmptyDefault() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("a");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-a", "foo"});

        assertEquals("foo", cmd.getOptionValue(opt, ""));
    }

    /**
     * related test case:
     * @throws Exception
     */
    @Test
    public void getOptionValueWithValidOptionObjectAndDefaultOfLengthOne() throws Exception {
        final Options options = new Options();
        Option opt = OptionBuilder.hasArg().create("z");
        options.addOption(opt);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, new String[] {"-z", "foo"});

        assertEquals("foo", cmd.getOptionValue(opt, "1"));
    }

}
