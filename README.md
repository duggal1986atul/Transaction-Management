# Currency Transaction App
Setup Requirement
1) Java 17 Installation
2) Maven installation

To run test report , use mvn test command, This will generate a jacoco report at
target/site/jacoco/index.html

To run this app for local installation 
Run following command -> mvn spring-boot:run.

Test against postman following end points
post  http://localhost:8080/addTransactions

sample body:-

{
"description": "currency test",
"amount": "50.0",
"transactionDate": "2023-06-30"
}

for currency conversion and get call use this url :-
http://localhost:8080/transactions/1/country/India
1 is db identifier
India is country code ,
just for reference this app is built upon H2 in memory db ,please refer to application.yaml for connection credentials
