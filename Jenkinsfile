
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
                }
            }
        }
        stage('Clean Cucumber.json file') {
             steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat 'mvn test -PCucumber-generator -Dmaven.test.skip=true -e'
                }
               }
        }
        stage('Generate Report') {
             steps {
                  bat 'mvn test integration-test -Dmaven.test.skip=true'
             }
        }

    }
    post {
        always {
             cucumber '**/cucumber.json'
             }
        }
}