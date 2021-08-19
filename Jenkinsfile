pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw -B -Dversion=${BUILD_NUMBER} -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test -Dversion=${BUILD_NUMBER}'
            }
            post {
                always {
                    tapdTestReport frameType: 'JUnit', onlyNewModified: true, reportPath: 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Quality'){
            steps{
                withSonarQubeEnv('DevOpsSonarQube'){
                   sh './mvnw -Dversion=${BUILD_NUMBER} sonar:sonar'
                }
            }
        }
        stage('Package'){
            steps{
                nexusPublisher nexusInstanceId: 'DevOpsNexus', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'target/example-app-${BUILD_NUMBER}.jar']], mavenCoordinate: [artifactId: 'example-app', groupId: 'com.example.app', packaging: 'jar', version: '${BUILD_NUMBER}']]]                
            }
        }
        stage('Deliver') {
            steps {
                sh 'sh ./deliver.sh'
            }
        }
    }
}
