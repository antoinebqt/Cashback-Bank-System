## Load Balancer

### Status
Accepted

### Context
We now have a lot of requests with cashback, we want to handle a constant high load.
According to our performance results, we are not able to handle many requests.

### Decision
A load balancer, which is able to redirect the traffic, could help to handle the requests management.

### Consequences
The code needs to undergo refactoring, and we need more resources to deploy our application twice.
But it will allows to handle many requests.