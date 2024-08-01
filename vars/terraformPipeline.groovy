import example.terraform.TerraformInit
import example.terraform.TerraformFormat
import example.terraform.TerraformValidate
import example.terraform.TerraformLint
import example.terraform.CheckovScan
import example.terraform.ArchiveReports

def call(Map params) {
    def REPO_URL = "https://github.com/Sanchit2323/Terraform-CI-Library-new.git"
    
    pipeline {
        agent any
        environment {
            CHECKOV_PATH = '/var/lib/jenkins/.local/bin'
            TFLINT_PATH = '/usr/local/bin/tflint' // Adjust the path if tflint is installed elsewhere
            REPO_URL = "${REPO_URL}"
        }

        stages {
            stage('Checkout') {
                steps {
                    script {
                        // Log the REPO_URL for debugging
                        echo "Checking out repository: ${REPO_URL}"
                        
                        // Checkout the repository
                        git url: "${REPO_URL}", branch: 'main'
                    }
                }
            }
            stage('Terraform Init') {
                steps {
                    script {
                        def terraformInit = new TerraformInit(this)
                        terraformInit.run([message: "Initializing Terraform"])
                    }
                }
            }

            stage('Terraform Format') {
                steps {
                    script {
                        def terraformFormat = new TerraformFormat(this)
                        terraformFormat.run()
                    }
                }
            }
            stage('Terraform Validate') {
                steps {
                    script {
                        def terraformValidate = new TerraformValidate(this)
                        terraformValidate.run()
                    }
                }
            }

            stage('Terraform Lint') {
                steps {
                    script {
                        def terraformLint = new TerraformLint(this)
                        terraformLint.run("${env.TFLINT_PATH}") // Using the CHECKOV_PATH environment variable
                    }
                }
            }
            stage('Checkov Scan') {
                steps {
                    script {
                        def checkovScan = new CheckovScan(this, env.WORKSPACE) // Pass both parameters
                        checkovScan.run(CHECKOV_PATH)
                      }
                }
            }
            stage('Archive Reports') {
                steps {
                    script {
                        ArchiveReports.run(this) // Pass the script context
                    }
                }
            }
        }
    }
}
