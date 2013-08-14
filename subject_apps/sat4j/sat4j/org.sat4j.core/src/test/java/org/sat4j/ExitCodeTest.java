package org.sat4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import junit.framework.TestCase;

import org.junit.Test;
/**
 * 
 * @author icewariya
 *
 */
public class ExitCodeTest extends TestCase{
	@Test
    public void testOptimumFound() throws Exception {
		assertEquals(30, ExitCode.OPTIMUM_FOUND.value());
		assertEquals("OPTIMUM FOUND",ExitCode.OPTIMUM_FOUND.toString());
	}
	
	@Test
    public void testUpperBound() throws Exception {
		assertEquals(30, ExitCode.UPPER_BOUND.value());
		assertEquals("UPPER BOUND",ExitCode.UPPER_BOUND.toString());
	}
	
	@Test
    public void testSatisfiable() throws Exception {
		assertEquals(10, ExitCode.SATISFIABLE.value());
		assertEquals("SATISFIABLE",ExitCode.SATISFIABLE.toString());
	}
	
	@Test
    public void testUnsatisfiable() throws Exception {
		assertEquals(20, ExitCode.UNSATISFIABLE.value());
		assertEquals("UNSATISFIABLE",ExitCode.UNSATISFIABLE.toString());
	}
	
	@Test
    public void testUnknown() throws Exception {
		assertEquals(0, ExitCode.UNKNOWN.value());
		assertEquals("UNKNOWN",ExitCode.UNKNOWN.toString());
	}
}
