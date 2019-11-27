package is.hi.hbv501g.chathub.Chathub.Controller;

import is.hi.hbv501g.chathub.Chathub.Model.Hub;
import is.hi.hbv501g.chathub.Chathub.service.HubService;
import is.hi.hbv501g.chathub.Chathub.service.MessageService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HubController {

    HubService hubService;
    MessageService messageService;

    @Autowired
    public HubController(HubService hubService, MessageService messageService) {
        this.hubService = hubService;
        this.messageService = messageService;
    }

    @RequestMapping(value = "/createhub", method = RequestMethod.POST)
    public String createHub(@Valid Hub hub, BindingResult result, Model model){
        if(result.hasErrors()){
            return "createhub";
        }
        String name = hub.getName();
        String interest = hub.getInterest();
        String description = hub.getDescription();
        String channeltype = "c";
        String password = hub.getPassword();
        Hub newHub = new Hub(name, interest, description, channeltype, null, password);
        hubService.save(newHub);

        return "redirect:/";
    }

    // fá formið til að creata
    @RequestMapping(value = "/createhub", method = RequestMethod.GET)
    public String createHubForm(Hub hub, HttpSession session, Model model){
        if(session.getAttribute("loggedInUser") == null){
            return "redirect:/";
        }
        model.addAttribute("response", "data");
        return "createhub";
    }
}
