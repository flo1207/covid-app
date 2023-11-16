// pipeline {
//     agent any

//     stages {
//         stage('Build and Push Docker Images') {
//             steps {
//                 script {
//                     // Étape de construction de l'image Angular
//                     docker.build('angular-app', './frontend')

//                     // Étape de construction de l'image Spring Boot
//                     docker.build('spring-app', './backend')
//                 }
//             }
//         }

//         stage('Deploy with Docker Compose') {
//             steps {
//                 script {
//                     // Déploiement avec Docker Compose
//                     sh 'docker-compose up -d'
//                 }
//             }
//         }
//     }

//     post {
//         always {
//             // Nettoyer les ressources Docker après le déploiement
//             script {
//                 sh 'docker-compose down'
//             }
//         }
//     }
// }

pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                checkout scm
            }
        }
        stage('Verify cloning') {
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
        stage('Stage3') {
            steps {
                echo 'Hello World'
            }
        }
    }
}