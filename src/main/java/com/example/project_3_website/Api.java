package com.example.project_3_website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path="/allTeams")
    Iterable<Team> allTeams(){
        return teamRepository.findAll();
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


    @PostMapping("/addHeroToTeam")
    public @ResponseBody String addHeroToTeam(@RequestParam String username, @RequestParam int teamId, @RequestParam int heroId){
        RestSpringBootController restSpringBootController = new RestSpringBootController();

        if (restSpringBootController.canUse(heroId)=="0"){
            return "sorry, please choose another hero";
        }
        String heroInfo = restSpringBootController.getHeroInfo(heroId);
        heroInfo = heroInfo.replaceFirst("^[^\\-\\d]*", "");
        String[] numbers = heroInfo.split("[^\\-\\d]+");
        Heroes hero = new Heroes(heroId, Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1]),Integer.parseInt(numbers[2]),Integer.parseInt(numbers[3]),Integer.parseInt(numbers[4]),Integer.parseInt(numbers[5]));

        if(teamRepository.existsById(teamId)){
            Team team = teamRepository.findByTeamId(teamId);
            if (userRepository.findUserByUsername(username)!=null){
                team.addHeroes(hero);

                User user1 = userRepository.findUserByUsername(username);

                List<Team> teamX = user1.getTeamList();

                if (teamX.contains(team)) {
                    team.addHeroes(hero);

                    userRepository.save(user1);
                    heroRepository.save(hero);
                    return "team added: "+ team.toString();
                } else {
                    return "team not found";
                }

            } else {
                return "username not found";
            }
        }

        return "";
    }

    @PostMapping("/randomTeam")
    public @ResponseBody Team addHeroToTeam() {
        Team rocket = new Team();
        RestSpringBootController restSpringBootController = new RestSpringBootController();
//
//        if (restSpringBootController.canUse(heroId)=="0"){
//            return "sorry, please choose another hero";
//        }
//        String heroInfo = restSpringBootController.getHeroInfo(heroId);
//        heroInfo = heroInfo.replaceFirst("^[^\\-\\d]*", "");
//        String[] numbers = heroInfo.split("[^\\-\\d]+");
//        Heroes hero = new Heroes(heroId, Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1]),Integer.parseInt(numbers[2]),Integer.parseInt(numbers[3]),Integer.parseInt(numbers[4]),Integer.parseInt(numbers[5]));


        return null;
    }



    }
