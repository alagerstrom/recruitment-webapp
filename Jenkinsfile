pipeline {
    agent any
    stages{
        stage('Docker version'){
            steps{
                sh 'docker version'
            }
        }
        stage('Build project'){
            steps{
                sh 'mvn clean'
                sh 'mvn package'
            }
        }
        stage('Docker build'){
            steps{
                sh 'docker stop $(docker ps -a -q)'
                sh 'docker build -t second .'
                sh 'docker run -d -p 8000:5000 second'
            }
        }
    }
}