package is.hi.hbv501g.chathub.Chathub.repository;
import is.hi.hbv501g.chathub.Chathub.Model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    ChatMessage save(ChatMessage chatMessage);
    List<ChatMessage> findAll();
}
