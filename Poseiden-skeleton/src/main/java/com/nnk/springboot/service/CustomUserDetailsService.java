package com.nnk.springboot.service;


import com.nnk.springboot.model.User;
import com.nnk.springboot.repositories.UserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    GrantedAuthority authority = new SimpleGrantedAuthority((user.getRole()));
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), Collections.singletonList(authority));
  }

}
