package is.hi.hbv501g.chathub.Chathub.Controller;

import is.hi.hbv501g.chathub.Chathub.Model.User;
import is.hi.hbv501g.chathub.Chathub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    UserService userService;
    @Autowired
    public LoginController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(User user){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@Valid User user, BindingResult result, Model model, HttpSession session){
        //Stuff
        if(result.hasErrors()){
            // model msg þar sem stendur að ehv hafi verið rangt
            return "login";
        }
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("loggedInUser", exists);
            // breyta þessu í /chat og hafa redirect:/ þar ef enginn loggedinuser.
            return "redirect:/chat";
        }
        // msg með villuboðum um að notendanafn eða pw sé rangt.
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(HttpSession session){
        session.setAttribute("loggedInUser", null);
        return "redirect:/";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGet(User user){
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@Valid User user, BindingResult result, Model model){
        // stuff
        if(result.hasErrors()){
            return "signup";
        }
        User exists = userService.findByuName(user.getuName());
        if(exists == null){
            userService.save(user);
        }
        else {
            return "signup";
        }

        return "redirect:/login";
    }

}
