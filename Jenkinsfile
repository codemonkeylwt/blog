pipeline {
    agent any
    stages {
        stage('Checkout') {
            checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'f80bc816-b1a2-4193-9b19-539c92a31706', url: 'git@github.com:codemonkeylwt/blog.git']]])
        }
        stage('build') {
            sh label: '', script: 'mvn clean package'
        }
    }
}
