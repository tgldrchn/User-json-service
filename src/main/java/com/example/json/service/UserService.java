package com.example.json.service;

import com.example.json.model.UserProfile;
import org.springframework.http.ResponseEntity;
import com.example.json.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserProfileRepository repo;

    public ResponseEntity<?> create(UserProfile profile) {
        
        if (repo.existsById(profile.getUserId())) {
            return ResponseEntity.status(409).body("Profile already exists");
        }

        profile.setCreatedAt(LocalDateTime.now());
        profile.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.status(201).body(repo.save(profile));
    }

    public Optional<UserProfile> getById(String id) {
        return repo.findById(id);
    }

    public UserProfile update(String id, UserProfile updated) {
        updated.setUserId(id);
        updated.setUpdatedAt(LocalDateTime.now());
        return repo.save(updated);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}