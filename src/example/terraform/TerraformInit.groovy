package example.terraform

class TerraformInit {
    static void run(Map params) {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            // Log the message passed from the pipeline
            echo params.message ?: "Running Terraform Init"
            sh 'terraform init'
        }
    }
}
