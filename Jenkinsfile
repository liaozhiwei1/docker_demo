pipeline {
    agent {
        docker any
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
