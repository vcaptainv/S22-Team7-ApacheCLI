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
import static org.apache.commons.cli.Util.EMPTY_STRING_ARRAY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import java.util.Properties;
import java.util.Collections;
import java.util.Arrays;
import org.junit.Test;
import org.junit.Before;

@SuppressWarnings("deprecation") // tests some deprecated classes
public class Step3Test {

	//	Option rows 
	Option case3b1;
	CommandLine cl;
	
	
	//
	final String[] argsProperty = {"-Xkey1=value1", "-Xkey2=value2", "-Xkey3", "-Xkey4=value4", "-X", "--property", "keyThisIsKey=ThisIsValue"};
	final String[] argsValue = {"-kv1", "321321", "-kv2", "foobar"};
	
	final Option nullOption = (Option) null;
	//	These are options with properties and the Option has a short name. Ex) -D
    final Option validOptionWithPropertiesOfOpt = 
    		OptionBuilder.withValueSeparator()
    			.hasOptionalArgs(2)
    			.create('X');
//	These are options with properties and the Option has a long option Name. Ex) --property
    final Option validOptionWithPropertiesOfLongOpt = 
    		OptionBuilder.withValueSeparator()
	    		.hasArgs(2).withLongOpt("property")
	    		.create(); 
    final Option validOptionWithKeyKV1 = 
    		Option.builder("kv1").hasArg().type(Number.class).build();
    final Option validOptionWithKeyKV2 = 
    		Option.builder("kv2").hasArg().type(String.class).build();
    //
    Options options;
    Parser parser;
	@Before
	public void setup() throws Exception{
		//
		options = new Options();
		parser = new GnuParser();
		
	}
	
	//	Testing with Null Option
	
    @Test
    public void getOptionPropertiesWithNullOptionResultsInEmptyMap() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsProperty);
        assertEquals(Collections.emptyMap(), cl.getOptionProperties(nullOption));
    }
    
    @Test
    public void getOptionValueWithNullOptionResultsInNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        assertNull(cl.getOptionValue(nullOption));
    }
    
    @Test
    public void getOptionValuesWithNullOptionResultsInNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        assertNull(cl.getOptionValues(nullOption));
    }
    
    @Test
    public void getParsedOptionValueWithNullOptionResultsInNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        assertNull(cl.getParsedOptionValue(nullOption));
    }
    
    @Test
    public void hasNullOptionReturnsFalse() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        assertEquals(false, cl.hasOption(nullOption));
    }
    
    //	Testing Valid Option
    
    @Test
    public void getOptionPropertiesWithValidOption() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsProperty);
        //
        final Properties propsShortOpt = cl.getOptionProperties(validOptionWithPropertiesOfOpt);
        final Properties propsLongOpt = cl.getOptionProperties(validOptionWithPropertiesOfLongOpt);
        //
        assertNotNull(propsShortOpt);
        assertNotNull(propsLongOpt);
        assertEquals( 4, propsShortOpt.size());
        assertEquals("value1", propsShortOpt.getProperty("key1"));
        assertEquals( "value2", propsShortOpt.getProperty("key2"));
        assertEquals( "true", propsShortOpt.getProperty("key3"));
        assertEquals( "value4", propsShortOpt.getProperty("key4"));
        //
        assertEquals("ThisIsValue", propsLongOpt.getProperty("keyThisIsKey"));
    }
    
    @Test
    public void getOptionValueWithValidOption() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        //
        assertEquals("321321", cl.getOptionValue(validOptionWithKeyKV1));
        assertEquals("foobar", cl.getOptionValue(validOptionWithKeyKV2));
    }
    
    @Test
    public void getOptionValuesWithValidOption() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        //
        //	intArray is the corresponding Option Value with the key "kv1"
        final String[] intArray = new String[]{"321321"};
        //	stringArrayy is the corresponding Option Value with the key "kv2"
        final String[] stringArray = new String[]{"foobar"};
        assertArrayEquals(intArray, cl.getOptionValues(validOptionWithKeyKV1));
        assertArrayEquals(stringArray, cl.getOptionValues(validOptionWithKeyKV2));
    }
    
    @Test
    public void getParsedOptionValueWithValidOption() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        //
        assertEquals(321321, ((Number) cl.getParsedOptionValue(validOptionWithKeyKV1)).intValue());
        assertEquals("foobar", cl.getParsedOptionValue(validOptionWithKeyKV2));
    }
    
    @Test
    public void hasValidOptionReturnsTrue() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        assertEquals(true, cl.hasOption(validOptionWithKeyKV1));
    }
	
    
    //	Testing Invalid Option
    
    @Test
    public void getOptionPropertiesWithInvalidOption() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsProperty);
        //
        final Option invalidOptionWithShortOpt = 
        		OptionBuilder.withValueSeparator()
        			.hasOptionalArgs(2)
        			.create('Y');
        final Option invalidOptionWithLongOpt = 
        		OptionBuilder.withValueSeparator()
	    			.hasArgs(2).withLongOpt("veryLongOptName")
	    			.create(); 
        final Properties propsShortOpt = cl.getOptionProperties(invalidOptionWithShortOpt);
        final Properties propsLongOpt = cl.getOptionProperties(invalidOptionWithLongOpt);
        //
        assertEquals(Collections.emptyMap(),propsShortOpt);
        assertEquals(Collections.emptyMap(),propsLongOpt);
    }
    
    @Test
    public void getOptionValueWithInvalidOption() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        //
        final Option invalidOptionWithNotExistingValue = 
        		Option.builder("kvNotExist").hasArg().type(String.class).build();
        //
        assertNull(cl.getOptionValue(invalidOptionWithNotExistingValue));
    }
    
    @Test
    public void getOptionValuesWithInvalidOption() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        //
        final Option invalidOptionWithNotExistingValue = 
        		Option.builder("kvNotExist").hasArg().type(String.class).build();
        //
        assertNull(cl.getOptionValues(invalidOptionWithNotExistingValue));
    }
    
    @Test
    public void getParsedOptionValueWithInvalidOption() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        //
        final Option invalidOptionWithNotExistingValue = 
        		Option.builder("kvNotExist").hasArg().type(String.class).build();
        //
        assertNull(cl.getParsedOptionValue(invalidOptionWithNotExistingValue));
    }
    
    @Test
    public void hasInvalidOptionReturnsTrue() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        //
        final Option invalidOptionWithNotExistingValue = 
        		Option.builder("kvNotExist").hasArg().type(String.class).build();
        //
        assertEquals(false, cl.hasOption(invalidOptionWithNotExistingValue));
    }
    

    
}
