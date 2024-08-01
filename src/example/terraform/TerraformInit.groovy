package example.terraform

class TerraformInit {
    def script

    TerraformInit(script) {
        this.script = script
    }

    void run(Map params) {
        script.sh "echo ${params.message ?: 'Running Terraform Init'}"
        script.sh 'terraform init'
    }
}
