package org.sat4j.tools;

import static org.mockito.Mockito.mock;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

import junit.framework.TestCase;

import org.sat4j.specs.ISolver;

public class FileBasedVisualizationToolTest extends TestCase{
	private FileBasedVisualizationTool tool;
	private ISolver solver;
	
 	public FileBasedVisualizationToolTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.tool = new FileBasedVisualizationTool("example");
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testGetFilename() throws Exception {
    	assertEquals("example", tool.getFilename());
    }
    
    public void testSetFilename() throws Exception {
    	tool.setFilename("sample");
    	tool.init();
    	assertEquals("sample", tool.getFilename());
    }
    
    public void testAddPoint() throws Exception {
    	tool.addPoint(2.0, 2.0);
    	assertEquals("example", tool.getFilename());
    }
    
    public void testAddInvisiblePoint() throws Exception {
    	tool.addInvisiblePoint(2.0, 2.0);
    	assertEquals("example", tool.getFilename());
    }
    
    public void testEnd() throws Exception {
    	tool.end();
    }
}
