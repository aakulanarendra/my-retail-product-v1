pipeline {
  environment {
    registry = "aakulanarendra/myretail"
    registryCredential = 'cr_id'
    dockerImage = ''
  }
  agent any
  stages {
    stage('Checkout') {
      steps {
        git 'https://github.com/aakulanarendra/my-retail-product-v1.git'
      }
    }
    stage('Build') {
      steps {
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Deploy') {
      steps {
        script {
          docker.withRegistry('', registryCredential) {
            dockerImage.push()
          }
        }
      }
    }
    stage('Post Actions') {
      steps {
        sh "docker rmi $registry:$BUILD_NUMBER"
      }
    }
  }
}
