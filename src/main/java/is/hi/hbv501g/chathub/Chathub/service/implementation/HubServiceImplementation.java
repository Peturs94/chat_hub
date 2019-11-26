package is.hi.hbv501g.chathub.Chathub.Service.Implementation;

import is.hi.hbv501g.chathub.Chathub.Model.Hub;
import is.hi.hbv501g.chathub.Chathub.Repository.HubRepository;
import is.hi.hbv501g.chathub.Chathub.Service.HubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.findAll();
    }

    @Override
    public Optional<Hub> findByChannelId(String channelId) {
        return repository.findByChannelId(channelId);
    }
}
