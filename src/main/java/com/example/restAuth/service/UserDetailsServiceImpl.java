package com.example.restAuth.service;

import com.example.restAuth.exception.UserNotFoundException;
import com.example.restAuth.models.User;
import com.example.restAuth.pojo.MessageResponse;
import com.example.restAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found whith username: " + username ));
        return UserDetailsImpl.build(user);
    }

    public User findUserById(Long id){
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found with id:" + id));
        return user;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserByUsername(String username){
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User Not Found with username: " + username));
        return user;
    }

    public void deleteUserById(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User Not Found with id:" + id);
        }
    }

}
