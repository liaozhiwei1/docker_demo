pipeline {
    agent any
    environment {
        tag = sh(  returnStdout: true, script: 'git rev-parse --short HEAD')
    }
    stages {
        stage('Build') {
            steps {
                 sh 'mvn clean package docker:build'
            }
        }
        stage('docker'){
            steps{
                sh 'docker login --username=廖智伟123456 registry.cn-hangzhou.aliyuncs.com -p lzw19961229'
                sh 'docker tag demo:0.0.1-SNAPSHOT registry.cn-hangzhou.aliyuncs.com/lzw_docker/java_lzw:${tag}'
                sh 'docker rmi demo:0.0.1-SNAPSHOT'
            }
        }
    }
}
