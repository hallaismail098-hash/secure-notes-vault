# DREAD Risk Assessment - Secure Notes Vault

| Threat | Damage | Reproducibility | Exploitability | Affected Users | Discoverability | Risk |
|-------|--------|-----------------|----------------|---------------|----------------|------|
| Password theft from DB | 8 | 7 | 6 | 9 | 6 | High |
| Unauthorized note access | 9 | 7 | 7 | 8 | 7 | High |
| JWT token theft | 8 | 6 | 6 | 7 | 6 | Medium/High |
| SQL Injection | 7 | 5 | 5 | 6 | 6 | Medium |
| DoS attack | 6 | 7 | 6 | 8 | 7 | Medium/High |

## Notes
- Password theft is reduced by BCrypt hashing.
- Unauthorized access is reduced using JWT + role access control.
- Notes content is protected by AES encryption at rest.
