package com.google.test.metric.report.html;


import com.google.test.metric.report.SourceLinker;
import java.lang.String;
import junit.framework.TestCase;
import static com.google.test.metric.report.FreemarkerReportGenerator.HTML_REPORT_TEMPLATE;
import static java.text.MessageFormat.format;
import static java.util.ResourceBundle.getBundle;

public class SourceLinkerModelGenTest extends TestCase {
	public SourceLinker instantiateSourceLinker713() {
		return new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
	}

	/**
	 * Test method for the class com.google.test.metric.report.html.SourceLinkerModel
	 */
	public void testSourceLinkerModel1275() throws Exception {
		SourceLinker var3976 = instantiateSourceLinker713();
		SourceLinkerModel var3977 = new SourceLinkerModel(var3976);
		var3976.buildLineLink("//class.java", 1234, " void methodA()");
		var3976.buildClassLink("//a.java", "fin.FinUI");
		var3976.buildClassLink("//class.java", "class$Conv");
	}

	public SourceLinker instantiateSourceLinker714() {
		return new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
	}

	/**
	 * Test method for the class com.google.test.metric.report.html.SourceLinkerModel
	 */
	public void testSourceLinkerModel1276() throws Exception {
		SourceLinker var3979 = instantiateSourceLinker714();
		SourceLinkerModel var3980 = new SourceLinkerModel(var3979);
		var3979.buildClassLink("//a.java", "fin.FinUI");
		var3979.buildClassLink("//class.java", "class$Conv");
		var3979.buildLineLink("//class.java", 1234, " void methodA()");
	}

	public SourceLinker instantiateSourceLinker715() {
		String var3981 = "pre{path}#{line}";
		String var3982 = "pre{path}";
		return new SourceLinker(var3981, var3982);
	}

	/**
	 * Test method for the class com.google.test.metric.report.html.SourceLinkerModel
	 */
	public void testSourceLinkerModel1277() throws Exception {
		SourceLinker var3983 = instantiateSourceLinker715();
		SourceLinkerModel var3984 = new SourceLinkerModel(var3983);
		var3983.buildClassLink("", "anchor");
		var3983.buildLineLink("", 1000, "anchor");
		var3983.buildLineLink("//class.java", 1234, " void methodA()");
	}

	public SourceLinker instantiateSourceLinker716() {
		String var3981 = "pre{path}#{line}";
		String var3982 = "pre{path}";
		return new SourceLinker(var3981, var3982);
	}

	/**
	 * Test method for the class com.google.test.metric.report.html.SourceLinkerModel
	 */
	public void testSourceLinkerModel1278() throws Exception {
		SourceLinker var3986 = instantiateSourceLinker716();
		SourceLinkerModel var3987 = new SourceLinkerModel(var3986);
		var3986.buildClassLink("//a.java", "fin.FinUI");
		var3986.buildClassLink("//class.java", "class$Conv");
		var3986.buildClassLink("", "anchor");
		var3986.buildLineLink("", 1000, "anchor");
	}

	public SourceLinker instantiateSourceLinker717() {
		return new SourceLinker(
				"http://code.repository/basepath/{path}&line={line}",
				"http://code.repository/basepath/{path}");
	}

	/**
	 * Test method for the class com.google.test.metric.report.html.SourceLinkerModel
	 */
	public void testSourceLinkerModel1279() throws Exception {
		SourceLinker var3988 = instantiateSourceLinker717();
		SourceLinkerModel var3989 = new SourceLinkerModel(var3988);
		var3988.buildClassLink("", "anchor");
		var3988.buildLineLink("", 1000, "anchor");
		var3988.buildLineLink("//class.java", 1234, " void methodA()");
	}
}