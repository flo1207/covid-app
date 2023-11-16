pipeline {
    agent any

    stages {
        stage('Clonage') {
            steps {
                checkout scm
            }
        }
        stage('Verification du clonnage') {
            steps {
                script {
                    if (currentBuild.currentResult == 'SUCCESS') {
                        echo 'Le repository a été cloné avec succès!'
                    } else {
                        error 'Échec de la récupération du repository.'
                    }
                }
            }
        }
        stage('Build et Push des Docker Images') {
            steps {
                script {
                    // Étape de construction de l'image Angular
                    docker.build('angular-app', './frontend')

                    // Étape de construction de l'image Spring Boot
                    docker.build('spring-app', './backend')
                }
            }
        }

        stage('Deploiement avec Docker Compose') {
            steps {
                script {
                    // Déploiement avec Docker Compose
                    sh 'docker-compose up -d'
                }
            }
        }
    }

    // post {
    //     always {
    //         // Nettoyer les ressources Docker après le déploiement
    //         script {
    //             sh 'docker-compose down'
    //         }
    //     }
    // }

}