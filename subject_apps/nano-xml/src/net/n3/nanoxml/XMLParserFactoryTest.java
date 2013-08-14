package net.n3.nanoxml;

import org.junit.Before;
import org.junit.Test;


public class XMLParserFactoryTest {
private XMLParserFactory target;
	@Before
	public void setUp()
	{
		target=new XMLParserFactory();
	}
	
	@Test(expected=ClassNotFoundException.class)
	public void testCreateXMLParserClassNotFound() throws Exception
	{
		target.createXMLParser("Test",new StdXMLBuilder());
		
	}
	
	@Test(expected=InstantiationException.class)
	public void testCreateXMLParserInstanceException() throws Exception
	{
		String className="net.n3.nanoxml.IXMLParser";
		target.createXMLParser(className,new StdXMLBuilder());
		
	}
}
