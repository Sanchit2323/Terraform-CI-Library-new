import example.terraform.TerraformInit
import example.terraform.TerraformFormat
import example.terraform.TerraformValidate
import example.terraform.TerraformLint
import example.terraform.CheckovScan
import example.terraform.ArchiveReports

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
                    script {
                        TerraformInit.run(message: "Initializing Terraform")
                    }
                }
            }
            stage('Terraform Format') {
                steps {
                    script {
                        TerraformFormat.run(message: "Formatting Terraform files")
                    }
                }
            }
            stage('Terraform Validate') {
                steps {
                    script {
                        TerraformValidate.run(message: "Validating Terraform files")
                    }
                }
            }
            stage('Terraform Lint') {
                steps {
                    script {
                        TerraformLint.run(TFLINT_PATH, message: "Linting Terraform files")
                    }
                }
            }
            stage('Checkov Scan') {
                steps {
                    script {
                        CheckovScan.run(CHECKOV_PATH, env.WORKSPACE, message: "Running Checkov scan")
                    }
                }
            }
            stage('Archive Reports') {
                steps {
                    script {
                        ArchiveReports.run(message: "Archiving reports")
                    }
                }
            }
        }
    }
}
