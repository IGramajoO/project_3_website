package com.example.project_3_website;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

    @GetMapping(path="/teamById")
    Iterable<Team> teamById(@RequestParam int user_id){
        return teamRepository.findTeamByUserId(user_id);
    }

    @GetMapping(path="/teamByUsername")
    Iterable<Team> teamByUsername(@RequestParam String username){
        return teamRepository.findTeamByUsername(username);
    }

    @PostMapping(path="/addUser")
    public String addUser(@RequestParam String username, @RequestParam String password) {
        User user = new User(username, password);
        userRepository.save(user);
        return "saved";
    }

    @PostMapping("/addTeam")
    public @ResponseBody String addteam(@RequestParam String username, HttpServletResponse response) throws IOException {
        Team team = new Team();
        if (userRepository.findUserByUsername(username)!=null){
            User user1 = userRepository.findUserByUsername(username);
            user1.addTeams(team);
            userRepository.save(user1);
            teamRepository.save(team);
            response.sendRedirect("/accountPage");
            return "accountPage";
        } else {
            return "login";
        }
    }


    @GetMapping("/addHeroToTeam")
    public @ResponseBody String addHeroToTeam(@RequestParam String username, @RequestParam int teamId, @RequestParam int heroId, HttpServletResponse response) throws IOException {
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
                    teamRepository.save(team);
                    heroRepository.save(hero);
                    response.sendRedirect("/team?team_id=" + teamId);
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

    @GetMapping("/randomTeam")
    public @ResponseBody String addHeroToTeam() {
        Team rocket = new Team();
        RestSpringBootController restSpringBootController = new RestSpringBootController();

        List<Heroes> heroes = new ArrayList<>();

        while (heroes.size()<5){
            int randId = 1 + (int)(Math.random() * ((731 - 1) + 1));
            while (restSpringBootController.canUse(randId) == "0") {
                randId = 1 + (int)(Math.random() * ((731 - 1) + 1));
                if (restSpringBootController.canUse(randId) == "1") {
                    String heroInfo = restSpringBootController.getHeroInfo(randId);
                    heroInfo = heroInfo.replaceFirst("^[^\\-\\d]*", "");
                    String[] numbers = heroInfo.split("[^\\-\\d]+");
                    Heroes hero = new Heroes(randId, Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]), Integer.parseInt(numbers[2]), Integer.parseInt(numbers[3]), Integer.parseInt(numbers[4]), Integer.parseInt(numbers[5]));
                    heroes.add(hero);
                    break;
                }
            }
        }

        rocket.setHeroesList(heroes);

        teamRepository.save(rocket);
        return rocket.toString();
    }

    @GetMapping("/teamVictor")
    public @ResponseBody String teamVictory(@RequestParam int teamIdA, @RequestParam int teamIdB) {
        Team teamA = teamRepository.findByTeamId(teamIdA);
        Team teamB = teamRepository.findByTeamId(teamIdB);

        double scoreA=0, scoreB=0;

        for (Heroes h: teamA.getHeroesList()){
            scoreA+=heroScore(h);
        }

        for (Heroes h: teamB.getHeroesList()){
            scoreB+=heroScore(h);
        }

        // returns 1 if team A (home team or user team) wins
        // and 0 if team B (incoming team or randomly generated team) wins
        if (scoreA>scoreB){
            return "1";
        } else {
            return "0";
        }

    }

    public double heroScore(Heroes hero){

        double score = (3*hero.getPower() + hero.getIntelligence()+2*hero.getSpeed()+2*hero.getDurability()+hero.getCombat()
                + 3*hero.getStrength()) / 5.0;
//        (3*data1.power+data1.intelligence+2*data1.speed+2*data1.durability+data1.combat)/5;
        return score;
    }


    }
