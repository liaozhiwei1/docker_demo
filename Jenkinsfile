pipeline {
    agent any
    environment {
        tag = sh(returnStdout: true, script: "git rev-parse -short=10 HEAD | tail -n +2")
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
                echo '${var.tag}'
            }
        }
    }
}
