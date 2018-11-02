# Employee Registration Using Messaging

### Technologies used:
- Spring Boot
- Spring JMS
- ActiveMQ
- JPA
- H2 DataBase

### Application Overview:
- Two Spring based Java applications implemented. 
- First one is a Spring application [employee-portal] where Employee can register by first name, last name & email. 
- Once Employee registered [status is in'created'], Employee-portal application sends this register entry to an Company-admin application which is a Spring based application using JMS queue [named as 'emp-queue'] via ActiveMQ Message broker, and configures a Listener on response queue [named as 'emp-response-queue'] to get the registration confirmation from Company-admin application. 
- Company-admin application, which was listing on employee queue ['emp-queue'], gets the register entry, and process it. It then sends the confirmation on the response queue ['emp-response-queue']. 
- On receiving the register entry response, the Employee-portal updates the status to Confirmed in it’s repository.
- Additionally, provided Search and View information on screen, Add/Edit information, Drag and Drop functionality (Company Website List) and fields validations.
