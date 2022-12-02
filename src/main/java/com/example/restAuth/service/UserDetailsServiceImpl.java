package com.example.restAuth.service;

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

    public UserDetails findById(Long id)  throws UsernameNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found whith id: " + id ));
        return UserDetailsImpl.build(user);
    }
    public List<User> findAll() {
        return userRepository.findAll();
  }
}
