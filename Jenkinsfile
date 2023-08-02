pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                script {
                      env.imageTag = sh (script: 'git rev-parse --short HEAD ${GIT_COMMIT}', returnStdout: true).trim()
                    }
                // sh 'mvn clean package docker:build'
                
            }
        }
        stage('docker'){
            steps{
                echo '${imageTag}'
                sh 'docker login --username=廖智伟123456 registry.cn-hangzhou.aliyuncs.com -p lzw19961229'
            }
        }
    }
}
