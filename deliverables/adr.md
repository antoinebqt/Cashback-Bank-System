## Message Queue : RabbitMQ

### Status
Accepted

### Context
Nous avons remarqué que notre application ne supporte pas une charge élevée dû à un couplage fort entre les différents composants de notre application.
Le principal problème étant que les services communiquaient entre eux de manière synchrone via l'utilisation d'API REST.

### Decision
Pour répondre à ce problème, nous avons décidé d'utiliser une queue pour permettre une communication asynchrone entre les différents services. Pour ce faire, nous avons choisi d'utiliser RabbitMQ qui est une queue de message open source. Notre application comporte une base de données stockant les transactions effectuées, donc nous n'avons pas besoin d'utiliser un event store type Apache Kafka.

### Consequences
L'envoie de message asynchrone permet de réduire le couplage entre les différents services et donc de réduire la charge de l'application. De plus, si un service tombe ou est surchargés, les messages seront tout de même envoyé et pourront être traité quand le service sera de nouveau disponible. Cependant, cela ajoute de la complexité au niveau de l'implémentation et de la gestion des erreurs. De plus, cela ajoute un point de défaillance à l'application, si la queue tombe en panne, l'application ne pourra plus fonctionner correctement.