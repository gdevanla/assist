package net.n3.nanoxml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;


public class StdXMLReaderTest {

	private StdXMLReader target;
	@Before
	public void setUp() throws Exception
	{
		target=new StdXMLReader(new FileInputStream("TestInputFiles/test.xml"));
	}
	
	@Test
	public void testValidEncoding()
	{
		String encoding=target.getEncoding("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
		assertEquals("ISO-8859-1",encoding);
	}
	
	@Test
	public void testEncodingNull() throws Exception
	{
		String encoding=target.getEncoding("<?version=\"1.0\" encoding=\"ISO-8859-1\"?>");		
		assertNull(encoding);
		encoding=target.getEncoding("<?xml?>");		
		assertNull(encoding);
		encoding=target.getEncoding("<?xml version=\"1.0\" encoding23=\"ISO-8859-1\"?>");
		assertNull(encoding);
		encoding=target.getEncoding("<?xml");		
		assertNull(encoding);
	}
	
	@Test
	public void testStringReader()
	{
		String str = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\r\n" + 
				"<!DOCTYPE note SYSTEM \"Note.dtd\">\r\n" + 
				"<note>\r\n" + 
				"<to>Tove</to>\r\n" + 
				"<from>Jani</from>\r\n" + 
				"<heading>Reminder</heading>\r\n" + 
				"<body>Don't forget me this weekend!</body>\r\n" + 
				"</note>";
		assertTrue(target.stringReader(str) instanceof StdXMLReader);
	}
	
	@Test
	public void testFileReader() throws Exception
	{
		IXMLReader r=target.fileReader("TestInputFiles/test.xml");
		assertEquals(1,r.getLineNr());
		assertEquals("file:TestInputFiles/test.xml",r.getSystemID());
		assertEquals("",r.getPublicID());
		assertFalse(r.atEOF());
		assertEquals(0,r.getStreamLevel());
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testFileReaderFileNotFound() throws Exception
	{		
			IXMLReader r=target.fileReader("TestInputFiles/FileNotFound.xml");
	}
	
	@Test
	public void testReaderPublicIDandSystemID() throws Exception
	{
		target=new StdXMLReader(null,"TestInputFiles/encoding.xml");
		assertEquals("file:TestInputFiles/encoding.xml",target.getSystemID());
		assertNull(target.getPublicID());
		assertEquals(0,target.getStreamLevel());
		assertEquals(1,target.getLineNr());
				
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testFileNotFound() throws Exception
	{
		target=new StdXMLReader(null,"E://encoding.xml");
		fail("Should throws file not found Exception");
		
	}

	@Test(expected=MalformedURLException.class)
	public void testMalformedURL() throws Exception
	{
		IXMLReader r=target.fileReader("C:/Users/zayin/Desktop/test.xml");
		fail("should throw MalFormed URL EXCEPTION");
	}
	
	
}
