pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    environment {
        tag= sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
    }
    stages {
        stage('Build') {
            steps {
                echo "${tag}"
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
}
