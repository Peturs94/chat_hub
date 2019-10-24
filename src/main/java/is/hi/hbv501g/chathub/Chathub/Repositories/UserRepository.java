package is.hi.hbv501g.chathub.Chathub.Repositories;

import is.hi.hbv501g.chathub.Chathub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}