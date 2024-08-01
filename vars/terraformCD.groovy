def call(Map config = [:]) {
    pipeline {
        agent any

        parameters {
            string(name: 'REPO_URL', defaultValue: 'https://github.com/cdivyanshu/my-shared-library.git', description: 'GitHub repository URL')
            choice(name: 'ACTION', choices: ['apply', 'destroy'], description: 'Choose Terraform action to perform')
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
