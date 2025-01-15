pipeline {
	agent any

    environment {
		TARGET_BRANCH = 'main'
        TARGET_URL = 'https://github.com/jaewoong-gwon/esg-self-assessment.git'
        GITHUB_TOKEN = credentials('Github')
        GIT_API = "https://api.github.com/repos"
        FLAG = 'false'
    }

    stages {
		stage('Checkout') {
			steps {
				script {
					def repo = TARGET_URL.split('/').last().replace('.git', '')
                    def response = sh(script: """
                        curl -H "Authorization: token ${GITHUB_TOKEN}" \
                        ${GIT_API}/${env.GIT_OWNER}/${repo}/pulls/${env.CHANGE_ID} \
                        | grep '"merged":' | cut -d ':' -f 2 | tr -d '", '
                    """, returnStdout: true).trim()

                    echo "env git branch : ${env.GIT_BRANCH}"
                    echo "env branch name : ${env.BRANCH_NAME}"
                    echo "env git target : ${env.CHANGE_TARGET}"

                    if (response == '' || response.toBoolean() == false) return

                    if (env.CHANGE_TARGET == "${TARGET_BRANCH}") {
						env.FLAG = 'true'
                    }
                }
                git branch: "${env.CHANGE_TARGET}", url: "${TARGET_URL}"
            }
        }

        stage('Build') {
			when {
				expression { env.FLAG == 'true' }
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
				expression { env.CHANGE_TARGET == "${TARGET_BRANCH}" }
            }
            steps {
				sshagent(credentials: ['dev-server-ssh']) {
					sh """
                        ssh -o StrictHostKeyChecking=no ${env.USER}@${env.HOST}
                        scp \$(pwd)/build/libs/*.jar ${env.USER}@${env.HOST}:${env.DIR}
                        ssh -t ${env.USER}@${env.HOST} ./deploy.sh
                    """
                }
            }
        }
    }
}
