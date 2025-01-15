pipeline {
	agent any

    environment {
		TARGET_BRANCH = 'main'
		TARGET_URL = 'https://github.com/jaewoong-gwon/esg-self-assessment.git'
		GIT_CREDENTIALS_ID = 'Github'
    }

    stages {
		stage('Checkout') {
			when {
				expression {env.CHANGE_TARGET == "${TARGET_BRANCH}"}
			}
			steps {
				script {
					echo "env git branch : ${env.GIT_BRANCH}"
					echo "env branch name : ${env.BRANCH_NAME}"
					echo "env git target : ${env.CHANGE_TARGET}"
				}
				git branch: env.CHANGE_TARGET, url: "${TARGET_URL}", credentialsId : "${GIT_CREDENTIALS_ID}"
            }
        }

        stage('Build') {
			when {
				expression {env.CHANGE_TARGET == "${TARGET_BRANCH}"}
			}
			steps {
				script {
					echo "Start Build"
					sh 'pwd'
					sh './gradlew build -x test'
					echo "End Build"
				}
			}
		}

        stage('Deploy') {
			when {
				expression {env.CHANGE_TARGET == "${TARGET_BRANCH}"}
			}
			steps {
				sshagent(credentials: ['dev-server-ssh']) {
					sh """
                        ssh -o StrictHostKeyChecking=no ${env.USER}@${env.HOST}
                        scp ${pwd}/build/libs/*.jar ${env.USER}@${evn.HOST}:${env.DIR}
						ssh -t ${env.USER}@${env.HOST} ./deploy.sh
                    """
                }
            }
        }
    }
}

