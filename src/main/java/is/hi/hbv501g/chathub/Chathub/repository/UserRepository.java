package is.hi.hbv501g.chathub.Chathub.repository;

import is.hi.hbv501g.chathub.Chathub.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);
    List<User> findAll();
    void delete(User user);

}
