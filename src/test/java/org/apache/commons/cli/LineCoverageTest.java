package org.apache.commons.cli;
import static org.apache.commons.cli.Util.EMPTY_STRING_ARRAY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import java.util.Properties;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.junit.Test;
import org.junit.Before;

@SuppressWarnings("deprecation") // tests some deprecated classes
public class LineCoverageTest {
	CommandLine.Builder builder;
	CommandLine cmd;
	Option T;
	@Before
	public void setup() {
		builder = new CommandLine.Builder();
		builder.addArg("foo").addArg("bar");
		T = Option.builder("T").build();
        builder.addOption(T);
	}

    @Test
    public void getArgListReturnsCorrectArgsAsArrayList() {
        List<String> correctArgsList = new ArrayList<String>();
        correctArgsList.add("foo");
        correctArgsList.add("bar");
        cmd = builder.build();
        assertEquals(correctArgsList, cmd.getArgList());
    }
    
    @Test
    public void getArgReturnsCorrectArgsAsArray() {
        String[] correctArgsList = new String[] {"foo", "bar"};
        cmd = builder.build();
        assertArrayEquals(correctArgsList, cmd.getArgs());
    }
    
    @Test 
    public void getOptions() {
    	Option[] correctOptList = new Option[]{T};
        cmd = builder.build();
        assertArrayEquals(correctOptList, cmd.getOptions());
    }
    
    
}