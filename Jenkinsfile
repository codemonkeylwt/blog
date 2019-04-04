pipeline {
    agent any

    environment {
        def USERMAIL = "nbliuwentao@gmail.com"
    }

    stages {
        stage('Checkout') {
            steps {
                script{
                    git credentialsId: '81a30c1f-8e72-4bfa-a5d6-fa7dfb2e1abf', url: 'git@github.com:codemonkeylwt/blog.git'
                }
            }
        }

        stage('Build'){
            steps {
                script{
                    sh label: 'Install', script: 'mvn clean install -Dmaven.test.skip=true'
                    sh label: '', script: 'cd /data/jenkins/workspace/blog/'
                    sh label: '', script: 'chmod 777 shell/*'
                    sh label: 'CopyJars', script: './shell/copy_jars.sh'
                }
            }
        }

        stage('Analysis'){
            steps {
                script{
                    sh label: 'FindBugs', script: 'mvn --batch-mode -V -U -e findbugs:findbugs spotbugs:spotbugs'
                }
            }
        }

        stage('Stop Old'){
            steps {
                sh label: 'Stop Services', script: './shell/stop_all.sh'
            }
        }

        stage('Run'){
            steps {
                script{
                    sh label: 'Start Services', script: 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /opt/blog/app/blog-index.jar > /opt/blog/logs/index/startup.log &'
                }
            }
        }

    }

    post {
        success {
            recordIssues enabledForFailure: true, tool: spotBugs()
        }
        failure {
            mail to: '${USERMAIL}',
                subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                body: "<html><body><p>Something is wrong with ${env.BUILD_URL}</p><p>Failure: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'</p><p>项目名称 ：${env.JOB_NAME}</p><p>项目更新进度：${env.BUILD_NUMBER}</p></body><html>"
        }
    }
}
