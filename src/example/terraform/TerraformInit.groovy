package example.terraform

class TerraformInit {
    static void run() {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            sh 'terraform init'
        }
    }
}
