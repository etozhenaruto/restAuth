package com.example.restAuth.controllers;

import com.example.restAuth.models.User;
import com.example.restAuth.pojo.MessageResponse;
import com.example.restAuth.pojo.UpdateUser;
import com.example.restAuth.pojo.UserResponse;
import com.example.restAuth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping("/all")
    public String allAccess() {
        return "public API";
    }

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/user-by-name")
    public ResponseEntity<?> getUserByName(@RequestParam("name") String name){
        User user = userDetailsService.findUserByUsername(name);
        return new ResponseEntity<>(new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()),
                HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/user-by-id")
    public ResponseEntity<?> getUserById(@RequestParam("id")Long id){
        User user = userDetailsService.findUserById(id);
        return new ResponseEntity<>(new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()),
                HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUsersById(@RequestParam("id")Long id){
        userDetailsService.deleteUserById(id);
        return new ResponseEntity<>(new MessageResponse("User deleted"),HttpStatus.OK);
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userDetailsService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/update-user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUser updateUser) {
        System.out.println(updateUser);
        return new ResponseEntity<>(userDetailsService.updateUser(updateUser), HttpStatus.OK);
    }

}
