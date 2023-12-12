# Load Balancer

## Status
Accepted

## Context
We now have a lot of requests with cashback, we want to handle a constant high load.
According to our performance results, we are not able to handle many requets.

## Decision
A load balancer could help to handle the requests.

## Consequences
The code needs to undergo refactoring, and we need more ressources to deploy our application twice.
But it will allows to handle many requests.