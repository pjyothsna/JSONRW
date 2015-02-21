package com.comcast.json;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MemeLulzTest {

	String inputParamFile;
	InputStream paramFile;
	String env;
	Properties prop;
	String[] fileTypes;
	String jsonfile;
	String wrongjsonfile;
	String emptyjson;
	String Invalidjson;
	MemeLulzScore memeTest;
	@Before
	public void setUp() throws Exception {
			
		//inputParamFile = System.getProperty("/input.properties");
		//paramFile = new FileInputStream(inputParamFile);
		InputStream paramFile = MemeLulzTest.class.getResourceAsStream("/input.properties");		
		prop = new Properties();
		prop.load(paramFile);	
		jsonfile = prop.getProperty("jsonfile");
		wrongjsonfile = prop.getProperty("wrongjsonfile");
		emptyjson = prop.getProperty("emptyjson");
		Invalidjson = prop.getProperty("Invalidjson");
		
		memeTest = new MemeLulzScore();
				
	}

	@Test
	public void validategetAbsPathFromSysParam() {
		String abspath = memeTest.getAbsPathFromSysParam("jsonfile");
		assertNotNull(abspath);
		assertEquals(abspath, jsonfile);
	}
	
	@Test
	public void validategetAbsPath() throws URISyntaxException {
		String abspath = memeTest.getAbsPath("jsonfile");
		assertNotNull(abspath);
		assertEquals(abspath, jsonfile);
	}
	
	@Test
	public void validateFileSize() {
		Boolean size = memeTest.checkFileSize(jsonfile);	
		assertEquals(size, true);
	}
	
	//Negative Test case
	
	@Test
	public void negvalidateFileSize() {
		Boolean size = memeTest.checkFileSize(emptyjson);	
		assertEquals(size, false);
	}
	
	@Test
	public void negvalidateFileNotFound() {
		Boolean size = memeTest.checkFileSize(wrongjsonfile);	
		assertEquals(size, false);
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
