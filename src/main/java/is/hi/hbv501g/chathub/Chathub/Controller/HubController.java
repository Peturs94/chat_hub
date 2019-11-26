package is.hi.hbv501g.chathub.Chathub.Controller;

import is.hi.hbv501g.chathub.Chathub.Model.Hub;
import is.hi.hbv501g.chathub.Chathub.Service.HubService;
import is.hi.hbv501g.chathub.Chathub.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

        hubService.save(hub);
        model.addAttribute("thishub", hubService.findById(0));

        model.addAttribute("messages", messageService.findByChannelId("0"));
        model.addAttribute("hubs", hubService.findAll());
        return "redirect:/";
    }

    // fá formið til að creata
    @RequestMapping(value = "/createhub", method = RequestMethod.GET)
    public String createHubForm(Hub hub){
        return "createhub";
    }
}
