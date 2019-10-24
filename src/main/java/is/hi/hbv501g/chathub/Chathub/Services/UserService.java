package is.hi.hbv501g.chathub.Chathub.Services;

import is.hi.hbv501g.chathub.Chathub.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
