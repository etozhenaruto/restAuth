package com.example.restAuth.controllers;



import com.example.restAuth.models.UserStory;
import com.example.restAuth.pojo.MessageResponse;
import com.example.restAuth.service.UserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userstory/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserStoryController {

    @Autowired
    UserStoryService userStoryService;

    @PostMapping("/create-userstory")
    public ResponseEntity<?> createUserStory(@RequestBody UserStory userStoryRequest){
        UserStory userStory = userStoryService.createUserStory(userStoryRequest);
        return new ResponseEntity<>(userStory,HttpStatus.OK);
    }

    @DeleteMapping("/delete-story")
    public ResponseEntity<?> deleteUserStoryById(Long id){
        userStoryService.deleteUserStoryById(id);
        return new ResponseEntity<>(new MessageResponse("User deleted with id: " + id), HttpStatus.OK);
    }

    @GetMapping("/get-story-by-id")
    public ResponseEntity<UserStory> getUserStory(Long id){
        return new ResponseEntity<>(userStoryService.getUserStoryById(id),HttpStatus.OK);
    }

    @GetMapping("/all-users-story")
    public ResponseEntity<List<UserStory>> getAllUserStory(){
        return new ResponseEntity<>(userStoryService.getAllUserStory(),HttpStatus.OK);
    }
}
