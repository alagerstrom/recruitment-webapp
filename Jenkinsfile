pipeline {
    agent any
    stages{
        stage('Maven clean'){
            steps{
                sh 'mvn clean'
            }
        }
        stage('Maven package'){
            steps{
                sh 'mvn package'
            }
        }
        stage('Docker build'){
            steps{
                sh 'docker build -t webapp .'
            }
        }
        stage('Restart docker container'){
            steps{
                sh 'docker stop $(docker ps -a -q)'
                sh 'docker run -d -p 8000:5000 webapp'
            }
        }
    }
}