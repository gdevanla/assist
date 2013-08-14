package com.saxman.unitTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.saxman.textdiff.LineInfo;
public class LineInfoTest {
	LineInfo target=new LineInfo();
	
	@Before
	public void setUp(){
		
		
	}
	@Test
	public void testisEOF(){
		target.lineStatus=-1;
		boolean status=target.isEOF();
		Assert.assertTrue(status);
	}
	@Test
	public void testisNotEOF(){
		target.lineStatus=1;
		boolean status;
		status=target.isEOF();
		Assert.assertFalse(status);
	}
	@Test
	public void testisMatch(){
		target.lineStatus=3;
		boolean status=target.isMatch();
		Assert.assertTrue(status);
	}
	@Test
	public void testisNotMatch(){
		target.lineStatus=-3;
		boolean status=target.isMatch();
		Assert.assertFalse(status);
	}
	
	@Test
	public void testisOldOnly(){
		target.lineStatus=1;
		boolean status=target.isOldOnly();
		Assert.assertTrue(status);
	}
	@Test
	public void testisOldNotOnly(){
		target.lineStatus=-3;
		boolean status=target.isOldOnly();
		Assert.assertFalse(status);
	}
	
	@Test
	public void testisNewOnly(){
		target.lineStatus=2;
		boolean status=target.isNewOnly();
		Assert.assertTrue(status);
	}
	
	@Test
	public void testisNotNewOnly(){
		target.lineStatus=-3;
		boolean status=target.isNewOnly();
		Assert.assertFalse(status);
	}
	
	@Test
	public void testLineStatusOther(){
		target.lineStatus=4;
		boolean newStatus=target.isNewOnly();
		Assert.assertTrue(newStatus);
		boolean oldStatus=target.isOldOnly();
		Assert.assertTrue(oldStatus);
	}
	
	@Test
	public void testsetBlockNumber(){
		boolean status;
		target.setBlockNumber(4);
		Assert.assertEquals(target.lineStatus,target.UNIQUEMATCH);
		Assert.assertNotSame(target.lineStatus,target.OLDONLY);
		Assert.assertNotSame(target.lineStatus,target.OTHER);
		Assert.assertNotSame(target.lineStatus,target.EOF);
		Assert.assertNotSame(target.lineStatus,target.NEWONLY);
	
	}
}


