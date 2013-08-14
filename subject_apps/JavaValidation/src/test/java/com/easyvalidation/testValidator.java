/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.easyvalidation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.easyvalidation.common.Constants;
import com.easyvalidation.common.ErrorMessages;
import com.easyvalidation.dto.Rule;
import com.easyvalidation.exception.ValidationException;
import com.easyvalidation.rules.IRule;
import com.easyvalidation.rules.impl.ExpressionRule;
import com.easyvalidation.util.Utils;
import com.easyvalidation.xml.processor.XMLProcessor;
import com.easyvalidation.dto.Error;


public class testValidator {
	
	@Test
	public void testcheckValidations()
	{
		List<Error> errorMessageList = null;
		Validator.initialize("I:\\proj30\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
		Map testRule=new HashMap<String,Object>();
		testRule.put("firstName",(Object)"");
		assertEquals(true,Validator.checkValidations("userFormValidation", testRule)!=null);
		//if(!errorMessageList.isEmpty())
			//System.out.println(errorMessageList.get(0).getMessage());
	}
	
	@Test
	public void testCheckValidatorLocale()
	{
		List<Error> errorMessageList = null;
		Map testRule1=new HashMap<String,Object>();
		testRule1.put("firstName",(Object)"ASDFASDFASDFA");
		errorMessageList=Validator.checkValidations("userFormValidation", testRule1 ,"FR");
		if(!errorMessageList.isEmpty())
			System.out.println(errorMessageList.get(0).getMessage());
	}
	
	
}
