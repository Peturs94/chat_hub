package is.hi.hbv501g.chathub.Chathub.service.implementation;

import is.hi.hbv501g.chathub.Chathub.Model.ChatMessage;
import is.hi.hbv501g.chathub.Chathub.repository.ChatRepository;
import is.hi.hbv501g.chathub.Chathub.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImplementation implements ChatService {

    ChatRepository repository;

    @Autowired
    public ChatServiceImplementation(ChatRepository repository){
        this.repository = repository;
    }

    @Override
    public void save(ChatMessage chatMessage){
        repository.save(chatMessage);
    }

    @Override
    public List<ChatMessage> findAll(){
        return repository.findAll();
    }
}

