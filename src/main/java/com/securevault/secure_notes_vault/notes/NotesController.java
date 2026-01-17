package com.securevault.secure_notes_vault.notes;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    private String email(Authentication auth) {
        return auth.getName(); // because our JWT subject is email
    }

    @PostMapping
    public ResponseEntity<NoteResponse> create(Authentication auth, @Valid @RequestBody NoteRequest req) {
        return ResponseEntity.ok(notesService.create(email(auth), req));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponse>> listMine(Authentication auth) {
        return ResponseEntity.ok(notesService.listMine(email(auth)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> update(Authentication auth, @PathVariable Long id, @Valid @RequestBody NoteRequest req) {
        return ResponseEntity.ok(notesService.updateMine(email(auth), id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(Authentication auth, @PathVariable Long id) {
        notesService.deleteMine(email(auth), id);
        return ResponseEntity.ok("Deleted");
    }
}
