package is.hi.hbv501g.chathub.Chathub.Controllers;

import is.hi.hbv501g.chathub.Chathub.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    ChatService chatService;
    @Autowired
    public HomeController(ChatService chatService){
        this.chatService = chatService;
    }

    @RequestMapping("/")
    public String Home(Model model){
        model.addAttribute("messages", chatService.findAll());
        return "chatting";
    }


}
