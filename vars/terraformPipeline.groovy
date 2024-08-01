import org.example.terraform.*

def call(Map params) {
    pipeline {
        agent any
        environment {
            CHECKOV_PATH = '/var/lib/jenkins/.local/bin'
            TFLINT_PATH = '/usr/local/bin/tflint' // Adjust the path if tflint is installed elsewhere
        }
        parameters {
            string(name: 'REPO_URL', defaultValue: 'https://github.com/Sanchit2323/Terraform-CI.git', description: 'GitHub repository URL')
        }
        stages {
            stage('Checkout') {
                steps {
                    git url: "${params.REPO_URL}", branch: 'main'
                }
            }
            stage('Terraform Init') {
                steps {
                    TerraformInit.run()
                }
            }
            stage('Terraform Format') {
                steps {
                    TerraformFormat.run()
                }
            }
            stage('Terraform Validate') {
                steps {
                    TerraformValidate.run()
                }
            }
            stage('Terraform Lint') {
                steps {
                    TerraformLint.run(TFLINT_PATH)
                }
            }
            stage('Checkov Scan') {
                steps {
                    CheckovScan.run(CHECKOV_PATH, env.WORKSPACE)
                }
            }
            stage('Archive Reports') {
                steps {
                    ArchiveReports.run()
                }
            }
        }
        post {
            always {
                cleanWs()
                echo 'Pipeline completed'
            }
        }
    }
}
