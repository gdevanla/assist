package com.google.test.metric;


import com.google.test.metric.ConfigModule;
import com.google.test.metric.ReportGeneratorProvider.ReportFormat;
import com.google.test.metric.CommandLineConfig;
import java.util.List;
import com.google.inject.Injector;
import java.lang.String;
import com.google.inject.Guice;
import java.io.PrintStream;
import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;

public class JavaTestabilityModuleGenTest extends TestCase {
	public CommandLineConfig instantiateCommandLineConfig452() {
		ByteArrayOutputStream var2854 = new ByteArrayOutputStream();
		ByteArrayOutputStream var2855 = new ByteArrayOutputStream();
		return new CommandLineConfig(new PrintStream(var2854), new PrintStream(
				var2855));
	}

	/**
	 * Test method for the class com.google.test.metric.JavaTestabilityModule
	 */
	public void testJavaTestabilityModule915() throws Exception {
		CommandLineConfig var2856 = instantiateCommandLineConfig452();
		ByteArrayOutputStream var2854 = new ByteArrayOutputStream();
		ByteArrayOutputStream var2855 = new ByteArrayOutputStream();
		var2857 = new CommandLineConfig(new PrintStream(var2854),
				new PrintStream(var2855));
		JavaTestabilityModule var2858 = new JavaTestabilityModule(var2856);
		var2856.validate();
		var2856.validate();
		var2858.getEntryList();
		var2858.getFormat();
		var2858.getErr();
		var2858.getPrintDepth();
		var2858.getFormat();
	}

	public CommandLineConfig instantiateCommandLineConfig453() {
		PrintStream var2861 = new PrintStream(new ByteArrayOutputStream());
		return new CommandLineConfig(null, var2861);
	}

	/**
	 * Test method for the class com.google.test.metric.JavaTestabilityModule
	 */
	public void testJavaTestabilityModule916() throws Exception {
		CommandLineConfig var2862 = instantiateCommandLineConfig453();
		ByteArrayOutputStream var2863 = new ByteArrayOutputStream();
		PrintStream var2864 = new PrintStream(var2863);
		ByteArrayOutputStream var2865 = new ByteArrayOutputStream();
		PrintStream var2866 = new PrintStream(var2865);
		Injector var2867 = Guice
				.createInjector(new ConfigModule(
						new String[] { "-cp", "not/default/path",
								"com.google.FirstClass com.google.second.package com.google.third.package" },
						var2864, var2866));
		JavaTestabilityModule var2868 = new JavaTestabilityModule(var2862);
		var2862.validate();
		var2862.validate();
		var2868.getFormat();
		var2868.getFormat();
	}

	public CommandLineConfig instantiateCommandLineConfig454() {
		ByteArrayOutputStream var2854 = new ByteArrayOutputStream();
		ByteArrayOutputStream var2855 = new ByteArrayOutputStream();
		return new CommandLineConfig(new PrintStream(var2854), new PrintStream(
				var2855));
	}

	/**
	 * Test method for the class com.google.test.metric.JavaTestabilityModule
	 */
	public void testJavaTestabilityModule917() throws Exception {
		CommandLineConfig var2869 = instantiateCommandLineConfig454();
		ByteArrayOutputStream var2854 = new ByteArrayOutputStream();
		ByteArrayOutputStream var2855 = new ByteArrayOutputStream();
		var2857 = new CommandLineConfig(new PrintStream(var2854),
				new PrintStream(var2855));
		JavaTestabilityModule var2870 = new JavaTestabilityModule(var2869);
		var2869.validate();
		var2869.validate();
		var2870.getEntryList();
		var2870.getFormat();
		var2870.getErr();
		var2870.getPrintDepth();
		var2870.getEntryList();
		var2870.getFormat();
		var2870.getErr();
		var2870.getPrintDepth();
	}

	public CommandLineConfig instantiateCommandLineConfig455() {
		PrintStream var2861 = new PrintStream(new ByteArrayOutputStream());
		return new CommandLineConfig(null, var2861);
	}

	/**
	 * Test method for the class com.google.test.metric.JavaTestabilityModule
	 */
	public void testJavaTestabilityModule918() throws Exception {
		CommandLineConfig var2871 = instantiateCommandLineConfig455();
		ByteArrayOutputStream var2863 = new ByteArrayOutputStream();
		PrintStream var2864 = new PrintStream(var2863);
		ByteArrayOutputStream var2865 = new ByteArrayOutputStream();
		PrintStream var2866 = new PrintStream(var2865);
		Injector var2867 = Guice
				.createInjector(new ConfigModule(
						new String[] { "-cp", "not/default/path",
								"com.google.FirstClass com.google.second.package com.google.third.package" },
						var2864, var2866));
		JavaTestabilityModule var2872 = new JavaTestabilityModule(var2871);
		var2871.validate();
		var2871.validate();
		var2872.getEntryList();
		var2872.getFormat();
		var2872.getErr();
		var2872.getPrintDepth();
		var2872.getEntryList();
		var2872.getFormat();
		var2872.getErr();
		var2872.getPrintDepth();
	}

	public CommandLineConfig instantiateCommandLineConfig456() {
		ByteArrayOutputStream var2854 = new ByteArrayOutputStream();
		ByteArrayOutputStream var2855 = new ByteArrayOutputStream();
		return new CommandLineConfig(new PrintStream(var2854), new PrintStream(
				var2855));
	}

	/**
	 * Test method for the class com.google.test.metric.JavaTestabilityModule
	 */
	public void testJavaTestabilityModule919() throws Exception {
		CommandLineConfig var2873 = instantiateCommandLineConfig456();
		ByteArrayOutputStream var2854 = new ByteArrayOutputStream();
		ByteArrayOutputStream var2855 = new ByteArrayOutputStream();
		var2857 = new CommandLineConfig(new PrintStream(var2854),
				new PrintStream(var2855));
		JavaTestabilityModule var2874 = new JavaTestabilityModule(var2873);
		var2873.validate();
		var2873.validate();
		var2874.getFormat();
		var2874.getFormat();
	}
}