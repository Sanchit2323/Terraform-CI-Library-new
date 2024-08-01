package example.terraform

class TerraformFormat {
    static void run() {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            sh 'terraform fmt -check -diff > terraform_fmt_report.txt'
        }
    }
}
