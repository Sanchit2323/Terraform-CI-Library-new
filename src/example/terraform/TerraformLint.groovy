package example.terraform

class TerraformLint {
    def script

    TerraformLint(script) {
        this.script = script
    }

    void run(String tflintPath) {
        script.catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            script.sh """
                ${tflintPath} --format json > tflint_report.json
            """
        }
    }
}

