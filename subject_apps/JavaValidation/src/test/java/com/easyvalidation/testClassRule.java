package com.easyvalidation;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.math.IntRange;
import org.junit.Test;

import com.easyvalidation.dto.Rule;
import com.easyvalidation.rules.IRule;
import com.easyvalidation.rules.RangeRule;

public class testClassRule {
	@Test
	public void testPopulateRule()
	{
		Rule rule=new Rule("int");
		
		rule.setKey("Field");
		rule.setProperty("Value");
		rule.setMessage("${fieldName} is not between ${min} and ${max}");
		rule.setMin("5");
		rule.setMax("10");
		rule.setUseAttributePlaceHolder(true);
		rule.populateRule();
		rule.transformMessage("EN");
		assertEquals("Value is not between 5 and 10",rule.getMessage());
	}
	@Test
	public void testPopulateRule1()
	{
		PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration();
		propertiesConfiguration.setAutoSave(false);
		propertiesConfiguration.clear();
		try {
			propertiesConfiguration.load("I:\\proj30\\JavaValidation\\src\\test\\resources\\error_messages_fr.properties");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, PropertiesConfiguration> propertiesMap = new HashMap<String, PropertiesConfiguration>();
		propertiesMap.put("FN",propertiesConfiguration);
		Rule rule=new Rule("int");
		
		rule.setKey("Field.int");
		rule.setProperty("Field");
		rule.setPropertiesMap(propertiesMap);
		
		//rule.setMessage("${fieldName} is not between ${min} and ${max}");
		rule.setMin("5");
		rule.setMax("10");
		rule.setUseAttributePlaceHolder(false);
		rule.populateRule();
		rule.transformMessage("FN");
		assertEquals("int is not valid",rule.getMessage());
	}
	
	@Test
	public void testRule()
	{
		Rule testRule=new Rule("int");
		assertEquals(true,testRule!=null);
		
	}
}
