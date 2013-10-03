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

package com.easyvalidation.xml.config;

import java.io.InputStream;

import org.apache.commons.configuration.XMLConfiguration;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class EasyValidationXmlConfiguration extends XMLConfiguration {

	private static final long serialVersionUID = 1773880388928412423L;
	
	
	 

	@Override
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException {
	
		if (systemId.contains("easy-validation.dtd")) {
			InputStream is = EasyValidationXmlConfiguration.class
					.getResourceAsStream("/easy-validation.dtd");
			return new InputSource(is);
		} else {
			return null; //super.resolveEntity(publicId, systemId);
		}
	}
}