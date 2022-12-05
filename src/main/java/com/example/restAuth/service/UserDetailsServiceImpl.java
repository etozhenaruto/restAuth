package com.example.restAuth.service;

import com.example.restAuth.exception.NotFoundException;
import com.example.restAuth.models.User;
import com.example.restAuth.pojo.UpdateUser;
import com.example.restAuth.pojo.UserResponse;
import com.example.restAuth.repository.UserRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found whith username: " + username ));
        return UserDetailsImpl.build(user);
    }

    public User findUserById(Long id){
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User Not Found with id: " + id));
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserByUsername(@NotNull String username) {
        return userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new NotFoundException("User not found with name: " + username));
    }
    public void deleteUserById(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else {
            throw new NotFoundException("User Not Found with id: " + id);
        }
    }

    public UserResponse updateUser(UpdateUser updateUser){
        User user = findUserById(updateUser.getId());
        user.setEmail(updateUser.getEmail());
        if(!Objects.equals(user.getUsername(), updateUser.getUsername())){
            throw new NotFoundException("ИМЯ МЕНЯТЬ НЕЛЬЗЯ ХИТРЫЙ ТЫ ЖУК");
        }
        userRepository.save(user);
        return new UserResponse(user.getId(),user.getUsername(),user.getEmail());
    }

}
