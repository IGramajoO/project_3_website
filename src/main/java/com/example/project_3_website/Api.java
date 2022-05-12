package com.example.project_3_website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api")
public class Api {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private HeroRepository heroRepository;

    @GetMapping(path="/allUsers")
    Iterable<User> allUsers(){
            return userRepository.findAll();
        }

    @PostMapping(path="/addUser")
    public String addUser(@RequestParam String username, @RequestParam String password) {
        User user = new User(username, password);

        userRepository.save(user);
        return "saved";
    }



    @PostMapping("/addTeam")
    public @ResponseBody String addteam(@RequestParam String username){
        Team team = new Team();
        if (userRepository.findUserByUsername(username)!=null){
            User user1 = userRepository.findUserByUsername(username);
            user1.addTeams(team);
            userRepository.save(user1);
            teamRepository.save(team);
            return "team added";
        } else {
            return "username not found";
        }
    }




}
