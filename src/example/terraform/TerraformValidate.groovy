package example.terraform

class TerraformValidate {
    def script

    TerraformValidate(script) {
        this.script = script
    }

    void run() {
        script.catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            script.sh 'terraform validate > terraform_validate_report.txt'
        }
    }
}

