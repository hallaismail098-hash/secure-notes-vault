# Secure Notes Vault — STRIDE + DREAD Report

## 1. System Scenario

The project I worked on: **Secure Notes Vault** is a secure web application that allows users to:

1. Create accounts (**Register**)
2. Login using email/password
3. Create private notes
4. View their own notes
5. Update and delete notes

**Admins can:**

1. Manage users (enable/disable accounts)
2. Access admin-only endpoints

**Security features implemented include:**

A. JWT Authentication  
B. BCrypt password hashing  
C. AES-GCM encryption for notes  
D. RBAC roles: USER / ADMIN

---

## 2. STRIDE Threat Analysis

| Threat | Description | Example |
|--------|-------------|---------|
| **Spoofing** | Impersonating a user | Threat Actor logs in with stolen credentials |
| **Tampering** | Unauthorized data modification | Threat actor modifies another user’s note |
| **Repudiation** | Denying actions without being able to trace the user | Threat actor denies deleting a note |
| **Information Disclosure** | Unauthorized access to sensitive data | Threat actor views/decrypts stored notes |
| **Denial of Service** | Making the system unavailable | Threat Actor floods login/notes API requests |
| **Elevation of Privilege** | Unauthorized privilege gain | User becomes ADMIN and accesses admin endpoints |

---

## 3. Threat Impact and Security Controls

| Threat | Impact | Countermeasures |
|--------|--------|-----------------|
| **Spoofing** | Unauthorized access, data leaks | Strong password hashing (BCrypt), JWT authentication |
| **Tampering** | Data corruption, trust loss | JWT authorization, note ownership checks |
| **Repudiation** | Lack of accountability | Audit logs, store user actions with timestamps |
| **Information Disclosure** | Privacy violation, sensitive data leakage | AES-GCM encryption, RBAC, access controls |
| **Denial of Service** | Service downtime | Rate limiting *(future improvement)*, validation login/notes API requests |
| **Elevation of Privilege** | Full system compromise | RBAC, least privilege, protected admin APIs |

---

## 4. DREAD Risk Assessment

**DREAD Scoring Criteria (1–10):**
- **Damage**: How much damage would the attack cause?
- **Reproducibility**: How easy is it to repeat the attack?
- **Exploitability**: How easy is it to exploit?
- **Affected Users**: How many users would be impacted?
- **Discoverability**: How easy is it to discover the vulnerability?

| Threat | Damage | Reproducibility | Exploitability | Affected Users | Discoverability | Total | Risk Level |
|--------|:------:|:---------------:|:--------------:|:--------------:|:---------------:|:-----:|-----------|
| **Spoofing** | 8 | 8 | 7 | 7 | 7 | **37** | **High** |
| **Tampering** | 8 | 7 | 7 | 6 | 6 | **34** | **High** |
| **Repudiation** | 5 | 6 | 5 | 6 | 6 | **28** | **Medium** |
| **Information Disclosure** | 9 | 7 | 6 | 7 | 6 | **35** | **High** |
| **Denial of Service** | 7 | 7 | 7 | 6 | 8 | **35** | **High** |
| **Elevation of Privilege** | 9 | 7 | 8 | 7 | 6 | **37** | **Critical** |

---

## 5. DREAD Summary Table (Final)

| Threat | Total Score | Risk Level | Recommended Fixes |
|--------|------------:|-----------|-------------------|
| **Spoofing** | 37 | High | Enforce strong passwords, use short JWT expiry, add account lockout, enable HTTPS |
| **Tampering** | 34 | High | Validate ownership checks, validate inputs, prevent IDOR attacks |
| **Repudiation** | 28 | Medium | Enable audit logging, log timestamp + user ID for actions |
| **Information Disclosure** | 35 | High | AES-GCM encryption, RBAC enforcement, use HTTPS, avoid logging sensitive data |
| **Denial of Service** | 35 | High | Add rate limiting, request throttling, monitoring, CAPTCHA on login/register *(optional)* |
| **Elevation of Privilege** | 37 | Critical | Strict RBAC, protect admin endpoints, method-level security, prevent role escalation |



