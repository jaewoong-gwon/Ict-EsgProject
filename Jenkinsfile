pipeline {
	agent any

	/*
    environment {
		TARGET_BRANCH = 'main'
        TARGET_URL = 'https://github.com/jaewoong-gwon/esg-self-assessment.git'
        GITHUB_TOKEN = credentials('Github')
        GIT_API = "https://api.github.com/repos"
        FLAG = 'false'
    }
	*/
    stages {
		/*
		stage('Checkout') {
			steps {
				script {
					def repo = TARGET_URL.split('/').last().replace('.git', '')
                    def response = sh(script: """
                        curl -H Authorization: token $GITHUB_TOKEN_PSW \
                        ${GIT_API}/${env.GIT_OWNER}/${repo}/pulls/${env.CHANGE_ID} \
                        | grep '"merged":' | cut -d ':' -f 2 | tr -d '", '
                    """, returnStdout: true).trim()

                    echo " \
						env git branch : ${env.GIT_BRANCH} \
						env branch name : ${env.BRANCH_NAME} \
						env git target : ${env.CHANGE_TARGET} \
						response : ${response} "

                    if (response == '' || response.toBoolean() == false) return

                    if (env.CHANGE_TARGET != "${TARGET_BRANCH}" ) return

					env.FLAG = 'true'
					git branch: "${env.CHANGE_TARGET}", url: "${TARGET_URL}"
                }
            }
        }
		*/
        stage('Build') {
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
			steps {
				sshagent(credentials: ['dev-server-ssh']) {
					sh """
                        ssh -o StrictHostKeyChecking=no ${env.SERVER_USER}@${env.SERVER_IP}
                        scp \$(pwd)/build/libs/*.jar ${env.SERVER_USER}@${env.SERVER_IP}:${env.PROJECT_DIR}
                        ssh -t ${env.SERVER_USER}@${env.SERVER_IP} ${env.PROJECT_DIR}/deploy.sh
                    """
                }
            }
        }
    }
}
