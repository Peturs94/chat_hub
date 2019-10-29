package is.hi.hbv501g.chathub.Chathub.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    public HomeController(){
    }

    @RequestMapping(value= "/", method = RequestMethod.GET)
    public String Home(){
        return "chat";
    }


}
