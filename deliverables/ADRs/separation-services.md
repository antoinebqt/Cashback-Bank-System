## Separation of Bank and Cashback services

### Status
Accepted

### Context
We noticed that our application does not support a high load due to strong coupling between the different components of our application. This significant coupling and synchronous communication via REST API causes large latencies between these services. In addition to this latency, these two services had extremely busy elements, thus causing significant points of failure.
### Decision
To address this issue, we have decided to separate the Bank and Cashback services into several services with the aim of separating responsibilities and allowing better scaling of the application:
- The Bank service is separated into a service handling transactions and another handling the management of users' bank accounts.
- The Cashback service is separated into a service dealing with the management of cashbacks and another dealing with the management of partner stores.
### Consequences
The separation of the Bank and Cashback services makes it possible to better separate responsibilities. This helps distribute the application load better by making horizontal scaling easier. However, this is enabled at the cost of higher complexity, with more services to manage and more complex communication between these services.