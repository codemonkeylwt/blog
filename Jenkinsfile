pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git credentialsId: '81a30c1f-8e72-4bfa-a5d6-fa7dfb2e1abf', url: 'git@github.com:codemonkeylwt/blog.git'
            }
        }
        stage('Build'){
            steps {
                withMaven(
                    maven: 'maven') {
                        sh label: '', script: 'mvn clean install -Dmaven.test.skip=true'
                    }
            }
        }
        stage('Run'){
            when {
                expression {
                    currentBuild.result == null || currentBuild.result == 'SUCCESS'
                }
            }
            steps {
                sh label: '', script: 'java -jar /var/jenkins_home/workspace/blog/index/target/index.jar'
            }
        }
    }
}
