pipeline {
    agent any
    tools{
        maven 'maven_3'
    }
    stages {
        stage('Example') {
            steps {
                sh 'mvn --version' // kiểm tra version của maven
            }
        }
    }
}