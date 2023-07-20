pipeline {
  agent any
  stages {
    stage('clean') {
      parallel {
        stage('clean') {
          steps {
            sh 'mvn -clean'
          }
        }

        stage('clone') {
          steps {
            sh '''rm -rf docker_demo
git clone https://github.com/liaozhiwei1/docker_demo.git
'''
          }
        }

      }
    }

  }
}