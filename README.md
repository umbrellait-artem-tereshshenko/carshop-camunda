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

From the Java perspective code flow consists of nornmal REST API invocation to fetch data from UI and  trigger REST endpoint to initiate 
Camunda BPM engine process inside facade class:

<code>
   
runtimeService.createProcessInstanceByKey("car_order_flow")
                .businessKey(carOrderDto.getId().toString())
                .setVariable("order", carOrderDto)
                .execute();
</code>


Camunda Service Tasks are mapped to Java delegate objects which are typical Spring Beans:


<code>
   
@Component
@AllArgsConstructor
@Slf4j
public class SendRequestForApprovalToBankDelegate extends AbstractDelegate {

    private final RequestForApprovalService requestForApprovalService;

    private final CarOrderRepository carOrderRepository;
    private final PersonService personService;

    @Override
    @Transactional
    public void run(DelegateExecution delegateExecution) {

        log.info("Sending request for approval to bank");

        CarOrderDto order = (CarOrderDto)delegateExecution.getVariable("order");

        //Find any manager with bank role
        Optional<Person> person = personService.findAnyPersonWithRole(Role.ROLE_BANK);

        CarOrder carOrder = carOrderRepository.getReferenceById(order.getId());

        requestForApprovalService.createRequestForApproval(carOrder, person.orElseThrow(
                () -> new PersonNotFoundException("Person with bank role not found")));
    }
}

</code>


Camunda UserTask entities are triggered by the following code inside facades:



<code>

Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(requestForApprovalDto.carOrder().getId().toString()).list().getFirst();

Map<String, Object> inputData = new HashMap<>();
inputData.put("order", carOrderDto);

taskService.complete(task.getId(), inputData);


</code>

