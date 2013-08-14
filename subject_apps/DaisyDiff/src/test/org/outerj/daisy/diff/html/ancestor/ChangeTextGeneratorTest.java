/*
 * Copyright 2007 Guy Van den Broeck
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
package org.outerj.daisy.diff.html.ancestor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.compare.rangedifferencer.RangeDifference;
import org.eclipse.compare.rangedifferencer.RangeDifferencer;
import org.junit.Test;
import org.outerj.daisy.diff.html.dom.TagNode;
import org.outerj.daisy.diff.html.modification.HtmlLayoutChange;
import org.xml.sax.helpers.AttributesImpl;
/*
 * @author icewariya
 */
public class ChangeTextGeneratorTest {

	@Test
	public void testChangeTextGenerator() throws Exception {
		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(null, "middle", new AttributesImpl());

    	List<TagNode> firstNodeList = new ArrayList<TagNode>();
    	firstNodeList.add(root);
    	firstNodeList.add(intermediate);
    	
    	TagNode html = new TagNode(null, "html", new AttributesImpl());
    	TagNode body = new TagNode(null, "body", new AttributesImpl());

    	List<TagNode> secondNodeList = new ArrayList<TagNode>();
    	secondNodeList.add(html);
    	secondNodeList.add(body);
	
		AncestorComparator comp =  new AncestorComparator(firstNodeList);
		AncestorComparator other =  new AncestorComparator(secondNodeList);

		ChangeTextGenerator textGenerator = new ChangeTextGenerator(comp, other, Locale.ENGLISH);
		
		assertEquals(ChangeTextGenerator.class, textGenerator.getClass());
	}
	
	@Test
	public void testGetChanged() throws Exception {
		TagNode root = new TagNode(null, "root", new AttributesImpl());
    	TagNode intermediate = new TagNode(null, "middle", new AttributesImpl());

    	List<TagNode> firstNodeList = new ArrayList<TagNode>();
    	firstNodeList.add(root);
    	firstNodeList.add(intermediate);
    	
    	TagNode html = new TagNode(null, "html", new AttributesImpl());
    	TagNode body = new TagNode(null, "body", new AttributesImpl());

    	List<TagNode> secondNodeList = new ArrayList<TagNode>();
    	secondNodeList.add(html);
    	secondNodeList.add(body);
	
		AncestorComparator comp =  new AncestorComparator(firstNodeList);
		AncestorComparator other =  new AncestorComparator(secondNodeList);

		ChangeTextGenerator textGenerator = new ChangeTextGenerator(comp, other, Locale.ENGLISH);
		
		List<HtmlLayoutChange> htmlLayoutChanges = new ArrayList<HtmlLayoutChange>();
		assertEquals(htmlLayoutChanges, textGenerator.getHtmlLayoutChanges());
		
		RangeDifference[] differences = RangeDifferencer.findDifferences(other,comp);
	    String changedText = "<ul class='changelist'><li>Moved out of a <b>html page</b>.</li><li>Moved out of a <b>html document</b>.</li><li><b>!diff-root!</b> added.</li><li><b>!diff-middle!</b> added.</li></ul>";
	    
	    assertEquals(changedText, textGenerator.getChanged(differences).toString());
	    assertEquals(ChangeText.class, textGenerator.getChanged().getClass());

	}
	

}
