# Introduction
- This project represents the basic example of Selenium implementation with Java.
- Its is a solid start project and contains some good practises e.g. implementing config file for better project configuration handling in future
## Technologies
- Project is using Selenium Web driver in TestNG automation framework
- maven installation is performed 
- ChromeDriver installation is performed (bee aware of ChromeDriver and browser version - those versions needs to match)
- in case of older browser usage, download corresponding ChromeDriver and place it inside /tools folder
## Launch
- in order to launch tests perform right click on RunTestSuite.xml file
- after test is done, report will be available inside of test/resources folder (TestReport.html)
- use configuration file (parameters.config) for setting parameter HEADLESS to true/false depending on what browser mode you want to run tests against (change parameter value, save the file and run again tests - RunTestSuite.xml)
### versions
- java version Java JDK 1.8.0_301
- client driver selenium-java-4.5.3
- maven-3.8.6
- Chrome version 107.0.5304.88
- ChromeDriver version 107.0.5304.62