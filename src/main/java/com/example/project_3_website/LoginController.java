package com.example.project_3_website;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @RequestMapping(value = "/login")
    String login(){
        return "login";
    }

    @RequestMapping(value = "/profile")
    String profile(HttpSession session){
        if(session.getAttribute("User_Session") == null){
            return "login";
        }
        else{
            return "profile";
        }

    }

    @RequestMapping(value = "/accountPage")
    String accountPage(HttpSession session){

        if(session.getAttribute("User_Session") == null){
            return "login";
        }
        else{
            return "accountPage";
        }
    }

    @RequestMapping(value = "/team")
    String team(@RequestParam int team_id, HttpSession session, Model model) throws JSONException {

        Team team = teamRepository.findByTeamId(team_id);

        int[] heroIds = new int[team.heroesList.size()];
        if(!team.heroesList.isEmpty()){
            for(int i = 0; i < team.heroesList.size(); i++){
                heroIds[i] = team.heroesList.get(i).getId();
            }
            model.addAttribute("heroIds", heroIds);
        }
//        else{
            model.addAttribute("team", team);

//        }

        if(session.getAttribute("User_Session") == null){
            return "login";
        }
        else{
            return "team";
        }
    }

    @PostMapping(value = "/newTeam")
    String newTeam(HttpSession session, @RequestParam String username){
        Team team = new Team();
        if (userRepository.findUserByUsername(username)!=null){
            User user1 = userRepository.findUserByUsername(username);
            user1.addTeams(team);
            userRepository.save(user1);
            teamRepository.save(team);
            return "accountPage";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/addCharacter")
    String addCharacter(HttpSession session){

        if(session.getAttribute("User_Session") == null){
            return "login";
        }
        else{
            return "addCharacter";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String login(HttpServletRequest response,
                 HttpSession session,
                 Model model,
                 @RequestParam String username,
                 @RequestParam String password) throws IOException {
        User user = userRepository.findUserByUsername(username);
        if(user != null){
            if(user.getPassword().equals(password)){
                session.setAttribute("User_Session", user);
                return "accountPage";
            }
        }
        model.addAttribute("Error_Message", "Incorrect username or password, please try again");
        return "login";
    }

    @RequestMapping(value = "/user")
    public String user(Principal principal,
                       HttpSession session,
                       Model model) throws IOException{
        System.out.println(principal);
        User user = userRepository.findUserByUsername(principal.getName().substring(0,10));
        User newUser = new User();
        if(user == null){
            newUser.setUsername(principal.getName().substring(0,10));
            newUser.setPassword("password@");
            userRepository.save(newUser);
            session.setAttribute("User_Session", newUser);
            return "accountPage";
        }
        session.setAttribute("User_Session", user);
        return "accountPage";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    void logout(HttpServletResponse response, HttpSession session) throws IOException {
        session.setAttribute("User_Session", null);
        response.sendRedirect("/login");
    }

    @RequestMapping(value = "/createAccount")
    String create_account(Model model){
        return "createAccount";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    String create_account(HttpServletResponse response,
                          HttpServletRequest request,
                          Model model,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String confirmPassword) throws IOException{
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            if(!username.equals("")){
                if(password.equals(confirmPassword)){
                    /*
                        This is to force special character in order to have a Stronger
                        login credential
                     */
                    Pattern pattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
                    Matcher match = pattern.matcher(password);
                    if(password.length() >= 6 && match.find()){
                        userRepository.save(new User(username,password));
                        return "login";
                        //Redirect to the home page
                    }
                    else{
                        model.addAttribute("error_message", "Password must have at least 6 characters and 1 special character. ");
                    }
                }
                else{
                    model.addAttribute("error_message", "Passwords do not match.");
                }
            }
            else{
                model.addAttribute("error_message", "Username field can't be empty.");
            }
        }
        else{
            model.addAttribute("error_message", "Username has been taken.");
        }

        return "createAccount";
    }


}
