package is.hi.hbv501g.chathub.Chathub.service.implementation;

import is.hi.hbv501g.chathub.Chathub.Model.Hub;
import is.hi.hbv501g.chathub.Chathub.repository.HubRepository;
import is.hi.hbv501g.chathub.Chathub.service.HubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HubServiceImplementation implements HubService {

    HubRepository repository;

    @Autowired
    public HubServiceImplementation(HubRepository repository) {
        this.repository = repository;
    }

    @Override
    public Hub save(Hub hub) {
        return repository.save(hub);
    }

    @Override
    public Optional<Hub> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Hub> findAll() {
        List<Hub> hubs = repository.findAll();
        List<Hub> newHubs = new ArrayList<Hub>();
        for(Hub hub : hubs){
            if(hub.getChannelType() =="c"){
                newHubs.add(hub);
            }
        }
        return newHubs;
    }

    @Override
    public Hub findByChannelId(String channelId) {
        return repository.findByChannelId(channelId);
    }

    @Override
    public boolean exists(String channelId) {
        Hub thisHub = repository.findByChannelId(channelId);
        if(thisHub != null){
            return true;
        }
        return false;
    }
}
