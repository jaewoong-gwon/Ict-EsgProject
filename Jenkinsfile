pipeline {
	agent any

    environment {
		TARGET_BRANCH = 'main'
		TARGET_URL = 'https://github.com/jaewoong-gwon/esg-self-assessment.git'
    }

    stages {
		stage('Checkout') {
			steps {
				git branch: env.GIT_BRANCH, url: "${TARGET_URL}", credentialsId : 'Github'
            }
        }

        stage('Deploy') {
			when {
				expression {env.GIT_BRANCH == 'main'}
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

