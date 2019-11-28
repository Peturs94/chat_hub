package is.hi.hbv501g.chathub.Chathub.service.implementation;

import is.hi.hbv501g.chathub.Chathub.Model.User;
import is.hi.hbv501g.chathub.Chathub.repository.UserRepository;
import is.hi.hbv501g.chathub.Chathub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    // Gets list of all Users,
    // Returns list of all Users except the logged in user.
    @Override
    public List<User> findAll(User thisUser) {
        List<User> allUsers = repository.findAll();
        List<User> newUsers = new ArrayList<User>();
        for(User user : allUsers){
            if(!thisUser.getuName().equals(user.getuName())){
                newUsers.add(user);
            }
        }
        return newUsers;
    }

    // Searches for a user with the username uName.
    @Override
    public User findByuName(String uName) {
        return repository.findByuName(uName);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    //Login checker.
    // checks if the User exists and therefore if passwords match
    // returns the user if login was successful.
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
