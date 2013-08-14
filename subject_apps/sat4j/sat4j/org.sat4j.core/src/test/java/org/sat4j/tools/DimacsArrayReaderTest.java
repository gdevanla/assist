package org.sat4j.tools;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;

import org.sat4j.specs.ISolver;

public class DimacsArrayReaderTest extends TestCase{
		private DimacsArrayReader reader;
		private ISolver solver;
		
	 	public DimacsArrayReaderTest(String arg0) {
	        super(arg0);
	    }

	    /*
	     * @see TestCase#setUp()
	     */
	    @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	        this.solver = mock(ISolver.class);
	        this.reader = new DimacsArrayReader(solver);
	    }

	    /*
	     * @see TestCase#tearDown()
	     */
	    @Override
	    protected void tearDown() throws Exception {
	        super.tearDown();
	    }
	    
	    public void testHandleConstr() throws Exception {
	    	int[] inputs = new int[50];
	    	for(int i=0;i<50;i++)
	    		inputs[i] = i;
	    	
	    	assertTrue(reader.handleConstr(1, 1, inputs));
	    }
	    
	    public void testParseInstance() throws Exception {
	    	int[] gateType = new int[50];
	    	for(int i=0;i<50;i++)
	    		gateType[i] = i;
	    	
	    	int[] outputs = new int[50];
	    	for(int i=0;i<50;i++)
	    		outputs[i] = i;
	    	
	    	int[][] inputs = new int[50][50];
	    	for(int i=0;i<5;i++)
	    		for(int j=0;j<5;j++)
	    		inputs[i][j] = i;
	    	
	    	ISolver expected = reader.parseInstance(gateType, outputs, inputs, 10);
	    	
	    	assertEquals(0, expected.nVars());
	    }
	    
	    public void testDecode() throws Exception {
	    	int[] model = new int[5];
	    	for(int i=0;i<5;i++)
	    		model[i] = i;
	    	
	    	assertEquals("0 1 2 3 4 0", reader.decode(model));
	    }
	    
	    public void testGetSolver() throws Exception {
	    	assertEquals(solver, reader.getSolver());
	    }
}
