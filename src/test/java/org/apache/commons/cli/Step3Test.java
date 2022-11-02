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
	//
	final String[] argsSinglePropertyWithArgs = {"-Xkey1=value1"};
	final String[] argsSinglePropertyOneArg = {"-Xkey3"};
	final String[] argsSinglePropertyWithOnlyOptName = {"-X"};
	final String[] argsSinglePropertyWithLongOpt = {"--property", "keyThisIsKey=ThisIsValue"};
	final String[] argsSingleIntValue = {"-kv1", "321321"};
	final String[] argsSingleStringValue = {"-kv2", "foobar"};
	//	
	final Option nullOption = (Option) null;
	//	These are options with properties and the Option has a short name. Ex) -X
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
        System.out.println("getOptionValuesNullOption");
        System.out.println(Arrays.toString(cl.getOptionValues(nullOption)));
        assertNull(cl.getOptionValues(nullOption));
    }
    
    @Test
    public void getParsedOptionValueWithNullOptionResultsInNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        assertNull(cl.getParsedOptionValue(nullOption));
    }
    /*
     * 
     * Test case 2.11
     * 
     */
    @Test
    public void hasNullOptionReturnsFalse() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        assertEquals(false, cl.hasOption(nullOption));
    }
    
    //	Testing Valid Option
    
    @Test
    public void getOptionPropertiesWithValidOptionContainingShortOptNameAndElementInArgsHasTwoArgumentsReturnsValue() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsSinglePropertyWithArgs);
        //
        final Properties propsShortOpt = cl.getOptionProperties(validOptionWithPropertiesOfOpt);
        //
        assertEquals("value1", propsShortOpt.getProperty("key1"));
    }
    
    @Test
    public void getOptionPropertiesWithValidOptionContainingShortOptNameAndElementInArgsHasOneArgumentsReturnsValue() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsSinglePropertyOneArg);
        //
        final Properties propsShortOpt = cl.getOptionProperties(validOptionWithPropertiesOfOpt);
        //
        assertEquals("true", propsShortOpt.getProperty("key3"));
    }
    
    @Test
    public void getOptionPropertiesWithValidOptionAndOnlyShortOptNameReturnsEmptyMap() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsSinglePropertyWithOnlyOptName);
        //
        final Properties propsShortOpt = cl.getOptionProperties(validOptionWithPropertiesOfOpt);
        // args without the first arg will not be added as option properties 
        //	Ex) if our opt Name is -D and one of the args is ONLY "-D" instead of "-Dkey1=key2", 
        //		it will not add an empty string into the option properties as "-D" is followed by nothing
        assertEquals(Collections.emptyMap(),  propsShortOpt );
    }

    @Test
    public void getOptionPropertiesWithValidOptionAndLongOptNameReturnsValue() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsSinglePropertyWithLongOpt);
        //
        final Properties propsLongOpt = cl.getOptionProperties(validOptionWithPropertiesOfLongOpt);
        // args without the first arg will not be added as option properties 
        //	Ex) if our opt Name is -D and one of the args is ONLY "-D" instead of "-Dkey1=key2", 
        //		it will not add an empty string into the option properties as "-D" is followed by nothing
        assertEquals("ThisIsValue", propsLongOpt.getProperty("keyThisIsKey"));

    }
    
    
    @Test
    public void getOptionValueWithValidOptionWithIntValueReturnsValue() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleIntValue);
        //
        assertEquals("321321", cl.getOptionValue(validOptionWithKeyKV1));
    }
    
    @Test
    public void getOptionValueWithValidOptionWithStringValueReturnsValue() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleStringValue);
        //
        assertEquals("foobar", cl.getOptionValue(validOptionWithKeyKV2));
    }
    
    @Test
    public void getOptionValuesWithValidOptionWithIntValueReturnsValue() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleIntValue);
        //
        //	intArray is the corresponding Option Value with the key "kv1"
        final String[] intArray = new String[]{"321321"};
        assertArrayEquals(intArray, cl.getOptionValues(validOptionWithKeyKV1));
    }
    
    
    @Test
    public void getOptionValuesWithValidOptionWithStringValReturnsValue() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleStringValue);
        //
        //	stringArrayy is the corresponding Option Value with the key "kv2"
        final String[] stringArray = new String[]{"foobar"};
        assertArrayEquals(stringArray, cl.getOptionValues(validOptionWithKeyKV2));
    }
    
    @Test
    public void getParsedOptionValueWithValidOptionWithIntValReturnsValue() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleIntValue);
        //
        assertEquals(321321, ((Number) cl.getParsedOptionValue(validOptionWithKeyKV1)).intValue());
    }
    
    @Test
    public void getParsedOptionValueWithValidOptionWithStringValReturnsValue() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleStringValue);
        //
        assertEquals("foobar", cl.getParsedOptionValue(validOptionWithKeyKV2));
    }
    /*
     * 
     * Test case 2.14
     * 
     */
    @Test
    public void hasValidOptionReturnsTrue() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        System.out.println("hasValidOptionReturnsTrue");
        System.out.println(cl.hasOption(validOptionWithKeyKV1));
        assertEquals(true, cl.hasOption(validOptionWithKeyKV1));
    }
	
    
    //	Testing Invalid Option that cannot be applied to the Arg list 
    //		This means that the Option CAN BE created but does not apply to the Arg List
    
    
    
    /*
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
    */
    
    
    
    @Test
    public void getOptionPropertiesWithInvalidOptionContainingShortOptNameAndElementInArgsHasTwoArgumentsReturnsEmptyMap() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsSinglePropertyWithArgs);
        //
        final Option invalidOptionWithShortOpt = 
        		OptionBuilder.withValueSeparator()
        			.hasOptionalArgs(2)
        			.create('Y');
        final Properties propsShortOpt = cl.getOptionProperties(invalidOptionWithShortOpt);
        //
        assertEquals(Collections.emptyMap(),propsShortOpt);
    }
    
    @Test
    public void getOptionPropertiesWithInvalidOptionContainingShortOptNameAndElementInArgsHasOneArgumentsReturnsEmptyMap() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsSinglePropertyWithArgs);
        //
        final Option invalidOptionWithShortOpt = 
        		OptionBuilder.withValueSeparator()
	    			.hasOptionalArgs(2)
	    			.create('Y'); 
        final Properties propsLongOpt = cl.getOptionProperties(invalidOptionWithShortOpt);
        //
        assertEquals(Collections.emptyMap(),propsLongOpt);
    }
    
    @Test
    public void getOptionPropertiesWithInvalidOptionAndOnlyShortOptNameReturnsEmptyMap() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsSinglePropertyWithOnlyOptName);
        //
        final Option invalidOptionWithShortOpt = 
        		OptionBuilder.withValueSeparator()
        			.hasOptionalArgs(2)
        			.create('Y');
        final Properties propsShortOpt = cl.getOptionProperties(invalidOptionWithShortOpt);
        // args without the first arg will not be added as option properties 
        //	Ex) if our opt Name is -D and one of the args is ONLY "-D" instead of "-Dkey1=key2", 
        //		it will not add an empty string into the option properties as "-D" is followed by nothing
        assertEquals(Collections.emptyMap(),  propsShortOpt );
    } 

    @Test
    public void getOptionPropertiesWithInValidOptionAndLongOptNameReturnsEmptyMap() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsSinglePropertyWithLongOpt);
        //
        final Option invalidOptionWithLongOpt = 
        		OptionBuilder.withValueSeparator()
	    			.hasArgs(2).withLongOpt("longOptName")
	    			.create(); 
        final Properties propsLongOpt = cl.getOptionProperties(invalidOptionWithLongOpt);
        // args without the first arg will not be added as option properties 
        //	Ex) if our opt Name is -D and one of the args is ONLY "-D" instead of "-Dkey1=key2", 
        //		it will not add an empty string into the option properties as "-D" is followed by nothing
        assertEquals(Collections.emptyMap(),  propsLongOpt );
    }
    
    
    @Test
    public void getOptionValueWithInvalidOptionWithIntValueReturnsNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleIntValue);
        //
        final Option invalidOptionWithNotExistingValue = 
        		Option.builder("kvNotExist").hasArg().type(Integer.class).build();
        //
        assertNull(cl.getOptionValue(invalidOptionWithNotExistingValue));
    }
    
    @Test
    public void getOptionValueWithInvalidOptionWithStringValueReturnsNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleStringValue);
        //
        final Option invalidOptionWithNotExistingValue = 
        		Option.builder("kvNotExist").hasArg().type(String.class).build();
        //
        assertNull(cl.getOptionValue(invalidOptionWithNotExistingValue));
    }
    
    
    /*
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
    */
 
    
    
    
    
    @Test
    public void getOptionValuesWithInvalidOptionWithIntValueReturnNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleIntValue);
        //
        final Option invalidOptionWithNotExistingValue = 
        		Option.builder("kvNotExist").hasArg().type(Integer.class).build();
        System.out.println("getOptionValuesInvalidOptionIntValue");
        System.out.println(Arrays.toString(cl.getOptionValues(invalidOptionWithNotExistingValue)));
        assertNull(cl.getOptionValues(invalidOptionWithNotExistingValue));
    }
    
    
    @Test
    public void getOptionValuesWithInValidOptionWithStringValReturnNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleStringValue);
        //
        //
        final Option invalidOptionWithNotExistingValue = 
        		Option.builder("kvNotExist").hasArg().type(String.class).build();
        System.out.println("getOptionValuesInvalidOptionStringValue");
        System.out.println(Arrays.toString(cl.getOptionValues(invalidOptionWithNotExistingValue)));
        assertNull(cl.getOptionValues(invalidOptionWithNotExistingValue));
    }
 
    
    
    @Test
    public void getParsedOptionValueWithInvalidOptionWithIntValReturnNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleIntValue);
        //
        final Option invalidOptionWithNotExistingValue = 
        		Option.builder("kvNotExist").hasArg().type(Integer.class).build();
        assertNull(cl.getParsedOptionValue(invalidOptionWithNotExistingValue));
    }
    
    @Test
    public void getParsedOptionValueWithInvalidOptionWithStringValReturnNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleStringValue);
        //
        final Option invalidOptionWithNotExistingValue = 
        		Option.builder("kvNotExist").hasArg().type(String.class).build();
        assertNull(cl.getParsedOptionValue(invalidOptionWithNotExistingValue));
    }
    
    /*
     * 
     * Test case 2.12
     * 
     */
    @Test
    public void hasInvalidOptionReturnsFalse() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        final Option invalidOptionWithNotExistingValue = 
        		Option.builder("kvNotExist").hasArg().type(String.class).build();
        assertEquals(false, cl.hasOption(invalidOptionWithNotExistingValue));
    }
    
   
    /*
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
    */
    
    
    
    
    
    
    //	Testing Invalid Option that can be created but conflicts with input space constraints
    //		This means that the Option CAN BE created violates input space constraints 
    //		but does not apply to the Arg List
    
    
   
    @Test
    public void getOptionPropertiesWithFaultyOptionContainingShortOptNameAndElementInArgsHasTwoArgumentsReturnsEmptyMap() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, new String[] {"power"});
        //
        final Option faultyOptionWithShortOpt = 
        		OptionBuilder.withValueSeparator()
        			.hasOptionalArgs(2)
        			.create("321");
        final Properties propsShortOpt = cl.getOptionProperties(faultyOptionWithShortOpt);
        //
        assertEquals(Collections.emptyMap(),propsShortOpt);
    }
    
    
     
    @Test
    public void getOptionPropertiesWithFaultyOptionContainingShortOptNameAndElementInArgsHasOneArgumentsReturnsEmptyMap() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsSinglePropertyWithArgs);
        //
        final Option faultyOptionWithShortOpt = 
        		OptionBuilder.withValueSeparator()
	    			.hasOptionalArgs(2)
	    			.create("321"); 
        final Properties propsLongOpt = cl.getOptionProperties(faultyOptionWithShortOpt);
        //
        assertEquals(Collections.emptyMap(),propsLongOpt);
    }
    
    @Test
    public void getOptionPropertiesWithFaultyOptionAndOnlyShortOptNameReturnsEmptyMap() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsSinglePropertyWithOnlyOptName);
        //
        final Option faultyOptionWithShortOpt = 
        		OptionBuilder.withValueSeparator()
        			.hasOptionalArgs(2)
        			.create("321");
        final Properties propsShortOpt = cl.getOptionProperties(faultyOptionWithShortOpt);
        // args without the first arg will not be added as option properties 
        //	Ex) if our opt Name is -D and one of the args is ONLY "-D" instead of "-Dkey1=key2", 
        //		it will not add an empty string into the option properties as "-D" is followed by nothing
        assertEquals(Collections.emptyMap(),  propsShortOpt );
    } 
    
    @Test
    public void getOptionPropertiesWithFaultyOptionAndLongOptNameReturnsEmptyMap() throws Exception {
    	options.addOption(validOptionWithPropertiesOfOpt);
        options.addOption(validOptionWithPropertiesOfLongOpt);
        final CommandLine cl = parser.parse(options, argsSinglePropertyWithLongOpt);
        //
        final Option faultyOptionWithLongOpt = 
        		OptionBuilder.withValueSeparator()
	    			.hasArgs(2).withLongOpt("")
	    			.create(); 
        final Properties propsLongOpt = cl.getOptionProperties(faultyOptionWithLongOpt);
        // args without the first arg will not be added as option properties 
        //	Ex) if our opt Name is -D and one of the args is ONLY "-D" instead of "-Dkey1=key2", 
        //		it will not add an empty string into the option properties as "-D" is followed by nothing
        assertEquals(Collections.emptyMap(),  propsLongOpt );
    }
    
    @Test
    public void getOptionValueWithFaultyOptionWithIntValueReturnsNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleIntValue);
        //
        final Option faultyOptionWithNotExistingValue = 
        		Option.builder("").hasArg().type(Integer.class).build();
        //
        assertNull(cl.getOptionValue(faultyOptionWithNotExistingValue));
    }
    
    @Test
    public void getOptionValueWithFaultyOptionWithStringValueReturnsNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleStringValue);
        //
        final Option faultyOptionWithNotExistingValue = 
        		Option.builder("").hasArg().type(String.class).build();
        //
        assertNull(cl.getOptionValue(faultyOptionWithNotExistingValue));
    }
    
 
    
    
    
    
    @Test
    public void getOptionValuesWithFaultyOptionWithIntValueReturnNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleIntValue);
        //
        final Option faultyOptionWithNotExistingValue = 
        		Option.builder("").hasArg().type(Integer.class).build();
        System.out.println("getOptionValuesFaultyOptionIntValue");
        System.out.println(Arrays.toString(cl.getOptionValues(faultyOptionWithNotExistingValue)));
        assertNull(cl.getOptionValues(faultyOptionWithNotExistingValue));
    }
    
    
    @Test
    public void getOptionValuesWithFaultyOptionWithStringValReturnNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleStringValue);
        //
        //
        final Option faultyOptionWithNotExistingValue = 
        		Option.builder("").hasArg().type(String.class).build();
        System.out.println("getOptionValuesFaultyOptionStringValue");
        System.out.println(Arrays.toString(cl.getOptionValues(faultyOptionWithNotExistingValue)));
        assertNull(cl.getOptionValues(faultyOptionWithNotExistingValue));
    }
 
    
    
    @Test
    public void getParsedOptionValueWithFaultyOptionWithIntValReturnNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleIntValue);
        //
        final Option faultyOptionWithNotExistingValue = 
        		Option.builder("").hasArg().type(Integer.class).build();
        assertNull(cl.getParsedOptionValue(faultyOptionWithNotExistingValue));
    }
    
    @Test
    public void getParsedOptionValueWithFaultyOptionWithStringValReturnNull() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsSingleStringValue);
        //
        final Option faultyOptionWithNotExistingValue = 
        		Option.builder("").hasArg().type(String.class).build();
        assertNull(cl.getParsedOptionValue(faultyOptionWithNotExistingValue));
    }
    /*
     * 
     * Test case 2.13
     * 
     */
    @Test
    public void hasFaultyOptionReturnsFalse() throws Exception {
    	options.addOption(validOptionWithKeyKV1);
        options.addOption(validOptionWithKeyKV2);
        final CommandLine cl = parser.parse(options, argsValue);
        final Option faultyOptionWithNotExistingValue = 
        		Option.builder("").hasArg().type(String.class).build();
        assertEquals(false, cl.hasOption(faultyOptionWithNotExistingValue));
    }
    
    

    
}
