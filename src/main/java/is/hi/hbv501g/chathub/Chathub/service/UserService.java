package is.hi.hbv501g.chathub.Chathub.service;

import is.hi.hbv501g.chathub.Chathub.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    List<User> findAll(User thisUser);
    User findByuName(String uName);
    void delete(User user);
    User login(User user);

}
