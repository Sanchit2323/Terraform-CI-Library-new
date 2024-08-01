package example.terraform

class TerraformInit {
    static void run(Map params, Closure command) {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            echo params.message ?: "Running Terraform Init"
            command()
        }
    }
}
