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
            when {
                expression {
                    currentBuild.result == null || currentBuild.result == 'SUCCESS'
                }
            }
            steps {
                sh label: '', script: 'cd /data/jenkins/workspace/blog/'
                sh label: '', script: './copy_jars.sh'
            }
        }
        stage('Run'){
            steps {
                sh label: '', script: 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /opt/blog/app/index.jar > /opt/blog/logs/index/startup.log &'
            }
        }
    }
}
