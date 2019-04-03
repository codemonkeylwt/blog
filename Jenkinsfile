pipeline {
    agent any

    environment {
        def ITEMNAME = "webapp"
        def DESTPATH = "/data/wwwroot"
        def SRCPATH = "~/workspace/test"
        def BUILD_USER = "mark"
        def USERMAIL = "nbliuwentao@gmail.com"
    }

    stages {
        stage('Checkout') {
            steps {
                try {
                    git credentialsId: '81a30c1f-8e72-4bfa-a5d6-fa7dfb2e1abf', url: 'git@github.com:codemonkeylwt/blog.git'
                } catch (exc) {
                    currentBuild.result = "FAILURE"
                    emailext (
                        subject: "'${env.JOB_NAME} [${env.BUILD_NUMBER}]' 更新失败",
                        body: """
                        详情：
                        FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'
                        状态：${env.JOB_NAME} jenkins 更新失败
                        URL ：${env.BUILD_URL}
                        项目名称 ：${env.JOB_NAME}
                        项目更新进度：${env.BUILD_NUMBER}
                        内容：nginx进程不存在
                        """,
                        to: "${USERMAIL}",
                        recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                    )
                }
            }
        }
        stage('Build'){
            steps {
                withMaven(
                    maven: 'maven') {
                        sh label: '', script: 'mvn clean install -Dmaven.test.skip=true'
                    }
                sh label: '', script: 'cd /data/jenkins/workspace/blog/'
                sh label: '', script: 'chmod 777 copy_jars.sh'
                sh label: '', script: './copy_jars.sh'
            }
        }
        stage('Run'){
            steps {
                sh label: '', script: 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /opt/blog/app/index.jar > /opt/blog/logs/index/startup.log &'
            }
        }
    }

    post {
        success {
            emailext (
                subject: "'${env.JOB_NAME} [${env.BUILD_NUMBER}]' 更新正常",
                body: """
                详情：
                SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'
                状态：${env.JOB_NAME} jenkins 更新运行正常
                URL ：${env.BUILD_URL}
                项目名称 ：${env.JOB_NAME}
                项目更新进度：${env.BUILD_NUMBER}
                """,
                to: "${USERMAIL}",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
    }
}
