# Docker lancement de fichiers php

## Auteur

Florian IMPROVISATO

## Description

Ce projet fut réalisé dans le cadre d'un  cours de mise en production et def programmation FullStack délivré à Polytech Nancy.

Il s'agit d'une application permettant de prendre des rendez-vous pour se faire vacciner mais aussi gérer les différents centres via une rubrique de gestion en fonction de rôles.

Le projet peut être ouvert via un container Docker.

Nous utiliserons pour cela différents fichiers indispensables au bon fonctionnement de docker:

- `Dockerfile` : fichier de configuration utilisé pour créer une image Docker
- `docker-compose.yaml` : fichier contenant les configurations nécessaires pour l'exécution de plusieurs conteneurs
- `backend`: repertoire contenant les fichiers du server spring boot
- `frontend`: repertoire contenant les fichiers angular du frontend


## Les prérequis
La liste des applications qui doivent être installées sur la machine :
1. Docker
2. Git


## Comment build les deux containers ?
1. Ouvrir l'application Docker
2. Cloner le repertoire git dans un terminal

```sh
git clone https://github.com/flo1207/covid-app
```

3. Se rendre dans le dossier `php-app`
4. Lancer la commande 

```sh
docker-compose up --build
```

5. Une fois que les deux conteneurs sont builds, on va sur l'application docker puis `Containers -> php-app -> phpapp-container -> Exec`
<br>On ouvre ensuite deux terminaux.

6. Dans le premier terminal, on lance la commande:

```sh
$ php server.php
```

7. Dans le second terminal, on lance la commande:

```sh
$ php client.php
```

8. On peut verifier que les deux containers sont bien entrain de run en utilisant la commande hors container:

```sh
$ docker ps
```

9. Pour `arrêter` les processus, on utilise ctrl+c puis la commande:

```sh
$ docker-compose down
```

## Comment build un container avec  le `Dockerfile` ?
1. Ouvrir l'application Docker
2. Cloner le repertoire git dans un terminal

```sh
$ git clone https://github.com/flo1207/php-app.git
```

3. Se rendre dans le dossier `php-app`
4. Lancer la commande de build

```sh
$ docker build -t video-php-app .
```
5. Executer la commande pour lancer le container

```sh
$ docker run -d -p 8080:80 --name video-php-container video-php-app
```

6. Pour se rendre dans l'application, on effectue la commande:

```sh
$ docker run -it video-php-app
```

7. Pour supprimer le container:

```sh
$ docker stop video-php-app
```
```sh
$ docker rm video-php-app
```