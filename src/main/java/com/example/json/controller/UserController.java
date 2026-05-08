package com.example.json.controller;

import com.example.json.model.UserProfile;
import com.example.json.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserProfile profile,
                                    HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        profile.setUserId(userId);
        return userService.create(profile); 
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return userService.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestBody UserProfile updated,
                                    HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        if (!userId.equals(id)) {
            return ResponseEntity.status(403).body("Forbidden");
        }
        return ResponseEntity.ok(userService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id,
                                    HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        if (!userId.equals(id)) {
            return ResponseEntity.status(403).body("Forbidden"); 
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}