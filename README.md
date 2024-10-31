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

Please use Postman script collection to init Camunda BPM flow and interact with app:

https://www.postman.com/spaceflight-explorer-97105024/artem-tereshcheko-umbrellait/collection/u041sue/spring-camunda-flow

The BPMN diagram:
   
![image](https://github.com/user-attachments/assets/dd80a1cb-9fac-4ddd-ac45-78e6a2ea4f0d)

From the Java perspective code flow consists of nornal REST API invocacation to fetch data for UI and some trigger REST endpoint to initiate 
Camunda BPM engine process:


runtimeService.createProcessInstanceByKey("car_order_flow")
                .businessKey(carOrderDto.getId().toString())
                .setVariable("order", carOrderDto)
                .execute();
                

Camunda Service Tasks are mapped to Java delegate objects inside application. 

Camunda UserTask entities are triggered by REST endpoints invocation from UI and in correlates to code in such manner:


 Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(requestForApprovalDto.carOrder().getId().toString()).list().getFirst();

Map<String, Object> inputData = new HashMap<>();
inputData.put("order", carOrderDto);

askService.complete(task.getId(), inputData);



