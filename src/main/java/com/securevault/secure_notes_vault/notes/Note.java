package com.securevault.secure_notes_vault.notes;

import com.securevault.secure_notes_vault.user.User;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    // Encrypted payload fields
    @Column(nullable = false, length = 4096)
    private String ciphertextBase64;

    @Column(nullable = false)
    private String ivBase64;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User owner;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCiphertextBase64() { return ciphertextBase64; }
    public void setCiphertextBase64(String ciphertextBase64) { this.ciphertextBase64 = ciphertextBase64; }

    public String getIvBase64() { return ivBase64; }
    public void setIvBase64(String ivBase64) { this.ivBase64 = ivBase64; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
