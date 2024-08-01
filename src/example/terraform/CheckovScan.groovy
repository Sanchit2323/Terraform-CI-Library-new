package org.example.terraform

class CheckovScan {
    static void run(String checkovPath, String workspace) {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            sh """
                cd ${workspace}
                ${checkovPath}/checkov -d . -s --output-file-path checkov_report.json
            """
            sh 'ls -l'
        }
    }
}
