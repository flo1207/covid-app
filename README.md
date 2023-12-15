# Application de vaccination et prise de rendez-vous

## Auteur

Florian IMPROVISATO

## Description

Ce projet fut réalisé dans le cadre d'un  cours de mise en production et de programmation FullStack délivrés à Polytech Nancy.

Il s'agit d'une application permettant de prendre des rendez-vous pour se faire vacciner mais aussi de gérer les différents centres via une rubrique de gestion en fonction de rôles.

Le projet peut être lancé grâce à plusieurs containers Docker.

Nous utiliserons pour cela différents fichiers indispensables au bon fonctionnement de docker:

- `backend/Dockerfile` : fichier de configuration utilisé pour créer une image Docker du backend 
- `frontend/Dockerfile` : fichier de configuration utilisé pour créer une image Docker du frontend 
- `docker-compose.yaml` : fichier contenant les configurations nécessaires pour l'exécution des conteneurs du projet
- `Jenkinsfile` : script jenkins pour lancer les différents build dans un pipeline jenkins
- `/backend`: repertoire contenant les fichiers du server spring boot
- `/frontend`: repertoire contenant les fichiers angular du frontend

## Travail accomplis

1. Création de l'application et de la partie frontend avec Angular.
2. Pour la partie Backend, j'ai pu développer une authentification par token et une gestion des demande et requettes en fonction de l'authentification de la personne.
3. J'ai pu réaliser une page de tests unitaires dans le repertoire backend/src/test. Pour un soucis de fonctionalité avec la partie docker les tests sont en commentaire mais fonctionel en local.
4. J'ai pu créer des metrics afin de compter le nombre de patients prenants des rendez-vous ainsi que des metrics liées au temps de création/suppression d'un utilisateur.
5. Enfin j'ai pu build l'application entièrement sur un docker (voir la partie de build).

## Les prérequis
La liste des applications qui doivent être installées sur la machine :
1. Docker
2. Git


## Comment build le projet ?
1. Ouvrir l'application Docker
2. Cloner le repertoire git dans un terminal

```sh
git clone https://github.com/flo1207/covid-app
```

3. Se rendre dans le dossier `covid-app`
4. Lancer la commande 

```sh
docker-compose up -d
```

5. Une fois que les trois conteneurs sont builds, on va pouvoir se rendre sur l'adresse :

```sh
http://localhost:4200
```
(ATTENTION le backend prendra du temps à s'executer)


6. Pour `tester` les processus, on se rend sur l'adresse :


```sh
http://localhost:8080/api/public/test
```
(ATTENTION le backend prendra du temps à s'executer)

7. Pour `arrêter` les processus, on utilise ctrl+c puis la commande:

```sh
docker-compose down
```

## Build du backend en local

1. Clonez le repertoire suivant:

```sh
git clone https://github.com/jredel/jenkins-compose
```

2. Une fois dans le reperoir /backend lancer la commande:

```sh
docker compose up -d
```

## Créer un pipeline avec `Jenkins`

1. Clonez le repertoire suivant:

```sh
git clone https://github.com/jredel/jenkins-compose
```

2. Lancez le docker Jenkins à l'aide de la commande

```sh
docker compose up -d 
```

3. Installer les plugins obligatoires:
- Docker
- `Docker compose`
- Git

4. Après avoir configurer votre compte à l'adresse http://localhost:8001
&nbsp;
Veuillez ensuite créer un nouvel item en pipeline avec les options suivantes:
- GitHub project -> `https://github.com/flo1207/covid-app`
- Pipeline -> Definition -> `Pipeline script from SCM`

5. Enfin vous pouvez lancer un nouveau build et tester le bon fonctionnement du projet.

## Ajouter des centres et gérer l'application

1. Rendez vous dans la rubrique `gestion`
2. Connectez vous avec: 
- username = `super_admin@gmail.com`
- password = `super`
3. Vous pouvez désormais ajouter des aministrateurs ou des medecins et gérer l'application
