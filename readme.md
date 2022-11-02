# Introduction
- This project represents the basic example of Selenium implementation with Java.
- Its is a solid start project and contains some good practises e.g. implementing config file for better project configuration handling in future
## Technologies
- Project uses Selenium Web driver in TestNG automation framework
- ChromeDriver is used for browser automation (be aware of ChromeDriver and browser version - those versions need to match)
- In case of older browser usage, download corresponding ChromeDriver and place it inside /tools folder
## Launch
- In order to launch tests perform right click on RunTestSuite.xml file
- After test is done, report will be available inside of test/resources folder (TestReport.html)
- Use configuration file (parameters.config) for setting parameter HEADLESS to true/false depending on what browser mode you want to run tests against (change parameter value, save the file and run again tests - RunTestSuite.xml)
### Versions
- java version Java JDK 1.8.0_301
- client driver selenium-java-4.5.3
- maven-3.8.6
- Chrome version 107.0.5304.88
- ChromeDriver version 107.0.5304.62