package com.easyvalidation;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.HashMap;
import java.util.Map;


import org.junit.Test;

import com.easyvalidation.xml.config.EasyValidationXmlConfiguration;
import com.easyvalidation.xml.util.RuleElementPathUtil;
import com.easyvalidation.dto.Error;

public class testEasyValidationimpl {
	@Test
	public void testDateRangeRule1()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("startdatetest1",(Object)"01.04.2013");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testDateRangeRule2()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("startdatetest2",(Object)"01.01.2013");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testDateRangeRule3()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("startdatetest3",(Object)"01.04.2013");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testDateRangeRule4()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("startdatetest1",(Object)"01.01.2013");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("Date is not within range",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testDateRangeRule5()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("startdatetest1",(Object)"01.06.2013");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("Date is not within range",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testDateRangeRule6()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("startdatetest1",(Object)"");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testExpressionRule()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("id",(Object)"abc789");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("It is not valud id",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testNumberRangeRule()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("number",(Object)"9");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testDoubleRangeRule()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("CGPA",(Object)"11");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("Value is not between 5.0 and 10.0",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testRequiredRule1()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("firstName",(Object)"Kevin");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testRequiredRule2()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("firstName",(Object)"");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("FirstName is required",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testStringLengthRule1()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("firstName",(Object)"Kevin");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testStringLengthRule3()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("firstName",(Object)"");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(false,errorMessageList.isEmpty());
		//if(!errorMessageList.isEmpty())
			//System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testStringLengthRule2()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("firstName",(Object)"Paul");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("firstName should be between 5 and 10",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testStringLengthRule4()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("firstName1",(Object)"Paul");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testStringLengthRule5()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("firstName2",(Object)"Paul");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("Value should be greater than 5",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testStringLengthRule6()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("firstName",(Object)"Paul Adamsss");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("firstName should be between 5 and 10",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testURLRule1()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("urlvalue",(Object)"http://www.google.com");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testURLRule2()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("urlvalue",(Object)"http://www.google");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("URL is not valid",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testURLRule3()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("urlvalue",(Object)"");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testPercentRule1()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("percentage",(Object)"11.2");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testPercentRule2()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("percentage",(Object)"11.2.2");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("Percentage value is not valid",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testPercentRule3()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("percentage",(Object)"");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testISBNRule1()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("isbn",(Object)"8175257660");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testISBNRule2()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("isbn",(Object)"7854698715");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("ISBN is not valid",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testINETAddrRule1()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("ipaddress",(Object)"127.0.0.1");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testINETAddrRule2()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("ipaddress",(Object)"127.0.0.1.2");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("Inetaddress is not valid",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testEmailRule1()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("mailid",(Object)"abc@def.com.in");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testEmailRule2()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("mailid",(Object)"abc@_def.com.in");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("emailid is not valid",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testCurrencyRule1()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("amount",(Object)"100");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testCurrencyRule2()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("amount",(Object)"a100");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("Amount is not valid",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testCreditCardRule1()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("creditcardnumber",(Object)"4408041234567893");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals(true,errorMessageList.isEmpty());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testCreditCardRule2()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("creditcardnumber",(Object)"1459787753684624");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("Credit card is not valid",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
}
