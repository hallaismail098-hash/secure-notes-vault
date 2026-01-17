package com.securevault.secure_notes_vault.notes;

import com.securevault.secure_notes_vault.security.AesGcmCrypto;
import com.securevault.secure_notes_vault.user.User;
import com.securevault.secure_notes_vault.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final AesGcmCrypto crypto;

    public NotesService(NoteRepository noteRepository,
                        UserRepository userRepository,
                        AesGcmCrypto crypto) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.crypto = crypto;
    }

    public NoteResponse create(String ownerEmail, NoteRequest req) {
        User owner = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        AesGcmCrypto.EncryptedPayload enc = crypto.encrypt(req.getContent());

        Note note = new Note();
        note.setTitle(req.getTitle());
        note.setCiphertextBase64(enc.ciphertextBase64());
        note.setIvBase64(enc.ivBase64());
        note.setOwner(owner);

        Note saved = noteRepository.save(note);

        return new NoteResponse(saved.getId(), saved.getTitle(), req.getContent(), saved.getCreatedAt(), saved.getUpdatedAt());
    }

    public List<NoteResponse> listMine(String ownerEmail) {
        return noteRepository.findAllByOwnerEmailOrderByUpdatedAtDesc(ownerEmail)
                .stream()
                .map(n -> new NoteResponse(
                        n.getId(),
                        n.getTitle(),
                        crypto.decrypt(n.getCiphertextBase64(), n.getIvBase64()),
                        n.getCreatedAt(),
                        n.getUpdatedAt()
                ))
                .toList();
    }

    public NoteResponse updateMine(String ownerEmail, Long noteId, NoteRequest req) {
        Note note = noteRepository.findByIdAndOwnerEmail(noteId, ownerEmail)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        AesGcmCrypto.EncryptedPayload enc = crypto.encrypt(req.getContent());

        note.setTitle(req.getTitle());
        note.setCiphertextBase64(enc.ciphertextBase64());
        note.setIvBase64(enc.ivBase64());

        Note saved = noteRepository.save(note);

        return new NoteResponse(saved.getId(), saved.getTitle(), req.getContent(), saved.getCreatedAt(), saved.getUpdatedAt());
    }

    public void deleteMine(String ownerEmail, Long noteId) {
        Note note = noteRepository.findByIdAndOwnerEmail(noteId, ownerEmail)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        noteRepository.delete(note);
    }
}
