package com.securevault.secure_notes_vault.admin;

import com.securevault.secure_notes_vault.user.User;
import com.securevault.secure_notes_vault.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers() {
        // Simple version: returns users (includes passwordHash -> not ideal for UI, but ok for backend testing)
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PutMapping("/users/{id}/enabled")
    public ResponseEntity<?> setEnabled(@PathVariable Long id, @RequestParam boolean enabled) {
        User u = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        u.setEnabled(enabled);
        userRepository.save(u);
        return ResponseEntity.ok("Updated");
    }
}
