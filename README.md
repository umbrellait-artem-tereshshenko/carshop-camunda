<b>carshop-camunda</b>

This project represents PoC for working with Camunda BPM embedded engine and Spring Boot application. 

The APP's BPMN file located inside resource folder describes the process of ordering car by customer 
and multiple approval steps by sales and bank managers depending on loan option.

The APP publishes several API endpoints protected by Spring Security basic authorization:

1. POST: http://localhost:8080/customer - for initiating car order flow and start BPM engine
2. GET: http://localhost:8080/customer - to get list of existed orders for authorized customer
3. GET: http://localhost:8080/sales - to obtain list of sended requests for approval for driver license verification by authorized sales manager
4. POST http://localhost:8080/sales - to accept/decline request for approval for driver license verification by authorized sales manager
5. GET: http://localhost:8080/bank - to obtain list of sended requests for approval for loan verification by authorized bank manager
4. POST http://localhost:8080/bank - to accept/decline request for approval for loan verification by authorized bank manager
   
![image](https://github.com/user-attachments/assets/58d214fc-82b5-4634-9a7b-a8bc62d3e56d)
