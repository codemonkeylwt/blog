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
                        sh "mvn clean install -Dmaven.test.skip=true"
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
                sh 'nohup java -jar /var/jenkins_home/workspace/blog/index/target/index.jar > /var/jenkins_home/logs/index/startup.log &'
            }
        }
    }
}
