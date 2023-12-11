## Communication entre les services via une queue

### Status
Accepté

### Contexte
Nous avons remarqué que notre application ne supporte pas une charge élevée dû à un couplage fort entre les différents composants de notre application. Le principal problème étant que les services communiquaient entre eux de manière synchrone via l'utilisation d'API REST.

### Décision
Pour répondre à ce problème, nous avons décidé d'utiliser une queue pour permettre une communication asynchrone entre les différents services. Pour ce faire, nous avons choisi d'utiliser RabbitMQ qui est une queue de message open source. Notre application comporte une base de données stockant les transactions effectuées, donc nous n'avons pas besoin d'utiliser un event store type Apache Kafka.

### Conséquences
L'envoie de message asynchrone permet de réduire le couplage entre les différents services et donc de réduire la charge de l'application. De plus, si un service tombe ou est surchargés, les messages seront tout de même envoyé et pourront être traité quand le service sera de nouveau disponible. Cependant, cela ajoute de la complexité au niveau de l'implémentation et de la gestion des erreurs. De plus, cela ajoute un point de défaillance à l'application, si la queue tombe en panne, l'application ne pourra plus fonctionner correctement.

---

## Séparation des services Bank et Cashback

### Status
Accepté

### Contexte
Nous avons remarqué que notre application ne supporte pas une charge élevée dû à un couplage fort entre les différents composants de notre application. Ce couplage important et la communication synchrone via API REST provoque de grandes latences entre ces services. En plus de cette latence, ces deux services possédaient des éléments extrêmement sollicités, provoquant ainsi des points of failure importants.

### Décision
Pour répondre à ce problème, nous avons décidé de séparer les services Bank et Cashback en plusieurs services dans le but de séparer les responsabilités et de permettre une meilleure mise à l'échelle de l'application :
 - Le service Bank est séparé en un service s'occupant des transactions et un autre s'occupant de la gestion des comptes bancaires des utilisateurs.
 - Le service Cashback est séparé en un service s'occupant de la gestion des cashbacks et un autre s'occupant de la gestion des magasins partenaires.

### Conséquences
La séparation des services Bank et Cashback permet de mieux séparer les responsabilités. Cela permet de mieux répartir la charge de l'application en facilitant la mise à l'échelle horizontale. Cependant, cela est permis au prix d'une complexité plus élevée, avec plus de services à gérer et une communication entre ces services plus complexe.