package com.easyvalidation;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;

import com.easyvalidation.dto.Error;
import com.easyvalidation.exception.ValidationException;

public class TestValidator{

	public static void main(String args[])
	{	
		try
		{
			String value;
			//Validator testValidator=new Validator();
			List<Error> errorMessageList = null;
			InputStreamReader converter = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(converter);
			Validator.initialize("I:\\proj40\\JavaValidation\\src\\test\\java\\com\\easyvalidation\\TestRules.xml");
			while(true)
			{
				Map testRule=new HashMap<String,Object>();
				System.out.print("Enter Firstname:");  //Give empty value or length not between 5 or 10
				value=in.readLine();
				testRule.put("firstName",(Object)value);
				errorMessageList=Validator.checkValidations("userFormValidation", testRule);
				if(!errorMessageList.isEmpty())
					System.out.println(errorMessageList.get(0).getMessage());
				else
					break;
				errorMessageList=null;
			}
			while(true)
			{
				System.out.print("Enter Secondname:"); //Give empty value or length not between 5 or 10
				value=in.readLine();
				Map testRule1=new HashMap<String,Object>();
				testRule1.put("firstName",(Object)value);
				errorMessageList=Validator.checkValidations("userFormValidation", testRule1 ,"FR");
				if(!errorMessageList.isEmpty())
					System.out.println(errorMessageList.get(0).getMessage());
				else
					break;
				errorMessageList=null;
			}
			while(true)
			{
			System.out.print("Enter mail id:"); //Enter invalid mail id
			value=in.readLine();
			Map testRule2=new HashMap<String,Object>();
			testRule2.put("mailid",(Object)value);
			errorMessageList=Validator.checkValidations("userFormValidation", testRule2);
			if(!errorMessageList.isEmpty())
				System.out.println(errorMessageList.get(0).getMessage());
			else
				break;
			errorMessageList=null;
			}
			while(true)
			{
			System.out.print("Enter age:"); //Give age not between 18 and 30
			value=in.readLine();
			Map testRule3=new HashMap<String,Object>();
			testRule3.put("age",(Object)value);
			errorMessageList=Validator.checkValidations("userFormValidation", testRule3);
			if(!errorMessageList.isEmpty())
				System.out.println(errorMessageList.get(0).getMessage());
			else
				break;
			errorMessageList=null;
			}
			while(true)
			{
			System.out.print("Enter delivery date:"); //Enter date lies other than april
			value=in.readLine();
			Map testRule4=new HashMap<String,Object>();
			testRule4.put("deliverydate",(Object)value);
			errorMessageList=Validator.checkValidations("userFormValidation", testRule4);
			if(!errorMessageList.isEmpty())
				System.out.println(errorMessageList.get(0).getMessage());
			else
				break;
			errorMessageList=null;
			}
			while(true)
			{
			System.out.print("Enter ip address:"); //Enter invalid ip address
			value=in.readLine();
			Map testRule5=new HashMap<String,Object>();
			testRule5.put("ipaddress",(Object)value);
			errorMessageList=Validator.checkValidations("userFormValidation", testRule5);
			if(!errorMessageList.isEmpty())
				System.out.println(errorMessageList.get(0).getMessage());
			else
				break;
			errorMessageList=null;
			}
			while(true)
			{
				System.out.print("Enter CGPA:"); //Enter invalid invalid double value
				value=in.readLine();
				Map testRule6=new HashMap<String,Object>();
				testRule6.put("CGPA",(Object)value);
				errorMessageList=Validator.checkValidations("userFormValidation", testRule6);
				if(!errorMessageList.isEmpty())
					System.out.println(errorMessageList.get(0).getMessage());
				else
					break;
				errorMessageList=null;
			}
			while(true)
			{
				System.out.print("Enter webaddress:"); //Enter invalid webaddress
				value=in.readLine();
				Map testRule7=new HashMap<String,Object>();
				testRule7.put("webaddress",(Object)value);
				errorMessageList=Validator.checkValidations("userFormValidation", testRule7);
				if(!errorMessageList.isEmpty())
					System.out.println(errorMessageList.get(0).getMessage());
				else
					break;
				errorMessageList=null;
			}
			while(true)
			{
				System.out.print("Enter percentage:"); //Enter invalid value
				value=in.readLine();
				Map testRule8=new HashMap<String,Object>();
				testRule8.put("percentage",(Object)value);
				errorMessageList=Validator.checkValidations("userFormValidation", testRule8);
				if(!errorMessageList.isEmpty())
					System.out.println(errorMessageList.get(0).getMessage());
				else
					break;
				errorMessageList=null;
			}
			while(true)
			{
				System.out.print("Enter amount to be paid:"); //Enter invalid value
				value=in.readLine();
				Map testRule9=new HashMap<String,Object>();
				testRule9.put("amount",(Object)value);
				errorMessageList=Validator.checkValidations("userFormValidation", testRule9);
				if(!errorMessageList.isEmpty())
					System.out.println(errorMessageList.get(0).getMessage());
				else
					break;
				errorMessageList=null;
			}
			while(true)
			{
				System.out.print("Enter isbn number:"); //Enter invalid value
				value=in.readLine();
				Map testRule10=new HashMap<String,Object>();
				testRule10.put("isbn",(Object)value);
				errorMessageList=Validator.checkValidations("userFormValidation", testRule10);
				if(!errorMessageList.isEmpty())
					System.out.println(errorMessageList.get(0).getMessage());
				else
					break;
				errorMessageList=null;
			}
			while(true)
			{
				System.out.print("Enter credit card number:"); //Enter invalid credit card number
				value=in.readLine();
				Map testRule11=new HashMap<String,Object>();
				testRule11.put("creditcardnumber",(Object)value);
				errorMessageList=Validator.checkValidations("userFormValidation", testRule11);
				if(!errorMessageList.isEmpty())
					System.out.println(errorMessageList.get(0).getMessage());
				else
					break;
				errorMessageList=null;
			}
			while(true)
			{
				System.out.print("Enter user id:"); //Enter invalid credit card number
				value=in.readLine();
				Map testRule12=new HashMap<String,Object>();
				testRule12.put("id",(Object)value);
				errorMessageList=Validator.checkValidations("userFormValidation", testRule12);
				if(!errorMessageList.isEmpty())
					System.out.println(errorMessageList.get(0).getMessage());
				else
					break;
				errorMessageList=null;
			}
		}
		catch(Exception ex)
		{

		}
	}
}