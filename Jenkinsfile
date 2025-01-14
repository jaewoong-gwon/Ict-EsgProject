pipeline {
	agent any

    environment {
		TARGET_BRANCH = 'main'
		TARGET_URL = 'https://github.com/jaewoong-gwon/esg-self-assessment.git'
    }

    stages {
		stage('Git Clone') {
			steps {
				git url : ${TARGET_URL}, branch : ${TARGET_BRANCH}
            }
        }

        stage('Deploy') {
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

