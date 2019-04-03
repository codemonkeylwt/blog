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
                script{
                    try {
                        git credentialsId: '81a30c1f-8e72-4bfa-a5d6-fa7dfb2e1abf', url: 'git@github.com:codemonkeylwt/blog.git'
                    } catch (exc) {
                        currentBuild.result = "FAILURE"
                        emailext (
                            subject: "'${env.JOB_NAME} [${env.BUILD_NUMBER}]' 更新失败",
                            body: """
                            <html>
                            <body>
                            <p>详情：</p>
                            <p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'</p>
                            <p>状态：${env.JOB_NAME} jenkins git拉取代码失败</p>
                            <p>URL ：${env.BUILD_URL}</p>
                            <p>项目名称 ：${env.JOB_NAME}</p>
                            <p>项目更新进度：${env.BUILD_NUMBER}</p>
                            </body>
                            <html>
                            """,
                            to: "${USERMAIL}",
                            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                        )
                    }
                }
            }
        }
        stage('Build'){
            steps {
                script{
                    try {
                        withMaven(
                            maven: 'maven') {
                                sh label: '', script: 'mvna clean install -Dmaven.test.skip=true'
                            }
                        sh label: '', script: 'cd /data/jenkins/workspace/blog/'
                        sh label: '', script: 'chmod 777 shell/*'
                        sh label: '', script: './shell/copy_jars.sh'
                    } catch (exc) {
                        currentBuild.result = "FAILURE"
                        emailext (
                            subject: "'${env.JOB_NAME} [${env.BUILD_NUMBER}]' 更新失败",
                            body: """
                            <html>
                            <body>
                            <p>详情：</p>
                            <p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'</p>
                            <p>状态：${env.JOB_NAME} jenkins maven构建失败</p>
                            <p>URL ：${env.BUILD_URL}</p>
                            <p>项目名称 ：${env.JOB_NAME}</p>
                            <p>项目更新进度：${env.BUILD_NUMBER}</p>
                            </body>
                            <html>
                            """,
                            to: "${USERMAIL}",
                            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                        )
                    }
            }
        }
        stage('Stop Old'){
            steps {
                sh label: '', script: './shell/stop_all.sh'
            }
        }
        stage('Run'){
            steps {
                script{
                    try {
                        sh label: '', script: 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /opt/blog/app/blog-index.jar > /opt/blog/logs/index/startup.log &'
                    } catch (exc) {
                        currentBuild.result = "FAILURE"
                        emailext (
                            subject: "'${env.JOB_NAME} [${env.BUILD_NUMBER}]' 更新失败",
                            body: """
                            <html>
                            <body>
                            <p>详情：</p>
                            <p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'</p>
                            <p>状态：${env.JOB_NAME} jenkins java启动失败</p>
                            <p>URL ：${env.BUILD_URL}</p>
                            <p>项目名称 ：${env.JOB_NAME}</p>
                            <p>项目更新进度：${env.BUILD_NUMBER}</p>
                            </body>
                            <html>
                            """,
                            to: "${USERMAIL}",
                            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                        )
                    }
                }
            }
        }
    }

}
