pipeline {
    script {
            build_tag = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim() 
        } 
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages {
        stage('Build') {
            echo '${build_tag}'
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
}
