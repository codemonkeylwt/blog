pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                script{
                    git credentialsId: '81a30c1f-8e72-4bfa-a5d6-fa7dfb2e1abf', url: 'git@github.com:codemonkeylwt/blog.git'
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true'
                sh 'cd /data/jenkins/workspace/blog/'
                sh 'chmod 777 shell/*'
                sh './shell/copy_jars.sh'
            }
        }

        stage('Analysis'){
            steps {
                sh 'mvn --batch-mode -V -U -e spotbugs:spotbugs'
                recordIssues enabledForFailure: true, tool: spotBugs()
            }
        }

        stage('Stop Old'){
            steps {
                sh label: 'Stop Services', script: './shell/stop_all.sh'
            }
        }

        stage('Run'){
            steps {
                sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /opt/blog/app/blog-index.jar > /opt/blog/logs/index/startup.log &'
            }
        }

    }

    post {
        failure {
            mail to: 'nbliuwentao@gmail.com',
                subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                body: "Something is wrong with ${env.BUILD_URL}"
        }
    }

}
