Nano-XML
==========
NanoXML is a small non-validating parser for Java. 

NOTE: All the tests input xml files are in TestInputFile and AcceptanceInput Folders. 
	and tests are not put in separate folders because of the access modifiers of different classes
IDE used ----- Eclipse

Unit Tests
==========
XMLElementTest.java
	Checks for the Null values for the private fields
	Oracles for checking the Full Name, fullName with SystemID and with NameSpace of the xml element
	Oracles for testing whether PCDATA element is created properly
	Checks for Create Element with Full Name, name space and system Identifier
	checks all the getter methods of the class
	checks whether the child with null and valid elements are "added" to the current element
	checks whether the child with null and valid elements are "inserted" to the current element
	Remove the child elements with valid and null values
	checks for the leaf element
	checks whether an element haschildren
	removing attribute with namespace and without namespace
	checking the equality of the two xml elements		
XMLParserFactoryTest.java
	checks whethr the parser is created with correct class file and correct reader.

EntityResolverTest.java
	checks whether an entity is internal, external, good,  bad
	
StdXMLAttribute.java
	Tests for the attributes default name, full name, value , type and namespace

StdXMLReaderTest.java
	Tests for the valid xml start tag
	tests for the valid Encoding in the xml
	Tests when the file is not found
	Tests for malformed URL
	tests for Readers Public and System Identifiers

StdXMLBuilderTest.java
	parses XML data by building the element with null elelment
	Starts the element builder with valid element
	starts the element when the stack is empty
	starts the element whent the stack is not empty
	checks for the end element of the xml tag
	Add the attribute with and without namespace to the element
	

NonValidatorTest.java
	
	Oracles for Adding an entity to the hashtable
	oracles for entities with public and system ID
	oracles for Parsing Document Type Definitions in xml for both external and internal
	oracles for parsing DTD internal and external with Public and system ID
	
	
Acceptance Test
==============
StdXMLParserAcceptanceTest.java	 
	Tests all private fields
	checks for unexpected entity
	parses the xml document with and without namespace
	parses the attibute in both internal and external DTD
	checks whether the comments are skipped
	checks whether the conditional section is parsed properly
	checks for the valid public id
	checks whether the element is closed properly
	checks for the internal DTD with namespace
	checks for the attribute with namespace
	checks for the namespace as an attribute
	checks the end tag is has right number of characters
	checks for the required, enemurated and all attributes for an xml element

Files that where not tested
=================
All interfaces are not tested
CDATAReader.java, ContentReader.java has just reads and closes the reader
PIReader.java for implementing additional reader other than standard reader
ValidatorPlugin.java for additional Validators as this just calls all the NON-Validator.java methods
XMLParseException.java, XMLValidationException.java as these are just constructor calls extending Java Exception Class
XUtil.java utilities functions used in Nano-XML and since the access modifier of the class is not public
XMLWriter.java as this just write the output to the console.