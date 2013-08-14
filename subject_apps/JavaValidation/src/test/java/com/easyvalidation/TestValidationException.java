package com.easyvalidation;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.easyvalidation.dto.Error;
import com.easyvalidation.exception.ValidationException;

public class TestValidationException {
	@Test //(expected=ValidationException.class)
	public void testValidationException1()
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
	
	@Test(expected=ValidationException.class)
	public void testValidationException2()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules_Validation.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("firstName",(Object)"Paul");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		
		//if(!errorMessageList.isEmpty())
		//	System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test(expected=ValidationException.class)
	public void testValidationException3()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules_Validation.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("firstName2",(Object)"");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule,"CN");
		
		//if(!errorMessageList.isEmpty())
		//	System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test(expected=ValidationException.class)
	public void testValidationException4()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules_Validation.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("startdatetest1",(Object)"40.13.2013");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule);
		assertEquals("Date is not within range",errorMessageList.get(0).getMessage());
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	
}
