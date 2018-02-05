pipeline {
    agent any
    stages{
        stage('Clean') {
            steps {
                sh "pwd"
                sh "mvn clean"
                sh "ls"
            }
        }
        stage('Build'){
            steps {
                sh "mvn compile"
            }
        }
        stage('Test'){
            steps{
                sh "mvn test"
            }
        }
        stage('Package'){
            steps{
                sh "mvn package"
            }
        }
    }
}