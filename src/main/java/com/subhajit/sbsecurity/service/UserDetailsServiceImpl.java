package com.subhajit.sbsecurity.service;

import com.subhajit.sbsecurity.dao.User;
import com.subhajit.sbsecurity.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  {
    System.out.println("[SPRING SECURITY] UserDetailsServiceImpl initialized!!");
  }
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    System.out.println("[SPRING SECURITY] loadUserByUsername in UserDetailsServiceImpl called and user-details fetched!!");

    return UserDetailsImpl.build(user);
  }

}
