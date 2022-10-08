# cucumber-project-automatisation


This is the selenium project for the automated test for cucumber-project team and all environments.

 ## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
See deployment for notes on how to deploy the project on a live system.

 ## Prerequisites

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)  
* [Maven 3](https://maven.apache.org/download.cgi)  
* [Git](https://git-scm.com/downloads)  
* [Gecko driver](https://github.com/mozilla/geckodriver/releases)  
* [Chrome driver](https://sites.google.com/a/chromium.org/chromedriver/downloads)  

 ## Installing

- Clone the project 

 ## Running the tests  
  
To run all the tests just type `mvn test`

### Running some specifics tests  
You can easily run a subset of your tests by using the `-Dcucumber.options` argument.  
There, you can filter the scenarios by their tags.  
For example, if you want to run a subset of scenarios tagged with `@smoke`, use the following line command :  
```  
mvn test -Dcucumber.options="--tags @smoke"  
```  

If you want to specify features files rather than tags, you can do it by writing the whole path from src to the feature file location.  
```  
mvn test -Dcucumber.options="path of file"  
```  

### Running tests in parallel  
You can run your test in parallel by providing `-P parallel` argument to the maven command. 

