package com.zidio.connect.admin;

import com.zidio.connect.user.UserAccount;
import com.zidio.connect.user.UserAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserAccountRepository userRepo;

    public AdminController(UserAccountRepository userRepo) { this.userRepo = userRepo; }

    @GetMapping("/users")
    public List<UserAccount> users() { return userRepo.findAll(); }

    @PostMapping("/users/{id}/toggle")
    public ResponseEntity<UserAccount> toggleEnabled(@PathVariable Long id) {
        return userRepo.findById(id)
                .map(u -> { u.setEnabled(!u.isEnabled()); return ResponseEntity.ok(userRepo.save(u)); })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

