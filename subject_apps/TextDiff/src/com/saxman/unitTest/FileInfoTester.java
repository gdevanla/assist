package com.saxman.unitTest;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.saxman.textdiff.FileInfo;
import com.saxman.textdiff.LineBlock;
import com.saxman.textdiff.LineInfo;

public class FileInfoTester 
{
	private FileInfo lInfo;
	@Before
	public void setUp(){
		lInfo=new FileInfo(new String[] { "one", "two", "three" } );
		  lInfo.lineInfo = new LineInfo[3];
		  lInfo.lineInfo[0] = new LineInfo( LineInfo.UNIQUEMATCH, 0, 0, 0 );
	      lInfo.lineInfo[1] = new LineInfo( LineInfo.OLDONLY, 1, 1, 1 );
	      lInfo.lineInfo[2] = new LineInfo( LineInfo.OLDONLY, 2, 2, 1 );
	      
	}
	
	@Test
    public void testBlock()
    {
        //FileInfo lInfo = new FileInfo( new String[] { "one", "two", "three" } );
		System.out.println(lInfo.lineNum);
        LineBlock lBlock = lInfo.nextBlock();
        System.out.println(lBlock);
        Assert.assertEquals(1, lInfo.lineNum);
        Assert.assertEquals(lInfo.currentLineInfo(), lInfo.lineInfo[1]);
        Assert.assertEquals( 1, lBlock.lines.length );
        lBlock = lInfo.nextBlock();
        Assert.assertEquals(3, lInfo.lineNum);
        Assert.assertEquals( 2, lBlock.lines.length );
        lBlock = lInfo.nextBlock();
        Assert.assertEquals(3, lInfo.lineNum);
        
        Assert.assertNull( lBlock );
    }
	
	@Test
	public void testisValidLineNumber(){
		
		Assert.assertFalse(lInfo.isValidLineNum(-1));
		Assert.assertTrue(lInfo.isValidLineNum(0));
		Assert.assertFalse(lInfo.isValidLineNum(4));
		Assert.assertFalse(lInfo.isValidLineNum(3));
		
	}
	
    // -------------- Housekeeping -----------
   /* public FileInfoTester(java.lang.String testName)
    {
        super( testName );
    }

    public void setUp()
    {
    }

    public static void main(java.lang.String[] args)
    {
        junit.textui.TestRunner.run( suite() );
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite( FileInfoTester.class );
        return suite;
    }
*/
}
