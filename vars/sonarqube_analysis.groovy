def call(String sonarServerName, String projectName, String projectKey) {
    echo "[SHARED-LIB] Initiating SonarQube Static Code Analysis (SAST)..."
    
    // 1. Tell Jenkins to find the compiled Scanner binary from global tools
    def scannerHome = tool 'sonar-scanner'
    
    // 2. Bind the environmental credentials container context
    withSonarQubeEnv(sonarServerName) {
        // 3. Execute the analysis using absolute binary mapping
        sh "${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectName=${projectName} \
            -Dsonar.projectKey=${projectKey} \
            -Dsonar.sources=. \
            -Dsonar.javascript.lcov.reportPaths=coverage/lcov.info"
    }
}
