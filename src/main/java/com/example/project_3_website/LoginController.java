package com.example.project_3_website;

import com.example.project_3_website.User;
import com.example.project_3_website.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/login")
    String login(){
        return "login";
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
