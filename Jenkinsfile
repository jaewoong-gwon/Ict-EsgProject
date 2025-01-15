pipeline {
	agent any

    environment {
		TARGET_BRANCH = 'main'
		TARGET_URL = 'https://github.com/jaewoong-gwon/esg-self-assessment.git'
		CREDENTIALS_ID = 'Github'
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
				git branch: env.CHANGE_TARGET, url: "${TARGET_URL}", credentialsId : "${CREDENTIALS_ID}"
            }
        }

        stage('Deploy') {
			when {
				expression {env.CHANGE_TARGET == "${TARGET_BRANCH}"}
			}
			steps {
				sshagent(credentials: ['dev-server-ssh']) {
					sh """
                        ssh -o StrictHostKeyChecking=no ${env.USER}@${env.HOST} << EOF
                        cd ${env.DIR}
                        docker compose down
                        docker compose up -d --build
                        exit
                        EOF
                    """
                }
            }
        }
    }
}

