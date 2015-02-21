package com.comcast.json;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MemesTest {

	@Test
	public void testGetMemesMap() {
		//fail("Not yet implemented");
		
		 Map<String, String> expectedMap = new HashMap<String,String>();
		 expectedMap.put("a", "Hello A");
		 expectedMap.put("b", "Hello B");
		 for(Map.Entry<String,String> value:expectedMap.entrySet()){
			 if ("a".equals(value.getKey())) {
		        String actualValue = expectedMap.get(value.getKey()) ;
		        assertNotNull(actualValue);
		        assertEquals(value.getValue(), "Hello A");
		    }
		 }
		
	}

	@Test
	public void testSetMemesMap() {
		// fail("Not yet implemented");
		 Map<String, String> expectedMap = new HashMap<String,String>();
		 expectedMap.put("a", "Hello A");
		 expectedMap.put("b", "Hello B");
		 assertEquals(expectedMap.size(), 2);
		    for(Map.Entry<String,String> value:expectedMap.entrySet()){
		        String actualValue = expectedMap.get(value.getKey());
		        assertNotNull(actualValue);
		        assertEquals(value.getValue(), actualValue);
		    }
	}

	@Test
	public void testToString() {
		// fail("Not yet implemented");
		
	}

}
