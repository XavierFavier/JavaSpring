package com.example.demo.dao;

import com.example.demo.dto.UserDTO;
import com.example.demo.plugin.GamePlugin;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//todo: get rid of DTO
public class MySQLUserDAO implements UserDAO {

    private List<Users> userList = new ArrayList<>();

    public List<Users> getAllUsers() {
        return userList;
    }

    public Users getUserById(UUID id) {
        for(Users user : userList) {
            if(user.getUuid().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(UserDTO userDTO) {
        Users user = new Users();
        user.setName(userDTO.getName());
        userList.add(user);
    }

    public void updateUser(UUID id, UserDTO userDTO) {
        for(Users user : userList) {
            if(user.getUuid().equals(id)) {
                user.setName(userDTO.getName());
            }
        }
    }

    public void deleteUser(UUID id) {
        for(Users user : userList) {
            if(user.getUuid().equals(id)) {
                userList.remove(user);
                break;
            }
        }
    }
}
