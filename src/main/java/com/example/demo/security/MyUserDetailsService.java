package com.example.demo.security;

import com.example.demo.dao.JbdcDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JbdcDao myDao = new JbdcDao();
        myDao.getUserByName(username);
        UserEntity myEntity = new UserEntity();
        return myEntity;
    }
 }
