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
        stage('Compile'){
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
        stage('Deploy'){
            steps{
                sh "mvn tomcat7:redeploy"
            }
        }
    }
}