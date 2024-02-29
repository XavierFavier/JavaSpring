package com.example.demo.dao;

import com.example.demo.dto.UserDTO;


import java.util.List;
import java.util.UUID;

public interface UserDAO {
    public List<Users> getAllUsers();
    public Users getUserById(UUID id);
    public void addUser(UserDTO userDTO);
    public void updateUser( UUID id, UserDTO userDTO);
    public void deleteUser(UUID id);
}
