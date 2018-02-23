pipeline {
    agent any
    stages{
        stage('Maven clean'){
            steps{
                sh 'mvn clean'
            }
        }
        stage('Maven test'){
            steps{
                sh 'mvn test'
            }
        }
        stage('Maven package'){
            steps{
                sh 'mvn package'
            }
        }
        stage('Docker build'){
            steps{
                sh 'docker build -t app .'
            }
        }
        stage('Restart docker container'){
            steps{
                sh 'docker kill app || true'
                sh 'docker rm app || true'
                sh 'docker run --net=\"host\" --name=app -d app'
            }
        }
    }
}