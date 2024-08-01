package org.example.terraform

class TerraformLint {
    static void run(String tflintPath) {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            sh """
                ${tflintPath} --format json > tflint_report.json
            """
        }
    }
}
