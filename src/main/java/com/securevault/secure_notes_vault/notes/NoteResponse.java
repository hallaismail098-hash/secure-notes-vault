package com.securevault.secure_notes_vault.notes;

import java.time.Instant;

public class NoteResponse {
    private Long id;
    private String title;
    private String content; // decrypted
    private Instant createdAt;
    private Instant updatedAt;

    public NoteResponse(Long id, String title, String content, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
