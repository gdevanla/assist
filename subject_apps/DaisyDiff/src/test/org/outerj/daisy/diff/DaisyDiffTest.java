package org.outerj.daisy.diff;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Locale;

import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Test for regressions involving out of bounds exceptions
 * 
 * @author guy
 *
 */
public class DaisyDiffTest  {

	@Test
    public void testOutOfBoundsExample1() throws Exception {
        
        String html1 = "<html><body>var v2</body></html>";
        String html2 = "<html>  \n  <body>  \n  Hello world  \n  </body>  \n  </html>";

        DaisyDiff.diffHTML(new InputSource(new StringReader(html1)), new InputSource(new StringReader(html2)), new DefaultHandler(), "test", Locale.ENGLISH);
       
    }
	
	@Test
    public void testOutOfBoundsExample2() throws Exception {
        
        String html1 = "<html><body>var v2</body></html>";
        String html2 = "<html>  \n  <body>  \n  Hello world  \n  </body>  \n  </html>";
        
        DaisyDiff.diffTag(html1, html2, new DefaultHandler());
        
        
    }
	
	@Test
    public void testOutOfBoundsExample3() throws Exception {
        
        String html1 = "<html><body>var v2</body></html>";
        String html2 = "<html>  \n  <body>  \n  Hello world  \n  </body>  \n  </html>";
        
        DaisyDiff.diffTag(new BufferedReader(new StringReader(html1)), new BufferedReader(new StringReader(html2)), new DefaultHandler());
        
        
    }
	
	@Test
    public void testOutOfBoundsExample4() throws Exception {
        
        String html1 = "<html>  \n  <body>  \n  Hello world  \n  </body>  \n  </html>";
        String html2 = "<html><body>var v2</body></html>";
    
        DaisyDiff.diffHTML(new InputSource(new StringReader(html1)), new InputSource(new StringReader(html2)), new DefaultHandler(), "test", Locale.ENGLISH);
    }
	
	@Test
    public void testOutOfBoundsExample5() throws Exception {
        
        String html1 = "<html>  \n  <body>  \n  Hello world  \n  </body>  \n  </html>";
        String html2 = "<html><body>var v2</body></html>";
    
        DaisyDiff.diffTag(html1, html2, new DefaultHandler());
       
    }
	
	@Test
    public void testOutOfBoundsExample6() throws Exception {
        
        String html1 = "<html>  \n  <body>  \n  Hello world  \n  </body>  \n  </html>";
        String html2 = "<html><body>var v2</body></html>";
    
        DaisyDiff.diffTag(new BufferedReader(new StringReader(html1)), new BufferedReader(new StringReader(html2)), new DefaultHandler());
        
    }
	
	@Test
    public void testOutOfBoundsExample7() throws Exception {
        
        String html1 = "<html><head></head><body><p>test</p></body></html>";
        String html2 = "<html><head></head><body></body></html>";
    
        DaisyDiff.diffHTML(new InputSource(new StringReader(html1)), new InputSource(new StringReader(html2)), new DefaultHandler(), "test", Locale.ENGLISH);
        
    }
	
	@Test
    public void testOutOfBoundsExample8() throws Exception {
        
        String html1 = "<html><head></head><body><p>test</p></body></html>";
        String html2 = "<html><head></head><body></body></html>";
    
        DaisyDiff.diffTag(html1, html2, new DefaultHandler());
        
    }
	
	@Test
    public void testOutOfBoundsExample9() throws Exception {
        
        String html1 = "<html><head></head><body><p>test</p></body></html>";
        String html2 = "<html><head></head><body></body></html>";
    
        DaisyDiff.diffTag(new BufferedReader(new StringReader(html1)), new BufferedReader(new StringReader(html2)), new DefaultHandler());
        
    }
	
	@Test
    public void testOutOfBoundsExample10() throws Exception {
        
        String html1 = "<html><head></head><body></body></html>";
        String html2 = "<html><head></head><body><p>test</p></body></html>";
    
        DaisyDiff.diffHTML(new InputSource(new StringReader(html1)), new InputSource(new StringReader(html2)), new DefaultHandler(), "test", Locale.ENGLISH);
       
    }
	
	@Test
    public void testOutOfBoundsExample11() throws Exception {
        
        String html1 = "<html><head></head><body></body></html>";
        String html2 = "<html><head></head><body><p>test</p></body></html>";
    
        DaisyDiff.diffTag(html1, html2, new DefaultHandler());
        
    }
	
	@Test
    public void testOutOfBoundsExample12() throws Exception {
        
        String html1 = "<html><head></head><body></body></html>";
        String html2 = "<html><head></head><body><p>test</p></body></html>";
    
        DaisyDiff.diffTag(new BufferedReader(new StringReader(html1)), new BufferedReader(new StringReader(html2)), new DefaultHandler());
        
    }
	@Test
    public void testOutOfBoundsExample13() throws Exception {
        
        String html1 = "<html><head></head><body><p>test</p><p>test</p></body></html>";
        String html2 = "<html><head></head><body></body></html>";
    
        DaisyDiff.diffHTML(new InputSource(new StringReader(html1)), new InputSource(new StringReader(html2)), new DefaultHandler(), "test", Locale.ENGLISH);
        
    }
	
	@Test
    public void testOutOfBoundsExample14() throws Exception {
        
        String html1 = "<html><head></head><body><p>test</p><p>test</p></body></html>";
        String html2 = "<html><head></head><body></body></html>";
    
        DaisyDiff.diffTag(html1, html2, new DefaultHandler());
        
    }
	
	@Test
    public void testOutOfBoundsExample15() throws Exception {
        
        String html1 = "<html><head></head><body><p>test</p><p>test</p></body></html>";
        String html2 = "<html><head></head><body></body></html>";
    
        DaisyDiff.diffTag(new BufferedReader(new StringReader(html1)), new BufferedReader(new StringReader(html2)), new DefaultHandler());
        
    }
	
	@Test
    public void testOutOfBoundsExample16() throws Exception {
        
        String html1 = "<html><head></head><body></body></html>";
        String html2 = "<html><head></head><body><p>test</p><p>test</p></body></html>";
    
        DaisyDiff.diffHTML(new InputSource(new StringReader(html1)), new InputSource(new StringReader(html2)), new DefaultHandler(), "test", Locale.ENGLISH);
        
    }
	
	@Test
    public void testOutOfBoundsExample17() throws Exception {
        
        String html1 = "<html><head></head><body></body></html>";
        String html2 = "<html><head></head><body><p>test</p><p>test</p></body></html>";
    
        DaisyDiff.diffTag(html1, html2, new DefaultHandler());
    }
	
	@Test
    public void testOutOfBoundsExample18() throws Exception {
        
        String html1 = "<html><head></head><body></body></html>";
        String html2 = "<html><head></head><body><p>test</p><p>test</p></body></html>";
    
        DaisyDiff.diffTag(new BufferedReader(new StringReader(html1)), new BufferedReader(new StringReader(html2)), new DefaultHandler());
        
    }
    
}
