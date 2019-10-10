This is a (very) simple Java application


Instructions using Maven:
------------------------

To compile:
  mvn compile

To run using exec plugin:
  mvn exec:java

To generate launch scripts for Windows and Linux:
  (mvn appassembler:assemble is attached to install phase)
  mvn install

To run using appassembler plugin (pom.xml file must be configured. The pom.xml still have bugs for this plugin ;) ):
  On Windows:
    target\appassembler\bin\java-app test 1 2 3
  On Linux:
    ./target/appassembler/bin/java-app test 1 2 3


Or, run as an application:
	java -cp target/my-app-1.0-SNAPSHOT.jar pt.ulusofona.cd.App test 1 2 3

To configure the Maven project in Eclipse:
-----------------------------------------

'File', 'Import...', 'Maven'-'Existing Maven Projects'
'Select root directory' and 'Browse' to the project base folder.
Check that the desired POM is selected and 'Finish'.


