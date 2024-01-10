## Master-Slave

### Status
Accepted

### Context
As there were many readings and writings on the same database, the requests management was slow.
Moreover, we need resiliency.

### Decision
Adding a Master-Slave model to allow the access to the data while new data are written.
This model is added to the bank (now named balance), transactions and cashback services, as they are the ones which handle many reads and writes.

### Consequences
The code needs to undergo refactoring, and we need more resources to handle more data.
But it significantly improves the traffic.
