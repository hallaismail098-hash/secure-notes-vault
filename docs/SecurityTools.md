# Security Tools Report

## Tool 1: GitHub CodeQL (Static Code Security Scan)
- GitHub CodeQL was enabled to perform static code analysis and detect security weaknesses.
- CodeQL detected 1 high severity alert:
- ### Finding:
**Disabled Spring CSRF protection (High)**
- File: `SecurityConfig.java`
- Issue: CSRF protection was disabled in the security configuration.
- Risk: If the application uses cookies/sessions in the future, disabling CSRF could allow attackers to perform unauthorized actions using Cross-Site Request Forgery.
- Explanation: The project primarily uses JWT authentication (stateless APIs), which is why CSRF is commonly disabled, but the risk must still be documented.

### Mitigation:
- Keep JWT authentication stateless (use Authorization headers, not cookies).
- If the project adds session-based authentication or browser cookie auth later, CSRF protection should be re-enabled.

## Tool 2: Trivy Filesystem Scan (Dependency Vulnerability Scan)
- Used Trivy to scan the project files and Maven dependencies for known vulnerabilities
- Command used:
- trivy fs .

## Evidence
![Maven Test Result](docs/screenshots/Trivy1.png)

![Trivy Scan Result](docs/screenshots/Trivy2.png)
![GithubQL Scan Result](docs/screenshots/Github Scan.png)

