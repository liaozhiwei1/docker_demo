pipeline {
    agent any
    script {
      env.imageTag = sh (script: 'git rev-parse --short HEAD ${GIT_COMMIT}', returnStdout: true).trim()
}
    stages {
        stage('Build') {
            steps {
                // sh 'mvn clean package docker:build'
            }
        }
        stage('docker'){
            steps{
                sh 'docker login --username=廖智伟123456 registry.cn-hangzhou.aliyuncs.com -p lzw19961229'
                echo '${imageTag}'
            }
        }
    }
}
