package com.easyvalidation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.junit.Test;

import com.easyvalidation.common.Constants;
import com.easyvalidation.common.ErrorMessages;
import com.easyvalidation.dto.Rule;
import com.easyvalidation.exception.ValidationException;
import com.easyvalidation.rules.IRule;
import com.easyvalidation.rules.impl.ExpressionRule;
import com.easyvalidation.util.Utils;
import com.easyvalidation.xml.common.Nodes;
import com.easyvalidation.xml.config.EasyValidationXmlConfiguration;
import com.easyvalidation.xml.processor.XMLProcessor;
import com.easyvalidation.xml.util.RuleElementPathUtil;
import com.easyvalidation.dto.Error;

public class TestXmlProcessor {
	@Test (expected=ValidationException.class)
	public void testParseXML1()
	{
		Map testRule=new HashMap<String, List<Rule>>();
		testRule=XMLProcessor.parseXML(("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml rule2.xml").split(Constants.SPACE_STR));
		assertEquals(true,testRule.isEmpty());
	}
	@Test
	public void testParseXML2()
	{
		Map testRule=new HashMap<String, List<Rule>>();
		testRule=XMLProcessor.parseXML(("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml").split(Constants.SPACE_STR));
		assertEquals(false,testRule.isEmpty());
	}
	@Test //(expected=ValidationException.class)
	public void testParseXML3()
	{
		Map testRule=new HashMap<String, List<Rule>>();
		testRule=XMLProcessor.parseXML(("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml").split(Constants.SPACE_STR));
		assertEquals(false,testRule.isEmpty());
	}
	
	
	
}
