package example.terraform

class ArchiveReports {
    static void run(script) {
        script.echo 'Archiving Reports'
        script.archiveArtifacts artifacts: 'terraform_fmt_report.txt, terraform_validate_report.txt, checkov_report.json, tflint_report.json', allowEmptyArchive: true
    }
}
