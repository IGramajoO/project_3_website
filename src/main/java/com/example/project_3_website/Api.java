package com.example.project_3_website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api")
public class Api {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/allUsers")
    public Iterable<User> getAllUsers(){
            return userRepository.findAll();
        }

    @PostMapping(path="/addUser")
    public String addUser(@RequestParam String username, @RequestParam String password) {
        User user = new User(username, password);

        userRepository.save(user);
        return "saved";
    }
}
