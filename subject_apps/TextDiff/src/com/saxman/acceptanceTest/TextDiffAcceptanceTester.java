package com.saxman.acceptanceTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.saxman.textdiff.EditCommand;
import com.saxman.textdiff.Report;
import com.saxman.textdiff.TextDiff;
import com.saxman.util.TextFileIn;

//import com.saxman.util.SSString;

public class TextDiffAcceptanceTester
{
	@Rule
	public TemporaryFolder folder=new TemporaryFolder();
	
	private File oldFile;
	private File newFile;
	private File nullFile;
	
	@Before
	public void setUp() throws Exception{
			oldFile = folder.newFile("oldFile.txt");
	        newFile = folder.newFile("newFile.txt");
	       
	}
	
	private Report runFileCompare (File fileOld,File fileNew) throws Exception 
	{
		String lOld[]=new TextFileIn(fileOld).asArray();
		String lNew[]=new TextFileIn(fileNew).asArray();
		
		return new TextDiff().compare( lOld, lNew );
		
	}
	
	@Test
	public void testBothEmpty() throws Exception
    {
	    Report lReport=null;
	    BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("");
	    outNewFile.close();
	    lReport = runFileCompare( oldFile,newFile );
        Assert.assertEquals( 0, lReport.size() );
	    
        lReport = runCompare( new String[0], new String[0] );
       lReport.print();
        Assert.assertEquals( 0, lReport.size() );
        
    }

	@Test
    public void testNewEmpty() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
		outOldFile.write("one\n");
		outOldFile.close();
		    
		BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
		outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		Assert.assertEquals( 1, lReport.size() );
        Assert.assertEquals( "Delete", lReport.getCommand( 0 ).command );
        
		lReport = runCompare( new String[] { "one" }, new String[0] );
        lReport.print();
        Assert.assertEquals( 1, lReport.size() );
        Assert.assertEquals( "Delete", lReport.getCommand( 0 ).command );
    }

	@Test
    public void testOldEmpty() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
		outOldFile.write("one\n");
		outOldFile.close();
		    
		BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
		outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		
        lReport = runCompare( new String[0], new String[] { "one" } );
      //  lReport.print();
        Assert.assertEquals( 1, lReport.size() );
        Assert.assertEquals( "Append", lReport.getCommand( 0 ).command );
    }

	@Test
    public void testMatch() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
		outOldFile.write("one\n");
		outOldFile.close();
		    
		BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
		outNewFile.write("one\n");
		outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		Assert.assertEquals( 1, lReport.size() );
        EditCommand lCmd = lReport.getCommand( 0 );
        Assert.assertEquals( "Match", lCmd.command );
        Assert.assertEquals( 1, lCmd.oldLines.lines.length );
        
        lReport = runCompare( new String[] { "one", "two", "three", "four" }, new String[] { "one", "two",
                "three", "four" } );
      //  lReport.print();
        Assert.assertEquals( 1, lReport.size() );
        lCmd = lReport.getCommand( 0 );
        Assert.assertEquals( "Match", lCmd.command );
        Assert.assertEquals( 4, lCmd.oldLines.lines.length );
    }

	@Test
    public void testChange() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
		outOldFile.write("one\n");
		outOldFile.close();
		    
		BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
		outNewFile.write("two\n");
		outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		Assert.assertEquals( 1, lReport.size() );
	    EditCommand lCmd = lReport.getCommand( 0 );
	    Assert.assertEquals( "Change", lCmd.command );
		
       lReport = runCompare( new String[] { "one", }, new String[] { "two", } );
      //  lReport.print();
        Assert.assertEquals( 1, lReport.size() );
        lCmd = lReport.getCommand( 0 );
        Assert.assertEquals( "Change", lCmd.command );

        lReport = runCompare( new String[] { "one", "two" }, new String[] { "one", "three" } );
      //  lReport.print();
        Assert.assertEquals( 2, lReport.size() );
        lCmd = lReport.getCommand( 0 );
        Assert.assertEquals( "Match", lCmd.command );
        lCmd = lReport.getCommand( 1 );
        Assert.assertEquals( "Change", lCmd.command );

        lReport = runCompare( new String[] { "one", "two", "three" }, new String[] { "one", "four", "three" } );
       // lReport.print();
        Assert.assertEquals( 3, lReport.size() );
        lCmd = lReport.getCommand( 0 );
        Assert.assertEquals( "Match", lCmd.command );
        lCmd = lReport.getCommand( 1 );
        Assert.assertEquals( "Change", lCmd.command );
        lCmd = lReport.getCommand( 2 );
        Assert.assertEquals( "Match", lCmd.command );
    }

	@Test
    public void testDelete() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
		outOldFile.write("one\n");
		outOldFile.write("two\n");
		outOldFile.close();
		    
		BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
		outNewFile.write("one\n");
		outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		Assert.assertEquals( 2, lReport.size() );
	    Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Delete", lReport.getCommand( 1 ).command );
	    
	        
       lReport = runCompare( new String[] { "one", "two", "three", "four" }, new String[] { "one", "four" } );
       // lReport.print();
        Assert.assertEquals( 3, lReport.size() );
        Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
        Assert.assertEquals( "Delete", lReport.getCommand( 1 ).command );
        Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
    }
	@Test 
    public void testInsertBefore() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("three\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("one\n");
	    outNewFile.write("two\n");
	    outNewFile.write("three\n");
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
		
        lReport = runCompare( new String[] { "one", "four" }, new String[] { "one", "two", "three", "four" } );
       // lReport.print();
        Assert.assertEquals( 3, lReport.size() );
        Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
        Assert.assertEquals( "Insert before", lReport.getCommand( 1 ).command );
        Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
    }
	
	@Test
    public void testMove() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
		outOldFile.write("one\n");
		outOldFile.write("two\n");
		outOldFile.write("three\n");
		outOldFile.close();
		    
		BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
		outNewFile.write("one\n");
		outNewFile.write("three\n");
		outNewFile.write("two\n");
		outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Move", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
		
        lReport = runCompare( new String[] { "a", "b", "c", "d" }, new String[] { "a", "c", "d", "b" } );
       // lReport.print();
        Assert.assertEquals( 3, lReport.size() );
        Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
        Assert.assertEquals( "Move", lReport.getCommand( 1 ).command );
        Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
    }
	@Test
    public void testMoveAndAppend() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
		outOldFile.write("one\n");
		outOldFile.write("two\n");
		outOldFile.write("three\n");
		outOldFile.close();
		    
		BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
		outNewFile.write("one\n");
		outNewFile.write("three\n");
		outNewFile.write("two\n");
		outNewFile.write("four\n");
		
		outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		Assert.assertEquals( 4, lReport.size() );
	    Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Move", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Append", lReport.getCommand( 3 ).command );
	    
	    
        lReport = runCompare( new String[] { "a", "b", "c", "d" }, new String[] { "a", "c", "d", "x", "y", "z",
                "b" } );
       // lReport.print();
        Assert.assertEquals( 4, lReport.size() );
        Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
        Assert.assertEquals( "Move", lReport.getCommand( 1 ).command );
        Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
        Assert.assertEquals( "Append", lReport.getCommand( 3 ).command );
    }
	@Test 
    public void testAmbiguous() throws Exception
    {
		
		Report lReport = runCompare( new String[] { "a", "b", "a", "b" }, new String[] { "b", "a", "b", "a" } );
       // lReport.print();
        Assert.assertEquals( 1, lReport.size() );
        Assert.assertEquals( "Change", lReport.getCommand( 0 ).command );
    }

    // Reproduced SSWiki defect D2.
	@Test
    public void testAmbiguousAfterChange() throws Exception
    {
		Report lReport=null;
		lReport = runCompare( new String[] { "a", " ", "b", " ", "c", " ", "d" }, new String[] { "a", " ", "b",
                " ", "x", " ", "d" } );
       // lReport.print();
        Assert.assertEquals( 3, lReport.size() );
        Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
        Assert.assertEquals( "Change", lReport.getCommand( 1 ).command );
        Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
    }
	
	@Test 
    public void testInsertAppend() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("three\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("one\n");
	    outNewFile.write("two\n");
	    outNewFile.write("three\n");
	    outNewFile.write("four\n");
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		
		Assert.assertEquals( 4, lReport.size() );
		Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
		Assert.assertEquals( "Insert before", lReport.getCommand( 1 ).command );
		Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
		Assert.assertEquals( "Append", lReport.getCommand( 3 ).command );
        lReport = runCompare( new String[] { "one", "four" }, new String[] { "one", "two", "three", "four","five" } );
        lReport.print();
     
        Assert.assertEquals( 4, lReport.size() );
	    Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Append", lReport.getCommand( 3 ).command );
		
    }
	
	
	@Test 
    public void testDeleteInsert() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.write("five\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("two\n");
	    outNewFile.write("three\n");
	    outNewFile.write("four\n");
	    outNewFile.write("five\n");
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
					
		Assert.assertEquals( 4, lReport.size() );
	    Assert.assertEquals( "Delete", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 3 ).command );
	   
        lReport = runCompare( new String[] { "a", "b","c","e" }, new String[] { "b", "c", "d", "e" } );
        lReport.print();
     
    	Assert.assertEquals( 4, lReport.size() );
	    Assert.assertEquals( "Delete", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 3 ).command );
	    
	}
	
	@Test 
    public void testDeleteChange() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.write("five\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("two\n");
	    outNewFile.write("three\n");
	    outNewFile.write("four\n");
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		
				
		Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Delete", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Change", lReport.getCommand( 2 ).command );
	    
	   
        lReport = runCompare( new String[] { "a", "b","c","e" }, new String[] { "b", "c", "d" } );
        lReport.print();
     
        Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Delete", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Change", lReport.getCommand( 2 ).command );
	}
	
	@Test 
    public void testMoveInsertAppend() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.write("four\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("two\n");
	    outNewFile.write("three\n");
	    outNewFile.write("five\n");
	    outNewFile.write("six\n");
	    outNewFile.write("four\n");
	    outNewFile.write("Seven\n");
	    outNewFile.write("one\n");
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		Assert.assertEquals( 5, lReport.size() );
	    Assert.assertEquals( "Move", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 3 ).command );
	    Assert.assertEquals( "Append", lReport.getCommand( 4 ).command );
		
	    lReport = runCompare( new String[] { "a", "b","c","d" }, new String[] { "b", "c", "f", "g","d","h","a" } );
        lReport.print();
     
        Assert.assertEquals( 5, lReport.size() );
	    Assert.assertEquals( "Move", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 3 ).command );
	    Assert.assertEquals( "Append", lReport.getCommand( 4 ).command );
	}
	
	@Test 
    public void testMoveInsertChange() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.write("four\n");
	    outOldFile.write("five\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("two\n");
	    outNewFile.write("three\n");
	    outNewFile.write("six\n");
	    outNewFile.write("four\n");
	    outNewFile.write("Seven\n");
	    outNewFile.write("one\n");
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		
			
		Assert.assertEquals( 5, lReport.size() );
	    Assert.assertEquals( "Move", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 3 ).command );
	    Assert.assertEquals( "Change", lReport.getCommand( 4 ).command );
		
	    lReport = runCompare( new String[] { "a", "b","c","d","g" }, new String[] { "b", "c", "f", "d","h","a" } );
        lReport.print();
     
        Assert.assertEquals( 5, lReport.size() );
	    Assert.assertEquals( "Move", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 3 ).command );
	    Assert.assertEquals( "Change", lReport.getCommand( 4 ).command );
	}
	
	@Test 
    public void testInsertDelete() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("one\n");
	    outNewFile.write("two\n");
	    
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		
		Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Delete", lReport.getCommand( 2 ).command );
		
	    lReport = runCompare( new String[] { "b","c"}, new String[] { "a", "b"} );
        lReport.print();
     
	    Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Delete", lReport.getCommand( 2 ).command );
	}
	@Test 
    public void testInsertChange() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("one\n");
	    outNewFile.write("two\n");
	    outNewFile.write("four\n");
	    
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		
		
		Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Change", lReport.getCommand( 2 ).command );
		
	    lReport = runCompare( new String[] { "b","c"}, new String[] { "a", "b","d"} );
        lReport.print();
     
	    Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Change", lReport.getCommand( 2 ).command );
	}
	

	@Test 
    public void testDeleteAppend() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
		outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    
	    outNewFile.write("two\n");
	    outNewFile.write("three\n");
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Delete", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Append", lReport.getCommand( 2 ).command );
		
	    lReport = runCompare( new String[] { "a","b"}, new String[] { "b", "c"} );
        lReport.print();
     
	    Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Delete", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Append", lReport.getCommand( 2 ).command );
	}
	
	
	@Test 
    public void testChangeAppend() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("one\n");
	    outNewFile.write("to\n");
	    outNewFile.write("three\n");
	    outNewFile.write("four\n");
	    
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
		Assert.assertEquals( 4, lReport.size() );
	    Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Change", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Append", lReport.getCommand( 3 ).command );
		
	    lReport = runCompare( new String[] { "a","b","c"}, new String[] { "a", "bc","c","d"} );
        lReport.print();
	    Assert.assertEquals( 4, lReport.size() );
	    Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Change", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Append", lReport.getCommand( 3 ).command );
	}
	@Test 
    public void testInsertMove() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("one\n");
	    outNewFile.write("four\n");
	    outNewFile.write("three\n");
	    outNewFile.write("two\n");
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
			Assert.assertEquals( 4, lReport.size() );
	    Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Move", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 3 ).command );
		
	    lReport = runCompare( new String[] { "a","b","c"}, new String[] { "a", "d","c","b"} );
        lReport.print();
	    Assert.assertEquals( 4, lReport.size() );
	    Assert.assertEquals( "Match", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Move", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 3 ).command );
		
	}
	
	
	@Test 
    public void testDeleteMove() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("three\n");
	    outNewFile.write("two\n");
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
	
		Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Delete", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Move", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	  
	    lReport = runCompare( new String[] { "a","b","c"}, new String[] { "c","b"} );
        lReport.print();
	    Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Delete", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Move", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	 
		
	}
	
	@Test 
    public void testChangeMove() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("onee\n");
	    outNewFile.write("three\n");
	    outNewFile.write("two\n");
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
	
		Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Change", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Move", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	  
	    lReport = runCompare( new String[] { "a","b","c"}, new String[] {"aa","c","b"} );
        lReport.print();
	    Assert.assertEquals( 3, lReport.size() );
	    Assert.assertEquals( "Change", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Move", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	 }
	
	@Test 
    public void testMoveDeleteInsertChange() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.write("four\n");
	    outOldFile.write("five\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	   
	    outNewFile.write("three\n");
	    outNewFile.write("six\n");
	    outNewFile.write("four\n");
	    outNewFile.write("Seven\n");
	    outNewFile.write("one\n");
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
					
		Assert.assertEquals( 6, lReport.size() );
	    Assert.assertEquals( "Move", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Delete", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 3 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 4 ).command );
	    Assert.assertEquals( "Change", lReport.getCommand( 5 ).command );
		
	    lReport = runCompare( new String[] { "a", "b","c","d","g" }, new String[] { "c", "f", "d","h","a" } );
        lReport.print();
     
	    Assert.assertEquals( 6, lReport.size() );
	    Assert.assertEquals( "Move", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Delete", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 3 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 4 ).command );
	    Assert.assertEquals( "Change", lReport.getCommand( 5 ).command );
	}

	@Test 
    public void testMoveDeleteInsertAppend() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.write("four\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	   
	    outNewFile.write("three\n");
	    outNewFile.write("six\n");
	    outNewFile.write("four\n");
	    outNewFile.write("one\n");
	    outNewFile.write("eight\n");
	    
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
	
		
		
		Assert.assertEquals( 6, lReport.size() );
	    Assert.assertEquals( "Move", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Delete", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 3 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 4 ).command );
	    Assert.assertEquals( "Append", lReport.getCommand( 5 ).command );
		
	    lReport = runCompare( new String[] { "a", "b","c","d" }, new String[] { "c", "f", "d","a","i" } );
        lReport.print();
     
	    Assert.assertEquals( 6, lReport.size() );
	    Assert.assertEquals( "Move", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Delete", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 3 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 4 ).command );
	    Assert.assertEquals( "Append", lReport.getCommand( 5 ).command );
		
		
	}
	
	@Test 
    public void testMoveDeleteInsertChangeAppend() throws Exception
    {
		Report lReport=null;
		BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.write("one\n");
	    outOldFile.write("two\n");
	    outOldFile.write("three\n");
	    outOldFile.write("four\n");
	    outOldFile.write("five\n");
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	   
	    outNewFile.write("three\n");
	    outNewFile.write("six\n");
	    outNewFile.write("four\n");
	    outNewFile.write("Seven\n");
	    outNewFile.write("one\n");
	    outNewFile.write("eight\n");
	    
	    outNewFile.close();
		lReport = runFileCompare( oldFile,newFile );
	
		
		Assert.assertEquals( 7, lReport.size() );
	    Assert.assertEquals( "Move", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Delete", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 3 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 4 ).command );
	    Assert.assertEquals( "Change", lReport.getCommand( 5 ).command );
	    Assert.assertEquals( "Append", lReport.getCommand( 6 ).command );
		
	    lReport = runCompare( new String[] { "a", "b","c","d","g" }, new String[] { "c", "f", "d","h","a","i" } );
        lReport.print();
     
	    Assert.assertEquals( 7, lReport.size() );
	    Assert.assertEquals( "Move", lReport.getCommand( 0 ).command );
	    Assert.assertEquals( "Delete", lReport.getCommand( 1 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 2 ).command );
	    Assert.assertEquals( "Insert before", lReport.getCommand( 3 ).command );
	    Assert.assertEquals( "Match", lReport.getCommand( 4 ).command );
	    Assert.assertEquals( "Change", lReport.getCommand( 5 ).command );
	    Assert.assertEquals( "Append", lReport.getCommand( 6 ).command );
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testOldFileNullPointerException() throws Exception
    {
		Report lReport=null;
		try{	
	    
	    BufferedWriter outOldFile = new BufferedWriter(new FileWriter(nullFile));
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(newFile));
	    outNewFile.write("");
	    outNewFile.close();
	    lReport = runFileCompare( oldFile,newFile );
        Assert.assertEquals( 0, lReport.size() );
	    
        lReport = runCompare( new String[0], new String[0] );
        Assert.fail("Should throw Null Pointer Exception");
		}
		catch(Exception e)
		{
			Assert.assertEquals( 0, lReport.size() );
			
		}
        
    }
	
	
	@Test(expected=NullPointerException.class)
	public void testNewFileNullPointerException() throws Exception
    {
		Report lReport=null;
		try{	
	    
	    BufferedWriter outOldFile = new BufferedWriter(new FileWriter(oldFile));
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(nullFile));
	    outNewFile.write("");
	    outNewFile.close();
	    lReport = runFileCompare( oldFile,newFile );
        Assert.assertEquals( 0, lReport.size() );
	    
        lReport = runCompare( new String[0], new String[0] );
        Assert.fail("Should throw Null Pointer Exception");
		}
		catch(Exception e)
		{
			Assert.assertEquals( 0, lReport.size() );
		}
        
    }
	
	@Test(expected=NullPointerException.class)
	public void testBothFilesNullPointerException() throws Exception
    {
		Report lReport=null;
		try{	
	    
	    BufferedWriter outOldFile = new BufferedWriter(new FileWriter(nullFile));
	    outOldFile.close();
	     
	    BufferedWriter outNewFile = new BufferedWriter(new FileWriter(nullFile));
	    outNewFile.write("");
	    outNewFile.close();
	    lReport = runFileCompare( oldFile,newFile );
        Assert.assertEquals( 0, lReport.size() );
	    
        lReport = runCompare( new String[0], new String[0] );
        Assert.fail("Should throw Null Pointer Exception");
		}
		catch(Exception e)
		{
			Assert.assertEquals( 0, lReport.size() );
		}
    }
	
    private Report runCompare(String[] aOld, String[] aNew)
    {
      return new TextDiff().compare( aOld, aNew );
    }
/*
    // -------------- Housekeeping -----------
    public TextDiffTester(java.lang.String testName)
    {
        super( testName );
    }

 
    public static void main(java.lang.String[] args)
    {
        junit.textui.TestRunner.run( suite() );
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite( TextDiffTester.class );
        return suite;
    }
       */

}


