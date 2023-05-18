pipeline {
    agent any
    tools{
        maven 'maven_3_8_4'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout scmGit(branches: [[name: '*/main'], [name: '*/develop']], extensions: [], userRemoteConfigs: [[credentialsId: '98750b12-1ae8-4f2e-9c8d-e32fe19bacac', url: 'https://github.com/ngocduc120499/spring-jpa.git']])
                sh 'mvn clean install'
            }
        }
    }
}
