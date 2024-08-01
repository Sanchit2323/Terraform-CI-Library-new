package example.terraform

class TerraformUtils {
    static void init(script) {
        script.sh 'terraform init'
    }

    static void plan(script) {
        script.sh 'terraform plan -out=tfplan'
    }

    static void apply(script) {
        script.input 'Apply Terraform changes?'
        script.sh 'terraform apply -auto-approve tfplan'
    }

    static void destroy(script) {
        script.input 'Destroy Terraform infrastructure?'
        script.sh 'terraform destroy -auto-approve'
    }
}
