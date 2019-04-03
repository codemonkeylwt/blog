pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git credentialsId: '81a30c1f-8e72-4bfa-a5d6-fa7dfb2e1abf', url: 'git@github.com:codemonkeylwt/blog.git'
            }
        }
        stage('build'){
            steps {
                withMaven(
                    maven: 'maven') {
                        sh "mvn clean install -Dmaven.test.skip=true"
                    }
            }
        }
    }
}
