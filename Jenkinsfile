pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                // sh 'mvn clean package docker:build'
                sh 'git rev-parse HEAD'
            }
        }
        stage('docker'){
            steps{
                sh 'docker login --username=廖智伟123456 registry.cn-hangzhou.aliyuncs.com -p lzw19961229'
            }
        }
    }
}
