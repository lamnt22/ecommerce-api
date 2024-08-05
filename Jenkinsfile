pipeline {
    agent any
    tools{
        maven 'maven_3'
    }
    stages{
	    stage('File Permission') {
	        steps {
	            sh "sudo chmod -R 777 /var/lib/jenkins/workspace/ecommerce-api"
	        }
	    }
	    stage('Checkout') {
		steps {
		     checkout scm
		}
	    }
        stage('Build Maven'){
            steps{
	       sh 'mvn clean install'
           sh 'sudo chmod -R 777 /var/www/ecommerce/api'
	       sh 'cp -f ./target/*.jar /var/www/ecommerce/api'
            }
        }
        stage('Restart service'){
            steps{
                script{
                    sh 'sh /var/www/qrsite/api/ecommerce_api.sh stop'
                    sh 'sh /var/www/qrsite/api/ecommerce_api.sh start'
                }
            }
        }
    }
}