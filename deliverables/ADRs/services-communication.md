## Communication entre les services via une queue

### Status
Accepted

### Context
We noticed that our application does not support a high load due to strong coupling between the different components of our application. The main problem being that the services communicated with each other synchronously through the use of REST APIs.
### Decision
To address this problem, we decided to use a queue to allow asynchronous communication between different services. To do this, we chose to use RabbitMQ which is an open source message queue. Our application includes a database storing the transactions carried out, so we do not need to use an Apache Kafka type event store.
### Consequences
Sending asynchronous messages makes it possible to reduce the coupling between the different services and therefore reduce the application load. In addition, if a service goes down or is overloaded, messages will still be sent and can be processed when the service is available again. However, this adds complexity to implementation and error handling. Additionally, this adds a point of failure to the application, if the queue fails the application will no longer be able to function properly.