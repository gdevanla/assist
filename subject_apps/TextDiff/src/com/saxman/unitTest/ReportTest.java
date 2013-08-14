package com.saxman.unitTest;

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

import com.saxman.textdiff.FileInfo;
import com.saxman.textdiff.LineInfo;
import com.saxman.textdiff.Report;
import com.saxman.util.TextFileIn;


public class ReportTest 
{
	
	@Rule
	public TemporaryFolder folder=new TemporaryFolder();
	
	private File oldFile;
	private File newFile;
	
	
	private FileInfo oldFileInfo;//=new FileInfo(new String[] { "one", "two", "three" } );
	private FileInfo newFileInfo;//=new FileInfo(new String[] { "one", "two", "four" } );
	
	private Report target;
	
	@Before
	public void setUp() throws IOException{
			oldFile = folder.newFile("oldFile.txt");
	        newFile = folder.newFile("newFile.txt");
	       
	}
	
	@Test
	public void testAppendCommandReport() throws IOException{
		
		
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("one\n");
	    outNewFile.write("two\n");
	    outNewFile.close();
	        
		String[] lOld = new TextFileIn( oldFile ).asArray();
        String[] lNew = new TextFileIn( newFile ).asArray();
        oldFileInfo=new FileInfo(lOld);
		newFileInfo=new FileInfo(lNew);
		oldFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		newFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		newFileInfo.lineInfo[1]=new LineInfo(LineInfo.NEWONLY,-1,1,1);
		target=new Report(oldFileInfo,newFileInfo);
		Assert.assertFalse(oldFileInfo.lineInfo[0].isEOF());
		Assert.assertFalse(newFileInfo.lineInfo[0].isNewOnly());
		Assert.assertTrue(newFileInfo.lineInfo[1].isNewOnly());
		Assert.assertEquals("Match",target.getCommand(0).command);
		Assert.assertEquals("Append",target.getCommand(1).command);
		
		
		oldFileInfo=new FileInfo(new String[] {"two"} );
		newFileInfo=new FileInfo(new String[] {"two"} );
		oldFileInfo.lineInfo[0]=new LineInfo(LineInfo.EOF,0,0,0);
		newFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,1,1,1);
		target=new Report(oldFileInfo,newFileInfo);
		Assert.assertTrue(oldFileInfo.lineInfo[0].isEOF());
		Assert.assertFalse(newFileInfo.lineInfo[0].isNewOnly());
		
		oldFileInfo=new FileInfo(new String[] {"one"} );
		newFileInfo=new FileInfo(new String[] {"one","two"} );
		oldFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		newFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		newFileInfo.lineInfo[1]=new LineInfo(LineInfo.NEWONLY,-1,1,1);
		target=new Report(oldFileInfo,newFileInfo);
		Assert.assertFalse(oldFileInfo.lineInfo[0].isEOF());
		Assert.assertFalse(newFileInfo.lineInfo[0].isNewOnly());
		Assert.assertTrue(newFileInfo.lineInfo[1].isNewOnly());
		Assert.assertEquals("Match",target.getCommand(0).command);
		Assert.assertEquals("Append",target.getCommand(1).command);
	}

	@Test
	public void testChangeCommandReport() throws IOException{
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("two\n");
	    outNewFile.close();
	        
		String[] lOld = new TextFileIn( oldFile ).asArray();
        String[] lNew = new TextFileIn( newFile ).asArray();
        oldFileInfo=new FileInfo(lOld);
		newFileInfo=new FileInfo(lNew);
		oldFileInfo.lineInfo[0]=new LineInfo(LineInfo.OLDONLY,0,0,0);
		newFileInfo.lineInfo[0]=new LineInfo(LineInfo.NEWONLY,0,0,0);
		target=new Report(oldFileInfo,newFileInfo);
		Assert.assertTrue(oldFileInfo.lineInfo[0].isOldOnly());
		Assert.assertTrue(newFileInfo.lineInfo[0].isNewOnly());
		Assert.assertEquals("Change",target.getCommand(0).command);
		
		oldFileInfo=new FileInfo(new String[] { "one",} );
		newFileInfo=new FileInfo(new String[] { "two",} );
		oldFileInfo.lineInfo[0]=new LineInfo(LineInfo.OLDONLY,0,0,0);
		newFileInfo.lineInfo[0]=new LineInfo(LineInfo.NEWONLY,0,0,0);
		target=new Report(oldFileInfo,newFileInfo);
		Assert.assertTrue(oldFileInfo.lineInfo[0].isOldOnly());
		Assert.assertTrue(newFileInfo.lineInfo[0].isNewOnly());
		Assert.assertEquals("Change",target.getCommand(0).command);
	}
	@Test
		public void testInsertCommandReport() throws IOException{
		
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("three\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("one\n");
	    outNewFile.write("two\n");
	    outNewFile.write("three\n");
	    outNewFile.close();
	        
		String[] lOld = new TextFileIn( oldFile ).asArray();
        String[] lNew = new TextFileIn( newFile ).asArray();
        oldFileInfo=new FileInfo(lOld);
		newFileInfo=new FileInfo(lNew);
		oldFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		oldFileInfo.lineInfo[1]=new LineInfo(LineInfo.UNIQUEMATCH,1,2,2);
		newFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		newFileInfo.lineInfo[1]=new LineInfo(LineInfo.NEWONLY,0,1,1);
		newFileInfo.lineInfo[2]=new LineInfo(LineInfo.UNIQUEMATCH,1,2,2);
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{oldFileInfo.lineInfo[0].isMatch(),oldFileInfo.lineInfo[0].isOldOnly(),oldFileInfo.lineInfo[0].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{oldFileInfo.lineInfo[1].isMatch(),oldFileInfo.lineInfo[1].isOldOnly(),oldFileInfo.lineInfo[1].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{newFileInfo.lineInfo[0].isMatch(),newFileInfo.lineInfo[0].isOldOnly(),newFileInfo.lineInfo[0].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{false,false,true},new Boolean[]{newFileInfo.lineInfo[1].isMatch(),newFileInfo.lineInfo[1].isOldOnly(),newFileInfo.lineInfo[1].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{newFileInfo.lineInfo[2].isMatch(),newFileInfo.lineInfo[2].isOldOnly(),newFileInfo.lineInfo[2].isNewOnly()});
		target=new Report(oldFileInfo,newFileInfo);
		Assert.assertEquals("Match",target.getCommand(0).command);
		Assert.assertEquals("Insert before",target.getCommand(1).command);
		Assert.assertEquals("Match",target.getCommand(2).command);
		
		oldFileInfo=new FileInfo(new String[] { "one","three"} );
		newFileInfo=new FileInfo(new String[] { "one","two","three"} );
		oldFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		oldFileInfo.lineInfo[1]=new LineInfo(LineInfo.UNIQUEMATCH,1,2,2);
		newFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		newFileInfo.lineInfo[1]=new LineInfo(LineInfo.NEWONLY,0,1,1);
		newFileInfo.lineInfo[2]=new LineInfo(LineInfo.UNIQUEMATCH,1,2,2);
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{oldFileInfo.lineInfo[0].isMatch(),oldFileInfo.lineInfo[0].isOldOnly(),oldFileInfo.lineInfo[0].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{oldFileInfo.lineInfo[1].isMatch(),oldFileInfo.lineInfo[1].isOldOnly(),oldFileInfo.lineInfo[1].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{newFileInfo.lineInfo[0].isMatch(),newFileInfo.lineInfo[0].isOldOnly(),newFileInfo.lineInfo[0].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{false,false,true},new Boolean[]{newFileInfo.lineInfo[1].isMatch(),newFileInfo.lineInfo[1].isOldOnly(),newFileInfo.lineInfo[1].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{newFileInfo.lineInfo[2].isMatch(),newFileInfo.lineInfo[2].isOldOnly(),newFileInfo.lineInfo[2].isNewOnly()});
		target=new Report(oldFileInfo,newFileInfo);
		Assert.assertEquals("Match",target.getCommand(0).command);
		Assert.assertEquals("Insert before",target.getCommand(1).command);
		Assert.assertEquals("Match",target.getCommand(2).command);
	}
	
	@Test
	public void testDeleteCommand() throws IOException{
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("one\n");
	    outNewFile.close();
	        
		String[] lOld = new TextFileIn( oldFile ).asArray();
        String[] lNew = new TextFileIn( newFile ).asArray();
        oldFileInfo=new FileInfo(lOld);
		newFileInfo=new FileInfo(lNew);
		oldFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		oldFileInfo.lineInfo[1]=new LineInfo(LineInfo.OLDONLY,0,1,1);
		newFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		target=new Report(oldFileInfo,newFileInfo);
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{oldFileInfo.lineInfo[0].isMatch(),oldFileInfo.lineInfo[0].isOldOnly(),oldFileInfo.lineInfo[0].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{false,true,false},new Boolean[]{oldFileInfo.lineInfo[1].isMatch(),oldFileInfo.lineInfo[1].isOldOnly(),oldFileInfo.lineInfo[1].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{newFileInfo.lineInfo[0].isMatch(),newFileInfo.lineInfo[0].isOldOnly(),newFileInfo.lineInfo[0].isNewOnly()});
		Assert.assertEquals("Match",target.getCommand(0).command);
		Assert.assertEquals("Delete",target.getCommand(1).command);
	
		
		oldFileInfo=new FileInfo(new String[] {"one","two"} );
		newFileInfo=new FileInfo(new String[] { "one"} );
		oldFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		oldFileInfo.lineInfo[1]=new LineInfo(LineInfo.OLDONLY,0,1,1);
		newFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		target=new Report(oldFileInfo,newFileInfo);
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{oldFileInfo.lineInfo[0].isMatch(),oldFileInfo.lineInfo[0].isOldOnly(),oldFileInfo.lineInfo[0].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{false,true,false},new Boolean[]{oldFileInfo.lineInfo[1].isMatch(),oldFileInfo.lineInfo[1].isOldOnly(),oldFileInfo.lineInfo[1].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{newFileInfo.lineInfo[0].isMatch(),newFileInfo.lineInfo[0].isOldOnly(),newFileInfo.lineInfo[0].isNewOnly()});
		Assert.assertEquals("Match",target.getCommand(0).command);
		Assert.assertEquals("Delete",target.getCommand(1).command);
	}
	

	
	@Test
public void testMoveCommand() throws IOException{
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("three\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("one\n");
	    outNewFile.write("two\n");
	    outNewFile.write("three\n");
	    outNewFile.close();
	        
		String[] lOld = new TextFileIn( oldFile ).asArray();
        String[] lNew = new TextFileIn( newFile ).asArray();
        oldFileInfo=new FileInfo(lOld);
		newFileInfo=new FileInfo(lNew);
		oldFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		oldFileInfo.lineInfo[1]=new LineInfo(LineInfo.UNIQUEMATCH,2,1,2);
		newFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		newFileInfo.lineInfo[1]=new LineInfo(LineInfo.NEWONLY,0,1,1);
		newFileInfo.lineInfo[2]=new LineInfo(LineInfo.UNIQUEMATCH,1,2,2);
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{oldFileInfo.lineInfo[0].isMatch(),oldFileInfo.lineInfo[0].isOldOnly(),oldFileInfo.lineInfo[0].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{oldFileInfo.lineInfo[1].isMatch(),oldFileInfo.lineInfo[1].isOldOnly(),oldFileInfo.lineInfo[1].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{newFileInfo.lineInfo[0].isMatch(),newFileInfo.lineInfo[0].isOldOnly(),newFileInfo.lineInfo[0].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{false,false,true},new Boolean[]{newFileInfo.lineInfo[1].isMatch(),newFileInfo.lineInfo[1].isOldOnly(),newFileInfo.lineInfo[1].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{newFileInfo.lineInfo[2].isMatch(),newFileInfo.lineInfo[2].isOldOnly(),newFileInfo.lineInfo[2].isNewOnly()});
		target=new Report(oldFileInfo,newFileInfo);
		Assert.assertEquals("Match",target.getCommand(0).command);
		Assert.assertEquals("Insert before",target.getCommand(1).command);
		Assert.assertEquals("Move",target.getCommand(2).command);

		
		oldFileInfo=new FileInfo(new String[] { "one","three"} );
		newFileInfo=new FileInfo(new String[] { "one","two","three"} );
		oldFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		oldFileInfo.lineInfo[1]=new LineInfo(LineInfo.UNIQUEMATCH,2,1,2);
		newFileInfo.lineInfo[0]=new LineInfo(LineInfo.UNIQUEMATCH,0,0,0);
		newFileInfo.lineInfo[1]=new LineInfo(LineInfo.NEWONLY,0,1,1);
		newFileInfo.lineInfo[2]=new LineInfo(LineInfo.UNIQUEMATCH,1,2,2);
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{oldFileInfo.lineInfo[0].isMatch(),oldFileInfo.lineInfo[0].isOldOnly(),oldFileInfo.lineInfo[0].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{oldFileInfo.lineInfo[1].isMatch(),oldFileInfo.lineInfo[1].isOldOnly(),oldFileInfo.lineInfo[1].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{newFileInfo.lineInfo[0].isMatch(),newFileInfo.lineInfo[0].isOldOnly(),newFileInfo.lineInfo[0].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{false,false,true},new Boolean[]{newFileInfo.lineInfo[1].isMatch(),newFileInfo.lineInfo[1].isOldOnly(),newFileInfo.lineInfo[1].isNewOnly()});
		Assert.assertArrayEquals(new Boolean[]{true,false,false},new Boolean[]{newFileInfo.lineInfo[2].isMatch(),newFileInfo.lineInfo[2].isOldOnly(),newFileInfo.lineInfo[2].isNewOnly()});
		target=new Report(oldFileInfo,newFileInfo);
		Assert.assertEquals("Match",target.getCommand(0).command);
		Assert.assertEquals("Insert before",target.getCommand(1).command);
		Assert.assertEquals("Move",target.getCommand(2).command);
	}
	
	
	
}
