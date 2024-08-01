package example.terraform

class CheckovScan {
    def script
    String workspace

    CheckovScan(script, String workspace) {
        this.script = script
        this.workspace = workspace
    }

    void run(String checkovPath) {
        script.catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            script.sh """
                cd ${workspace}
                ${checkovPath}/checkov -d . -s --output-file-path checkov_report.json
            """
            script.sh 'ls -l'
        }
    }
}
