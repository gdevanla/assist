package com.google.test.metric;


import com.google.test.metric.VariableState;
import com.google.test.metric.LocalVariableState;
import com.google.test.metric.Variable;
import java.lang.String;
import junit.framework.TestCase;
import com.google.test.metric.FieldInfo;

public class LocalVariableStateGenTest extends TestCase {
	public LocalVariableState instantiateLocalVariableState482() {
		VariableState var2995 = new VariableState();
		return new LocalVariableState(var2995);
	}

	/**
	 * Test method for the class com.google.test.metric.LocalVariableState
	 */
	public void testLocalVariableState960() throws Exception {
		LocalVariableState var2996 = instantiateLocalVariableState482();
		VariableState var2995 = new VariableState();
		LocalVariableState var2997 = new LocalVariableState(var2995);
		FieldInfo var2998 = new FieldInfo(null, "field", null, false, false,
				false);
		var2996.setGlobal(var2998);
		var2996.setInjectable(var2998);
		Variable var3000 = new Variable("lod", null, false, false);
		var2996.setLoDCount(var3000, 123);
		var2996.getLoDCount(var3000);
		Variable var3001 = new Variable("var", null, false, false);
		var2996.getLoDCount(var3001);
		var2997.setGlobal(var3001);
		var2997.setInjectable(var2998);
		var2997.setLoDCount(var3000, 123);
		var2997.toString();
		var2997.setGlobal(var3001);
		var2997.setInjectable(var2998);
		var2997.setLoDCount(var3000, 123);
		var2997.toString();
	}

	public LocalVariableState instantiateLocalVariableState483() {
		VariableState var2995 = new VariableState();
		return new LocalVariableState(var2995);
	}

	/**
	 * Test method for the class com.google.test.metric.LocalVariableState
	 */
	public void testLocalVariableState961() throws Exception {
		LocalVariableState var3002 = instantiateLocalVariableState483();
		VariableState var2995 = new VariableState();
		LocalVariableState var2997 = new LocalVariableState(var2995);
		FieldInfo var2998 = new FieldInfo(null, "field", null, false, false,
				false);
		var3002.setGlobal(var2998);
		var3002.setInjectable(var2998);
		var3002.setGlobal(var2998);
		var3002.setInjectable(var2998);
		Variable var3001 = new Variable("var", null, false, false);
		var2997.setGlobal(var3001);
		var2997.setInjectable(var3001);
		var2997.isGlobal(var3001);
		var2997.isInjectable(var3001);
		var2997.setGlobal(var3001);
		var2997.setInjectable(var3001);
		var2997.isGlobal(var3001);
		var2997.isInjectable(var3001);
	}

	public LocalVariableState instantiateLocalVariableState484() {
		VariableState var2995 = new VariableState();
		return new LocalVariableState(var2995);
	}

	/**
	 * Test method for the class com.google.test.metric.LocalVariableState
	 */
	public void testLocalVariableState962() throws Exception {
		LocalVariableState var3003 = instantiateLocalVariableState484();
		VariableState var2995 = new VariableState();
		LocalVariableState var2997 = new LocalVariableState(var2995);
		Variable var3001 = new Variable("var", null, false, false);
		var3003.setGlobal(var3001);
		FieldInfo var2998 = new FieldInfo(null, "field", null, false, false,
				false);
		var3003.setInjectable(var2998);
		Variable var3000 = new Variable("lod", null, false, false);
		var3003.setLoDCount(var3000, 123);
		var3003.toString();
		var3003.setGlobal(var3001);
		var3003.setInjectable(var2998);
		var3003.setLoDCount(var3000, 123);
		var3003.toString();
		var2997.setGlobal(var3001);
		var2997.setInjectable(var2998);
		var2997.setLoDCount(var3000, 123);
		var2997.toString();
		var2997.setGlobal(var3001);
		var2997.setInjectable(var3001);
		var2997.isGlobal(var3001);
		var2997.isInjectable(var3001);
	}

	public LocalVariableState instantiateLocalVariableState485() {
		VariableState var2995 = new VariableState();
		return new LocalVariableState(var2995);
	}

	/**
	 * Test method for the class com.google.test.metric.LocalVariableState
	 */
	public void testLocalVariableState963() throws Exception {
		LocalVariableState var3004 = instantiateLocalVariableState485();
		VariableState var2995 = new VariableState();
		LocalVariableState var2997 = new LocalVariableState(var2995);
		Variable var3000 = new Variable("lod", null, false, false);
		var3004.setLoDCount(var3000, 123);
		var3004.getLoDCount(var3000);
		Variable var3001 = new Variable("var", null, false, false);
		var3004.getLoDCount(var3001);
		var3004.setLoDCount(var3000, 123);
		var3004.getLoDCount(var3000);
		var3004.getLoDCount(var3001);
		var2997.setGlobal(var3001);
		FieldInfo var2998 = new FieldInfo(null, "field", null, false, false,
				false);
		var2997.setInjectable(var2998);
		var2997.setLoDCount(var3000, 123);
		var2997.toString();
		var2997.setGlobal(var3001);
		var2997.setInjectable(var2998);
		var2997.setLoDCount(var3000, 123);
		var2997.toString();
	}

	public LocalVariableState instantiateLocalVariableState486() {
		VariableState var2995 = new VariableState();
		return new LocalVariableState(var2995);
	}

	/**
	 * Test method for the class com.google.test.metric.LocalVariableState
	 */
	public void testLocalVariableState964() throws Exception {
		LocalVariableState var3005 = instantiateLocalVariableState486();
		VariableState var2995 = new VariableState();
		LocalVariableState var2997 = new LocalVariableState(var2995);
		Variable var3000 = new Variable("lod", null, false, false);
		var3005.setLoDCount(var3000, 123);
		var3005.getLoDCount(var3000);
		Variable var3001 = new Variable("var", null, false, false);
		var3005.getLoDCount(var3001);
		var3005.setLoDCount(var3000, 123);
		var3005.getLoDCount(var3000);
		var3005.getLoDCount(var3001);
		FieldInfo var2998 = new FieldInfo(null, "field", null, false, false,
				false);
		var2997.setGlobal(var2998);
		var2997.setInjectable(var2998);
		var2997.setLoDCount(var3000, 123);
		var2997.getLoDCount(var3000);
		var2997.getLoDCount(var3001);
	}
}