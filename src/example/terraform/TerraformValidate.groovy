package example.terraform

class TerraformValidate {
    static void run() {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            sh 'terraform validate > terraform_validate_report.txt'
        }
    }
}
