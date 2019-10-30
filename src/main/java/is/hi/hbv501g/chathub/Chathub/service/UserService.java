package is.hi.hbv501g.chathub.Chathub.service;

import is.hi.hbv501g.chathub.Chathub.Model.User;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(User user);
}
