package is.hi.hbv501g.chathub.Chathub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    public HomeController(){
    }

    @RequestMapping("/")
    public String Home(){
        return "index";
    }

}
