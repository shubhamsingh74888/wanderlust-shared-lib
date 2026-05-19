def call(String odcInstallationName = 'OWASP', String nvdCredentialsId = 'NVD_API_KEY') {
    echo "[SHARED-LIB] Executing OWASP Dependency-Check Software Composition Analysis (SCA)..."
    
    // Dynamically grab the API token from Jenkins Vault securely
    withCredentials([string(credentialsId: nvdCredentialsId, variable: 'NVD_TOKEN')]) {
        dependencyCheck(
            odcInstallation: odcInstallationName,
            additionalArguments: "--nvdApiKey ${NVD_TOKEN} --format HTML --format XML"
        )
    }
    
    // Publish the visual charts and reporting dashboards directly to the Jenkins job home view
    dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
}
