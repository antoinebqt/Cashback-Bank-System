# Utilisez l'image de base Node.js
FROM node:14

# Créez un répertoire de travail à l'intérieur de l'image
WORKDIR /app

# Copiez le package.json et le package-lock.json dans le répertoire de travail
COPY . /app

# Installez les dépendances
RUN npm install

# Exposez le port sur lequel votre application écoute (si nécessaire)
EXPOSE 8080

# Commande pour exécuter votre application lorsque le conteneur démarre
CMD ["npm", "start"]
