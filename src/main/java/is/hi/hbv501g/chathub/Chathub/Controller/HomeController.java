package is.hi.hbv501g.chathub.Chathub.Controller;



import is.hi.hbv501g.chathub.Chathub.Model.Hub;
import is.hi.hbv501g.chathub.Chathub.Model.Mood;
import is.hi.hbv501g.chathub.Chathub.Model.User;
import is.hi.hbv501g.chathub.Chathub.service.HubService;
import is.hi.hbv501g.chathub.Chathub.service.MessageService;
import is.hi.hbv501g.chathub.Chathub.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.Binding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
        hub = new Hub("main", "main", "main", "c", null, "");
        hubService.save(hub);
    }




    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String Chat(Mood mood, HttpSession session, Model model){
        // 11111 fyrir public msgs.
        if(session.getAttribute("loggedInUser") == null){
            return "redirect:/login";
        }
        model.addAttribute("thisUser", session.getAttribute("loggedInUser"));
        User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("users", userService.findAll(user));
        model.addAttribute("thishub", hub);
        model.addAttribute("hubs", hubService.findAll());
        model.addAttribute("messages", messageService.findByChannelId(hub.getChannelId()));
        model.addAttribute("response", "data");
        return "chat";
    }

    @RequestMapping(value = "/chat/{id}", method = RequestMethod.GET)
    public String getHubMsgTemplate(@PathVariable("id") String id, Mood mood, HttpSession session, Model model){
        Hub thishub = null;
        if(session.getAttribute("loggedInUser") == null){
            return "redirect:/login";
        }
        if(id.charAt(0)=='c') {
            thishub = hubService.findById(Long.parseLong(id.substring(1, id.length()))).orElseThrow(() -> new IllegalArgumentException("Invalid hub ID"));
            System.out.println("FYRSTA IF: " + thishub.getChannelId() + " " + thishub.getName() + " LONG " + Long.parseLong(id.substring(1, id.length())));
            model.addAttribute("messages", messageService.findByChannelId("c" + thishub.getId()));
        }
        else if(!hubService.exists(id)){
            thishub = hubService.save(new Hub("private chat", "priv", "priv", "p", id, ""));
            model.addAttribute("messages", messageService.findByChannelId(thishub.getChannelId()));
        } else {
            thishub = hubService.findByChannelId(id);
            model.addAttribute("messages", messageService.findByChannelId(thishub.getChannelId()));
        }
        model.addAttribute("thishub", thishub);
        model.addAttribute("thisUser", session.getAttribute("loggedInUser"));
        User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("users", userService.findAll(user));
        model.addAttribute("hubs", hubService.findAll());
        model.addAttribute("response", "data");
        return "chat";
    }

    @RequestMapping("/")
    public String Home(HttpSession session, Model model){
        if(session.getAttribute("loggedInUser") != null){
            return "redirect:/chat";
        }
        model.addAttribute("response", "nodata");
        return "home";
    }

    @RequestMapping(value = "/changemood", method = RequestMethod.POST)
    public String ChangeMoodPost(@Valid Mood mood, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return "redirect:/chat";
        }
        User user = (User) session.getAttribute("loggedInUser");
        String newMood = mood.getName();
        user.setMood(newMood);
        userService.save(user);
        return "redirect:/chat";
    }


}
