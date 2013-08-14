
package org.eclipse.compare.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author icewariya
 */
public class LCSSettingsTest {
	@Test
    public void testTooLong() throws Exception
    {
		double too_long = 10000000.0;
		LCSSettings lcs = new LCSSettings();
		lcs.setTooLong(too_long);
		assertEquals(too_long, lcs.getTooLong(),0);
    }

	@Test
    public void testTooLongZero() throws Exception
    {
		double too_long = 0;
		LCSSettings lcs = new LCSSettings();
		lcs.setTooLong(too_long);
		assertEquals(too_long, lcs.getTooLong(),0);
    }
	
	@Test
    public void testTooLongLong() throws Exception
    {
		double too_long = 978462375223.21;
		LCSSettings lcs = new LCSSettings();
		lcs.setTooLong(too_long);
		assertEquals(too_long, lcs.getTooLong(),0);
    }
	
	@Test
    public void testTooLongNeg() throws Exception
    {
		double too_long = -978462375223.21;
		LCSSettings lcs = new LCSSettings();
		lcs.setTooLong(too_long);
		assertEquals(too_long, lcs.getTooLong(),0);
    }
	
	@Test
    public void testPowLimit() throws Exception
    {
		double powLimit = 35.50;
		LCSSettings lcs = new LCSSettings();
		lcs.setPowLimit(powLimit);
		assertEquals(powLimit, lcs.getPowLimit(),0);
    }

	@Test
    public void testPowLimitZero() throws Exception
    {
		double powLimit = 0;
		LCSSettings lcs = new LCSSettings();
		lcs.setPowLimit(powLimit);
		assertEquals(powLimit, lcs.getPowLimit(),0);
    }
	
	@Test
    public void testPowLimitLong() throws Exception
    {
		double powLimit = 978462375223.21;
		LCSSettings lcs = new LCSSettings();
		lcs.setPowLimit(powLimit);
		assertEquals(powLimit, lcs.getPowLimit(),0);
    }
	
	@Test
    public void testPowNeg() throws Exception
    {
		double powLimit = -35.50;
		LCSSettings lcs = new LCSSettings();
		lcs.setPowLimit(powLimit);
		assertEquals(powLimit, lcs.getPowLimit(),0);
    }
	
	@Test
    public void testUseGreedyMethod() throws Exception
    {
		LCSSettings lcs = new LCSSettings();
		
		lcs.setUseGreedyMethod(true);
		assertTrue(lcs.isUseGreedyMethod());
		
		lcs.setUseGreedyMethod(false);
		assertFalse(lcs.isUseGreedyMethod());
    }
}
