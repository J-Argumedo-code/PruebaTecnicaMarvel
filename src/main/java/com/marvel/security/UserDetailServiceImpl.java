package com.marvel.security;

import com.marvel.model.dao.UserDao;
import com.marvel.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao
                .findOneByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("The user with email " + email + " doesn't exist."));

        return new UserDetailsImpl(user);
    }
}
