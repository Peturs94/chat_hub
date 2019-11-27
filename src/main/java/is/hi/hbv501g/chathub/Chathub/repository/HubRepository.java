package is.hi.hbv501g.chathub.Chathub.repository;

import is.hi.hbv501g.chathub.Chathub.Model.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HubRepository extends JpaRepository<Hub, Long> {
    Hub save(Hub hub);
    Optional<Hub> findById(long id);
    List<Hub> findAll();
    Hub findByChannelId(String channelId);
}
