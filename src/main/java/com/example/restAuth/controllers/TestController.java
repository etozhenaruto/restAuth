package com.example.restAuth.controllers;

import com.example.restAuth.models.User;
import com.example.restAuth.pojo.UserResponse;
import com.example.restAuth.service.UserDetailsImpl;
import com.example.restAuth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByName(@PathParam("name") String name){
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(name);
        return ResponseEntity.ok(new UserResponse(userDetails.getId(), userDetails.getUsername(),userDetails.getEmail()));
    }

    @GetMapping("/id")
    public ResponseEntity<?> getUserById(){
        long i = 34;
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.findById(i);
        return ResponseEntity.ok(new UserResponse(userDetails.getId(), userDetails.getUsername(),userDetails.getEmail()));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userDetailsService.findAll();
        System.out.println("allarm");
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
