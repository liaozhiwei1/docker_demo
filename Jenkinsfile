pipeline {
    agent any
    environment {


        COMMT= sh(  returnStdout: true, script: 'git log --oneline -1 | awk \'{print \$1}\'')
        HTTPD= sh(  returnStdout: true, script: 'git rev-parse --short HEAD')

        }
    stages {
        stage('Build') {
            steps {
                    sh 'printenv'
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
