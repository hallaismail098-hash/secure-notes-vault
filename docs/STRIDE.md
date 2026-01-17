# STRIDE Threat Model - Secure Notes Vault

## 1) Spoofing (Fake identity)
**Threat:** Attacker pretends to be another user.
**Mitigation:**
- Authentication using JWT
- Password hashing using BCrypt
- Token required for /api/notes endpoints

## 2) Tampering (Modifying data)
**Threat:** Attacker modifies notes or requests.
**Mitigation:**
- AES encryption for stored notes
- JWT prevents unauthorized modification
- Validation for note input (title/content)

## 3) Repudiation (Deny actions)
**Threat:** User denies creating/deleting notes.
**Mitigation:**
- Server logs login/requests (Spring Boot logging)
- Notes linked to authenticated user

## 4) Information Disclosure (Data leak)
**Threat:** Sensitive notes or passwords exposed.
**Mitigation:**
- Passwords stored as hashed (BCrypt)
- Notes encrypted with AES before saving in DB
- Role-based restrictions

## 5) Denial of Service (DoS)
**Threat:** Flood login/notes endpoints.
**Mitigation:**
- Stateless JWT reduces server session load
- Input validation reduces heavy payloads
- Can be improved by rate limiting (future work)

## 6) Elevation of Privilege
**Threat:** Normal user becomes Admin.
**Mitigation:**
- Admin endpoints protected by ROLE_ADMIN
- Role stored in DB and verified in JWT filter/security config
