
pipeline {
    agent any
    stages {
        stage('Build Project') {
            steps {
                bat 'mvn clean install -Dmaven.test.skip=true'
            }
        }

        stage('Test Execution') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat 'mvn clean install -DfailIfNoTests=false -Dtest=*RunTest -Dcucumber.options="${TAGS}"'
                    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '', reportFiles: 'index.html', reportName: 'htmlReport', reportTitles: '', useWrapperFileDirectly: true])
                }
            }
        }
   
}