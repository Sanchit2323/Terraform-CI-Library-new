def call(Map config = [:]) {
    pipeline {
        agent any

        parameters {
            string(name: 'REPO_URL', defaultValue: 'https://github.com/cdivyanshu/my-shared-library.git', description: 'GitHub repository URL')
            string(name: 'AWS_ACCESS_KEY_ID', defaultValue: 'aws-access-key-id', description: 'AWS Access Key ID')
            string(name: 'AWS_SECRET_ACCESS_KEY', defaultValue: 'aws-secret-access-key', description: 'AWS Secret Access Key')
            choice(name: 'ACTION', choices: ['apply', 'destroy'], description: 'Choose Terraform action to perform')
        }

        environment {
            AWS_ACCESS_KEY_ID = credentials("${params.AWS_ACCESS_KEY_ID}")
            AWS_SECRET_ACCESS_KEY = credentials("${params.AWS_SECRET_ACCESS_KEY}")
        }

        stages {
            stage('Checkout') {
                steps {
                    git url: "${params.REPO_URL}",
                        branch: 'main'
                }
            }

            stage('Terraform Init') {
                steps {
                    script {
                        example.terraform.TerraformUtils.init(this)
                    }
                }
            }

            stage('Terraform Plan') {
                steps {
                    script {
                        example.terraform.TerraformUtils.plan(this)
                    }
                }
            }

            stage('Terraform Apply or Destroy') {
                steps {
                    script {
                        if (params.ACTION == 'apply') {
                            org.foo.TerraformUtils.apply(this)
                        } else if (params.ACTION == 'destroy') {
                            org.foo.TerraformUtils.destroy(this)
                        }
                    }
                }
            }
        }

        post {
            always {
                cleanWs()
            }
        }
    }
}
