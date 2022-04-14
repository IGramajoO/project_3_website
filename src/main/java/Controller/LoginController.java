package Controller;


import com.example.project_3_website.User;
import com.example.project_3_website.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            }
        }
        model.addAttribute("Error_Message", "Incorrect username or password, please try again");
        return "login";
    }


}
