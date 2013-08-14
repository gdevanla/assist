IDE Used
========
The package was tested using Eclipse IDE and JUnit 4

Classes and Methods that are being Tested
=========================================
UNIT TESTS

1. Report.java
	checks the constructor whether it calls the right command by comparing two files or two String arrays.
	Calls the LineInfo Constructor by setting the LineStatus to 1,2,3 respectively for old,New,match commands for the two input Files or String arrays
	Report returns Match command when two lines of the two input files match
	Report returns Delete command when new file line does not contain old file line
	Report returns Change command when new file line has a differnt text in the same line of the old File
	Report returns Insert Before command when new file contains new text between two lines of old file
	Report returns Append command when new file contains new text at the end

2. LineInfo.java
	Methods Tested
		isEof() checks the Happy and Sad path for this function to check it returns true when lineStatus is -1 
		isMatch() check whther this function returns true when lineStatus is 3
		isNewOnly() check whther this function returns true when lineStatus is 2
		isOldOnly() check whther this function returns true when lineStatus is 1
		setBlockNumber() check whether this functions returns uniquematch flag when it is called


3. FileInfo.java
	Methods Tested
		nextBlock = For each line checks whether the nextBlock(which acts like an iterator) writtens the correct line NUmber 
		currentLineInfo = Oracles for this checks whether the nextblock and the current line number iterates correctly
		isValidNumber= checks whether line number is valid


4. TextFileIn.Java
	checks the asArray methods checks whether a given input file is converted into an String Array with each line of the File being the element in the Array.

I have not written any tests for MatchCommand.java,DeleteCommand.java,AppendCommand.java, InsertCommand.java, MoveCommand.Java as these are just constructor calls



AcceptanceTest
=============
TextDiff.java
	Checks various inputs for the TextDiff Application
	1. Both files Empty- does not return any command
	2. Old File Empty- returns Append command
	3. New File Empty - returns Delete Command
	4. Whem both files exactly matched- returns Match Command
	5. Old File line is in different line of new File- return move command
	6. New File contains new lines in between the lines of old File- return Insert Before
	7. Line of Text is appeared changed from old File- returns Change Command
	8  Checks for various combinations of inputs from 1 to 7 and checks whther the right command is returned.



