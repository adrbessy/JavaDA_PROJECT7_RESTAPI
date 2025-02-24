# Poseidon
An application for making trades.
This app uses Java to run and stores the data in Postgresql DB.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

What things you need to install the software and how to install them

- Java 1.8
- Maven 4.0.0

### Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

### Running App

You can run the application in two different ways:

1/ import the code into an IDE of your choice and run the Application.java to launch the application.

2/ Or import the code, unzip it, open the console, go to the folder that contains the pom.xml file, then execute the below command to launch the application.

mvn spring-boot:run 

### API calls (URI, parameters)
GET

http://localhost:9004/bids

http://localhost:9004/curvePoints

http://localhost:9004/ratings

http://localhost:9004/rules

http://localhost:9004/trades

http://localhost:9004/users


POST

http://localhost:9004/authenticate

http://localhost:9004/bid

http://localhost:9004/curvePoint

http://localhost:9004/rating

http://localhost:9004/rule

http://localhost:9004/trade


PUT

http://localhost:9004/bid/2

http://localhost:9004/curvePoint/2

http://localhost:9004/rating/2

http://localhost:9004/rule/2

http://localhost:9004/trade/2

http://localhost:9004/user/14


DELETE

http://localhost:9004/bid?id=3

http://localhost:9004/curvePoint?id=1

http://localhost:9004/rating?id=1

http://localhost:9004/rule?id=1

http://localhost:9004/trade?id=1

http://localhost:9004/user?id=3


### Testing
The app has unit tests written.

To run the tests from maven, go to the folder that contains the pom.xml file and execute the below command.

mvn test


