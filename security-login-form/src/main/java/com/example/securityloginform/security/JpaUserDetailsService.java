package com.example.securityloginform.security;

import com.example.securityloginform.CustomerUserDetails;
import com.example.securityloginform.UserRepository;
import com.example.securityloginform.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public CustomerUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User u = userRepository.findUserByUsername(username)
        .orElseThrow(()-> new UsernameNotFoundException("Problem during authentication!"));

    return new CustomerUserDetails(u);
  }
}
