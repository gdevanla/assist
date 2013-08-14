package org.sat4j.tools;
import static org.mockito.Mockito.mock;
import org.sat4j.specs.ISolver;

import junit.framework.TestCase;

public class ExtendedDimacsArrayReaderTest extends TestCase{
	private ExtendedDimacsArrayReader reader;
    private ISolver solver;
    private int[] inputs;
	private int[] emptyInputs;
	
    public ExtendedDimacsArrayReaderTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.inputs = new int[5];
        this.emptyInputs = new int[0];
        
        this.solver = mock(ISolver.class);
        this.reader = new ExtendedDimacsArrayReader(solver);
        
        for(int i=0;i<5;i++) 
    		this.inputs[i] = i;
    }

    /*
     * @see TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testHandleConstrFalseExample1() throws Exception {
    	assertTrue(reader.handleConstr(1, 1, inputs));
    }
    
    public void testHandleConstrFalseExample2() throws Exception {
    	assertTrue(reader.handleConstr(1, 0, emptyInputs));
    }
    
    public void testHandleConstrTrueExample1() throws Exception {
    	assertTrue(reader.handleConstr(2, 0, inputs));
    }
    
    public void testHandleConstrTrueExample2() throws Exception {
    	assertTrue(reader.handleConstr(2, 1, emptyInputs));
    }
    
    public void testHandleConstrNotExample() throws Exception {
    	assertTrue(reader.handleConstr(3, 1, inputs));
    }
    
    public void testHandleConstrAndExample1() throws Exception {
    	assertTrue(reader.handleConstr(4, 0, inputs));
    }
    
    public void testHandleConstrAndExample2() throws Exception {
    	assertTrue(reader.handleConstr(4, 1, emptyInputs));
    }
    
    public void testHandleConstrOrExample1() throws Exception {
    	assertTrue(reader.handleConstr(6, 0, inputs));
    }
    
    public void testHandleConstrOrExample2() throws Exception {
    	assertTrue(reader.handleConstr(6, 1, emptyInputs));
    }
    
    public void testHandleConstrXorExample1() throws Exception {
    	assertTrue(reader.handleConstr(8, 1, inputs));
    }
    
    public void testHandleConstrXorExample2() throws Exception {
    	assertTrue(reader.handleConstr(8, 0, emptyInputs));
    }
    
    public void testHandleConstrIffExample1() throws Exception {
    	assertTrue(reader.handleConstr(11, 0, inputs));
    }
    
    public void testHandleConstrIffExample2() throws Exception {
    	assertTrue(reader.handleConstr(11, 1, emptyInputs));
    }
    
    public void testHandleConstrIfThenElse() throws Exception {
    	assertTrue(reader.handleConstr(12, 1, inputs));
    }
    
    public void testDefault() throws Exception {
    	try {
    	assertTrue(reader.handleConstr(50, 0, inputs));
    	} catch(Exception e) {
    		assertEquals("Gate type 50 not handled yet",e.getMessage());
    	}
    }
    
}
