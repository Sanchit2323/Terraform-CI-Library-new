package example.terraform

class TerraformInit {
    static void run(Map params) {
        sh 'terraform init'
    }
}
