package is.hi.hbv501g.chathub.Chathub.repository;

import is.hi.hbv501g.chathub.Chathub.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    List<User> findAll();
    User findById(long id);
    User findByuName(String uName);
    void delete(User user);
}
