/*
 * Copyright 2009 Guy Van den Broeck
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.outerj.daisy.diff;
import static org.junit.Assert.*;

import java.io.StringReader;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

/**
 *@author icewariya
 *
 */
public class HtmlCleanerTest  {

	@Test
    public void testCleanAndParse() throws Exception {
        
        String html = "<html><body>var v2</body></html>";     
        String htmlCopy = "<html><body>var v2</body></html>";
        
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        htmlCleaner.cleanAndParse(new InputSource(new StringReader(html)),new DefaultHandler());
        assertEquals(html, htmlCopy);
    }

    
}
