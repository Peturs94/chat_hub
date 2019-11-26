package is.hi.hbv501g.chathub.Chathub.Controller;



import is.hi.hbv501g.chathub.Chathub.Model.Hub;
import is.hi.hbv501g.chathub.Chathub.Service.HubService;
import is.hi.hbv501g.chathub.Chathub.Service.MessageService;
import is.hi.hbv501g.chathub.Chathub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    HubService hubService;
    MessageService messageService;
    UserService userService;
    public Hub hub;

    @Autowired
    public HomeController(HubService hubService, MessageService messageService, UserService userService) {
        this.hubService = hubService;
        this.messageService = messageService;
        this.userService = userService;
        //eyða seinna
        hub = new Hub("main", "main", "main", "c", null);
        hubService.save(hub);
    }




    @RequestMapping("/chat")
    public String Chat(Model model, HttpSession session){
        // 11111 fyrir public msgs.
        if(session.getAttribute("loggedInUser") == null){
            return "redirect:/login";
        }
        model.addAttribute("thisUser", session.getAttribute("loggedInUser"));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("thishub", hub);
        model.addAttribute("hubs", hubService.findAll());
        model.addAttribute("messages", messageService.findByChannelId(hub.getChannelId()));
        return "chat";
    }

    @RequestMapping(value = "/chat/{id}", method = RequestMethod.GET)
    public String getHubMsgTemplate(@PathVariable("id") String id, Model model, HttpSession session){
        Hub thishub = null;
        if(session.getAttribute("loggedInUser") == null){
            return "redirect:/login";
        }
        if(id.charAt(0)=='c') {
            thishub = hubService.findByChannelId(id).orElseThrow(() -> new IllegalArgumentException("Invalid hub ID"));
        }
        else {
            thishub = hubService.save(new Hub("priv", "priv", "priv", "p", id));
        }
        model.addAttribute("thishub", thishub);
        model.addAttribute("thisUser", session.getAttribute("loggedInUser"));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("hubs", hubService.findAll());
        model.addAttribute("messages", messageService.findByChannelId(id));
        return "chat";
    }

    @RequestMapping("/")
    public String Home(HttpSession session){
        if(session.getAttribute("loggedInUser") != null){
            return "redirect:/chat";
        }
        return "home";
    }


}
