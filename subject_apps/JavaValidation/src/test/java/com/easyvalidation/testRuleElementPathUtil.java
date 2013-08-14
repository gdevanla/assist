package com.easyvalidation;

import static org.junit.Assert.assertEquals;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.junit.Test;

import com.easyvalidation.xml.config.EasyValidationXmlConfiguration;
import com.easyvalidation.xml.util.RuleElementPathUtil;

public class testRuleElementPathUtil {
	@Test
	public void testValidationName()
	{
		try
		{
			String value;
		EasyValidationXmlConfiguration configuration = new EasyValidationXmlConfiguration();
		configuration.setAutoSave(false);
		configuration.clear();
		configuration.setValidating(true);
		configuration.load("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		value= configuration.getString(RuleElementPathUtil.getValidationName(0));
		assertEquals("userFormValidation", value);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testRuleType1()
	{
		try
		{
		String value;
		EasyValidationXmlConfiguration configuration = new EasyValidationXmlConfiguration();
		configuration.setAutoSave(false);
		configuration.clear();
		configuration.setValidating(true);
		configuration.load("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		HierarchicalConfiguration subConfig = configuration
		.configurationAt(RuleElementPathUtil
				.getValidation(0));

		value= subConfig.getString(RuleElementPathUtil
				.getRuleType(0));
		assertEquals("required", value);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void testGetRuleType()
	{
		try
		{
		String value;
		EasyValidationXmlConfiguration configuration = new EasyValidationXmlConfiguration();
		configuration.setAutoSave(false);
		configuration.clear();
		configuration.setValidating(true);
		configuration.load("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		HierarchicalConfiguration subConfig = configuration
		.configurationAt(RuleElementPathUtil
				.getValidation(0));

		value= subConfig.getString(RuleElementPathUtil
				.getRuleMessage(1));
		assertEquals("${fieldName} should be between ${min} and ${max}", value);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void testGetRuleMax()
	{
		try
		{
		String value;
		EasyValidationXmlConfiguration configuration = new EasyValidationXmlConfiguration();
		configuration.setAutoSave(false);
		configuration.clear();
		configuration.setValidating(true);
		configuration.load("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		HierarchicalConfiguration subConfig = configuration
		.configurationAt(RuleElementPathUtil
				.getValidation(0));

		value= subConfig.getString(RuleElementPathUtil
				.getRuleMax(1));
		assertEquals("10", value);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
