package com.example.demo.dao;

import com.example.demo.dto.UserDTO;
import com.example.demo.plugin.GamePlugin;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//TODO get rid of DTO
public class MySQLUserDAO implements UserDAO {

    private List<User> userList = new ArrayList<>();

    public List<User> getAllUsers() {
        return userList;
    }

    public User getUserById(UUID id) {
        for(User user : userList) {
            if(user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(UserDTO userDTO) {
        User user = new User(userDTO.name());
        userList.add(user);
    }

    public void updateUser(UUID id, UserDTO userDTO) {
        for(User user : userList) {
            if(user.getId().equals(id)) {
                user.setName(userDTO.name());
            }
        }
    }

    public void deleteUser(UUID id) {
        for(User user : userList) {
            if(user.getId().equals(id)) {
                userList.remove(user);
                break;
            }
        }
    }
}
