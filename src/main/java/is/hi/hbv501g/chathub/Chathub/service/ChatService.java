package is.hi.hbv501g.chathub.Chathub.service;

import is.hi.hbv501g.chathub.Chathub.Model.ChatMessage;

import java.util.List;

public interface ChatService {

    void save(ChatMessage chatMessage);
    List<ChatMessage> findAll();


}
