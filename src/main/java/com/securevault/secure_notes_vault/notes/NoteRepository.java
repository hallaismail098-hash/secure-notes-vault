package com.securevault.secure_notes_vault.notes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByOwnerEmailOrderByUpdatedAtDesc(String ownerEmail);

    Optional<Note> findByIdAndOwnerEmail(Long id, String ownerEmail);
}
