package example.terraform

class TerraformFormat {
    def script

    TerraformFormat(script) {
        this.script = script
    }

    void run() {
        script.catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            script.sh 'terraform fmt -check -diff > terraform_fmt_report.txt'
        }
    }
}
