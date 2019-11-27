package is.hi.hbv501g.chathub.Chathub.service;

import is.hi.hbv501g.chathub.Chathub.Model.ChatMessage;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    ChatMessage save(ChatMessage chatMessage);
    List<ChatMessage> findByChannelId(String id);

}
