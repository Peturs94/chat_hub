package is.hi.hbv501g.chathub.Chathub.service;

import is.hi.hbv501g.chathub.Chathub.Model.Hub;

import java.util.List;
import java.util.Optional;

public interface HubService {
    Hub save(Hub hub);
    Optional<Hub> findById(long id);
    List<Hub> findAll();
    Hub findByChannelId(String channelId);
    boolean exists(String channelId);
}
