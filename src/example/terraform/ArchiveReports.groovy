package example.terraform

class ArchiveReports {
    static void run() {
        echo 'Archiving Reports'
        archiveArtifacts artifacts: 'terraform_fmt_report.txt, terraform_validate_report.txt, checkov_report.json, tflint_report.json', allowEmptyArchive: true
    }
}
