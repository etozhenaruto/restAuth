package com.example.restAuth.controllers;

import com.example.restAuth.models.User;
import com.example.restAuth.pojo.MessageResponse;
import com.example.restAuth.pojo.UserResponse;
import com.example.restAuth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping("/all")
    public String allAccess() {
        return "public API";
    }

    @GetMapping("/users1")
    public ResponseEntity<?> getUserByName(@RequestParam("name") String name){
        User user = userDetailsService.findUserByUsername(name);
        return new ResponseEntity<>(new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()),
                HttpStatus.OK);
    }
    @GetMapping("/users2")
    public ResponseEntity<?> getUserById(@RequestParam("id")Long id){
        User user = userDetailsService.findUserById(id);
        return new ResponseEntity<>(new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()),
                HttpStatus.OK);
    }

    @DeleteMapping("/users3")
    public ResponseEntity<?> deleteUsersById(@RequestParam("id")Long id){
        userDetailsService.deleteUserById(id);
        return new ResponseEntity<>(new MessageResponse("User deleted"),HttpStatus.OK);
    }


    @GetMapping("/users4")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userDetailsService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "user API";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String moderatorAccess() {
        return "moderator API";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "admin API";
    }
}
