package com.saxman.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


public class TextFileInTest {

	TextFileIn target = null;

	TextFileIn forException=null;
	@Rule
	public TemporaryFolder folder=new TemporaryFolder();
	
	private File file1;
	private File file2;
	private File file3;
	
	@Before
	public void setUp() {
		try {
			
			file1 = folder.newFile("Test1.txt");
	        BufferedWriter outFile1 = new BufferedWriter(new FileWriter(file1));
	        outFile1.write("Hello\n");
	        outFile1.write("world\n");
	        outFile1.write("first program\n");
	        outFile1.close();
		    
		    
	 		file2 = folder.newFile("Test2.txt");
	        BufferedWriter outFile2 = new BufferedWriter(new FileWriter(file2));
	        outFile2.write("");
	        outFile2.close();
	        
	      //  file3 = folder.newFile("NoFileFound.txt");
	        
	        
		} catch (Exception e) {
		}
	}

	@Test
	public void testasArray() {
		try {
			target = new TextFileIn(file1);
			String[] fileLines = target.asArray();
			Assert.assertEquals(3, fileLines.length);
		} catch (Exception e) {
		}
	}

	@Test
	public void testasArrayEmpty() {
		try {
			target = new TextFileIn(file2);
			String[] fileLines = target.asArray();
			Assert.assertEquals(0, fileLines.length);
		} catch (Exception e) {
		}
	}

	@Test
	public void testFileNotFound() throws FileNotFoundException {
		try {
			target = new TextFileIn(file3);
			Assert.fail("should throw file not found exception");
		} catch (Exception e) {
			//System.out.println(e);
			}
	}

	@Test
	public void testasArrayIOException() throws IOException {
		try {
			forException.asArray();
			Assert.fail("should throw IOException");
		} catch (Exception e) {
			//System.out.println(e);
		}
	}
	

}
