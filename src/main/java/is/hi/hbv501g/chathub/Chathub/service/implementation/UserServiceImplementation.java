package is.hi.hbv501g.chathub.Chathub.Service.Implementation;

import is.hi.hbv501g.chathub.Chathub.Model.User;
import is.hi.hbv501g.chathub.Chathub.Repository.UserRepository;
import is.hi.hbv501g.chathub.Chathub.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    UserRepository repository;

    @Autowired
    public UserServiceImplementation(UserRepository repository){
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
    public User findByuName(String uName) {
        return repository.findByuName(uName);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public User login(User user) {
        User exists = repository.findByuName(user.getuName());
        if(exists == null){
            return null;
        }
        else if(!exists.getPassword().equals(user.getPassword())){
            return null;
        }
        return exists;
    }


}
