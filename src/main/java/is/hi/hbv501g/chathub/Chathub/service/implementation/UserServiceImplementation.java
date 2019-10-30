package is.hi.hbv501g.chathub.Chathub.service.implementation;

import is.hi.hbv501g.chathub.Chathub.Model.User;
import is.hi.hbv501g.chathub.Chathub.repository.UserRepository;
import is.hi.hbv501g.chathub.Chathub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    UserRepository repository;

    @Autowired
    public UserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }
}
