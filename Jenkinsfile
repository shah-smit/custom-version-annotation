pipeline{
    agent any

    stages {
        stage('Compile'){
            steps {
                withMaven(maven: 'maven_3_6_3'){
                    sh 'mvn clean compile'
                }
            }
        }

        stage('Unit Testing'){
            steps {
                withMaven(maven: 'maven_3_6_3'){
                     sh 'mvn test'
                }
            }
        }


        stage('Sonar Scan'){
            steps {
                withMaven(maven: 'maven_3_6_3'){
                    sh 'mvn sonar:sonar \
                          -Dsonar.projectKey=custom-version-annotation \
                          -Dsonar.host.url=http://localhost:9000 \
                          -Dsonar.login=45f9e442543a68157c2534aa2ca96221354ebd7a'
                }
            }
        }

        stage('Jar Package'){
            steps {
                withMaven(maven: 'maven_3_6_3'){
                    sh 'mvn package'
                }
            }
        }

        stage('Local Deploy'){
            steps {
                script {
                    sh 'nohup java -jar target/bean-0.0.1-SNAPSHOT.jar &'
                 }
             }
        }

        stage('Waiting'){
            steps {
                sleep(time:10,unit:"SECONDS")
            }
        }

        stage('Functional Testing'){
            steps {
                script {
                    sh 'mvn test -Dtest=KarateRunner'
                 }
            }
        }

        stage('Kill Deployment'){
            steps {
                script {
                    sh 'kill $(lsof -ti:8081)'
                }
            }
        }
    }
}