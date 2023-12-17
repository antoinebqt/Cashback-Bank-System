# New Bank Status

## Week 50

### What was done
- Refactor of the code to match our new architecture
- Add a slave database for the account service to prevent table locking on balance update/read
- Write ADR
- Advanced load test with container limitation

### What is planned
- Continue work on the load balancer for transaction service
- Continue to work on the cache for transaction service
- Analyze more precisely where the old architectural problems originate
- Create multiple instances of transaction service with load balancer to make load tests

### What's the difference from last week
- Continue work on the load balancer for transaction service
- Advanced load test with container limitation to determine where are the architecture problems

### Issues
- No blockers

### Risk
- No risks

### RYG flag
- Green
---

## Week 49

### What was done
- Refactor of the code to match our new architecture
- Add a slave database for the account service to prevent table locking on balance update/read
- Load test between previous and new architcture
- Write ADR

### What is planned
- Continue work on the load balancer for transaction service
- Continue to work on the cache for transaction service
- Add a cache for the affiliated store service
- Analyze more precisely where the old architectural problems originate

### What's the difference from last week
- Continue work on the load balancer for transaction service
- Continue to work on the cache for transaction service
- Write missing ADR

### Issues
- No blockers

### Risk
- No risks

### RYG flag
- Green
---

## Week 48

### What was done
- Refactor of the code to match our new architecture
- Add a slave database for the account service to prevent table locking on balance update/read
- Load test on old and new architecture to compare performance

### What is planned
- Add a load balancer and a cache for the transaction service
- Add a cache for the affiliated store service

### What's the difference from last week
- Database replication using the master-slave pattern on the account service
- End of architecture refactoring

### Issues
- No blockers

### Risk
- No risks

### RYG flag
- Green
---

## Week 47

### What was done
- Major refactor of our architecture diagram to take into account the feedbacks made during the presentation and the new development needs

### What is planned
- Continue refactoring the code to match our new architecture
- Add a load balancer for the transaction service and a cache for the affiliated store service

### What's the difference from last week
- Two new services to better separate responsibilities
- Database replication using the master-slave pattern
- Add a RabbitMQ message queue for communication between certain services

### Issues
- No blockers

### Risk
- No risks

### RYG flag
- Green
---

## Week 44

### What was done
- Developpement of features associated with our user stories:
  Transactions statistics for the bank partnership manager
  Features progress

### What is planned
- Continue the development of the MVP to cover more user stories

### What's the difference from last week
- Additional functionalities

### Issues
- No blockers

### Risk
- Prevent fraud by pretending to have made a purchase at a partner store

### RYG flag
- Yellow
---

## Week 42

### What was done
- Developpement of features associated with our user stories:
  - Creation of bank account and a card
  - Money transfer into an account
  - Make a Payment
  - View affiliated store catalog and get cashback after a purchase 
  - Transactions history and amount earn thanks to cashback

### What is planned
- Continue the development of the MVP to cover more user stories

### What's the difference from last week
- Additional functionalities 

### Issues
- No blockers

### Risk
- Prevent fraud by pretending to have made a purchase at a partner store

### RYG flag
- Green
---

## Week 41

### What was done
- Refine a bit the architecture diagram and the user stories
- Repartition of the work between the team members (see the [Kanban](https://github.com/orgs/pns-si5-al-course/projects/15))
- Start the development of the MVP

### What is planned
- Continue the development of the MVP to cover more user stories

### What's the difference from last week
- Each member of the team has a user story to implement
- The MVP is in development

### Issues
- No blockers for the moment, just a bit of lack of time

### Risk
- Prevent fraud by pretending to have made a purchase at a partner store

### RYG flag
- Yellow
---

## Week 40

### What was done
- Build the banking application diagram
- Define the scope of the MVP
- Define the use cases
- Define the user stories answering these use case
- Choose the technology to build the application : Java Spring

### What is planned
- Have a clear idea about how to link different cash back systems for different partners

### What's the difference from last week
- Refining the bank application diagram
- Define the use cases
- Define the user stories answering these use case
- Choose the technology to build the application : Java Spring

### Issues
- No blockers for the moment

### Risk
- Prevent fraud by pretending to have made a purchase at a partner store

### RYG flag
- Green
---

## Week 39

### What was done
- Build the banking application diagram
- Define the scope of the MVP
- Have a good idea of our project objectives

### What is planned
- Choose a technology to build the application
- Start developing the project MVP
- Refine diagram if necessary

### What's the difference from last week
- No previous week

### Issues
- No blockers for the moment

### Risk
- Prevent fraud by pretending to have made a purchase at a partner store

### RYG flag
- Green
