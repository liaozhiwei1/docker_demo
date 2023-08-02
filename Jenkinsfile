pipeline {
    agent {
        docker any
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package docker:build'
                sh 'docker images'
            }
        }
    }
}
