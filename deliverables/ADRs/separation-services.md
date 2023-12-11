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
