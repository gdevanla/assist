package com.google.test.metric;


import junit.framework.TestCase;

public class RegExpWhiteListGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.RegExpWhiteList
	 */
	public void testRegExpWhiteList880() throws Exception {
		RegExpWhiteList var2757 = new RegExpWhiteList("java.lang");
		var2757.isClassWhiteListed("java.lang.String");
		var2757.isClassWhiteListed("com.company.String");
		var2757.isClassWhiteListed("java.lang.String");
		var2757.isClassWhiteListed("java.x.Z");
	}

	/**
	 * Test method for the class com.google.test.metric.RegExpWhiteList
	 */
	public void testRegExpWhiteList881() throws Exception {
		RegExpWhiteList var2760 = new RegExpWhiteList("java.");
		var2760.isClassWhiteListed("java.lang.String");
		var2760.isClassWhiteListed("com.company.String");
		var2760.isClassWhiteListed("java.lang.String");
		var2760.isClassWhiteListed("java.x.Z");
	}

	/**
	 * Test method for the class com.google.test.metric.RegExpWhiteList
	 */
	public void testRegExpWhiteList882() throws Exception {
		RegExpWhiteList var2762 = new RegExpWhiteList("String");
		var2762.isClassWhiteListed("java.lang.String");
		var2762.isClassWhiteListed("com.company.String");
		var2762.addPackage("javax.");
	}

	/**
	 * Test method for the class com.google.test.metric.RegExpWhiteList
	 */
	public void testRegExpWhiteList883() throws Exception {
		RegExpWhiteList var2760 = new RegExpWhiteList("java.");
		var2760.isClassWhiteListed("java.lang.String");
		var2760.isClassWhiteListed("com.company.String");
		var2760.addPackage("javax.");
	}

	/**
	 * Test method for the class com.google.test.metric.RegExpWhiteList
	 */
	public void testRegExpWhiteList884() throws Exception {
		RegExpWhiteList var2760 = new RegExpWhiteList("java.");
		var2760.isClassWhiteListed("java.lang.String");
		var2760.isClassWhiteListed("java.x.Z");
		var2760.isClassWhiteListed("java.lang.String");
		var2760.isClassWhiteListed("java.x.Z");
	}
}