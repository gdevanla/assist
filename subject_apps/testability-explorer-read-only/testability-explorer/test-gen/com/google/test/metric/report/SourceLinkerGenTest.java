package com.google.test.metric.report;


import java.lang.String;
import junit.framework.TestCase;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;

public class SourceLinkerGenTest extends TestCase {
	/**
	 * Test method for the class com.google.test.metric.report.SourceLinker
	 */
	public void testSourceLinker1225() throws Exception {
		SourceLinker var3883 = new SourceLinker("", "");
		var3883.buildClassLink("//a.java", "fin.FinUI");
		var3883.buildClassLink("//class.java", "class$Conv");
		var3883.buildLineLink("//class.java", 1234, " void methodA()");
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceLinker
	 */
	public void testSourceLinker1226() throws Exception {
		String var3885 = "pre{path}#{line}";
		String var3886 = "pre{path}";
		SourceLinker var3887 = new SourceLinker(var3885, var3886);
		var3887.buildClassLink("", "anchor");
		var3887.buildLineLink("", 1000, "anchor");
		var3887.buildClassLink("", "anchor");
		var3887.buildLineLink("", 1000, "anchor");
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceLinker
	 */
	public void testSourceLinker1227() throws Exception {
		String var3885 = "pre{path}#{line}";
		String var3886 = "pre{path}";
		SourceLinker var3887 = new SourceLinker(var3885, var3886);
		var3887.buildClassLink("//a.java", "fin.FinUI");
		var3887.buildClassLink("//class.java", "class$Conv");
		var3887.buildClassLink("//a.java", "fin.FinUI");
		var3887.buildClassLink("//class.java", "class$Conv");
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceLinker
	 */
	public void testSourceLinker1228() throws Exception {
		SourceLinker var3889 = new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
		var3889.buildClassLink("//a.java", "fin.FinUI");
		var3889.buildClassLink("//class.java", "class$Conv");
		var3889.buildClassLink("", "anchor");
		var3889.buildLineLink("", 1000, "anchor");
	}

	/**
	 * Test method for the class com.google.test.metric.report.SourceLinker
	 */
	public void testSourceLinker1229() throws Exception {
		SourceLinker var3889 = new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
		var3889.buildLineLink("//class.java", 1234, " void methodA()");
		var3889.buildLineLink("//class.java", 1234, " void methodA()");
	}
}