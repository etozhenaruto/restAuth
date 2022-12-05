package com.example.restAuth.service;

import com.example.restAuth.exception.NotFoundException;
import com.example.restAuth.models.UserStory;
import com.example.restAuth.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStoryService {

    @Autowired
    UserStoryRepository userStoryRepository;

    public List<UserStory> getAllUserStory(){
        return userStoryRepository.findAll();
    }

    public UserStory getUserStoryById(Long id){
        return userStoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("UserStory Not Found with id: " + id));
    }

    public void deleteUserStoryById(Long id){
        if(userStoryRepository.findById(id).isPresent()){
            userStoryRepository.deleteById(id);
        }else throw new NotFoundException("UserStory Not Found with id: " + id);
    }

    public UserStory createUserStory(UserStory userStory){
        return userStoryRepository.save(userStory);
    }

}
