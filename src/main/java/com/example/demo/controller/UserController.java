package com.example.demo.controller;

import com.example.demo.dao.JbdcDao;
import com.example.demo.dao.MySQLUserDAO;
import com.example.demo.dao.Users;
import com.example.demo.dao.UserRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.security.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
public class UserController {
    //DAO
    //private MySQLUserDAO myDAO = new MySQLUserDAO();
    private JbdcDao myDAO = new JbdcDao();

    //@Secured("ROLE_USER")
    //@Secured("ROLE_ADMIN")
    @GetMapping("/allUsers")
    public List<Users> getAllUsers() {
        return myDAO.getAllUsers();
    }

    @GetMapping("/test")
    public String test() {
        return "hello!";
    }

    @GetMapping("/user")
    public UserDTO getUserById(@RequestParam String id) {
        Users user = myDAO.getUserById(id);
        UserDTO userDTO = new UserDTO(UUID.fromString(user.getUuid()), user.getName());
        return userDTO;
    }

    @PostMapping("/user")
    public String addUser(@RequestParam("name") String name) {
        String id = UUID.randomUUID().toString();
        myDAO.addUser(id, name);
        return id;
    }

    @PutMapping("/user")
    public void updateUser(@RequestParam String id,
                           @RequestParam String newID, @RequestParam String newName) {

        myDAO.updateUser(id, newID, newName);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String id) {
        myDAO.deleteUser(id);
    }

    @Autowired
    MessageSource messageSource;

    @GetMapping("/testLang")
    public String getName(@RequestHeader("Accept-Language") Locale locale) {
        return messageSource.getMessage("con", null, locale);
    }

    //3. JPA

//    @Autowired // This means to get the bean called userRepository
//    // Which is auto-generated by Spring, we will use it to handle the data
//    private UserRepository userRepository;
//
//    @PostMapping(path="/user") // Map ONLY POST Requests
//    public @ResponseBody String addNewUser (@RequestParam String name) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        Users n = new Users();
//        n.setName(name);
//        n.setUuid(UUID.randomUUID().toString());
//
//        userRepository.save(n);
//
//        return n.getUuid();
//    }
//
//    @GetMapping(path="/allUsers")
//    public @ResponseBody Iterable<Users> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return userRepository.findAll();
//    }
}
