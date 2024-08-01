package example.terraform

class TerraformInit {
    static void run(Map params) {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            sh 'terraform init'
        }
    }
}
