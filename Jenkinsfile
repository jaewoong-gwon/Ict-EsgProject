pipeline {
	agent any

    stages {
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
                        scp ${WORKSPACE}/build/libs/*.jar ${env.SERVER_USER}@${env.SERVER_IP}:${env.PROJECT_DIR}
                        ssh -t ${env.SERVER_USER}@${env.SERVER_IP} ${env.PROJECT_DIR}/deploy.sh
                    """
                }
            }
        }
    }
}
