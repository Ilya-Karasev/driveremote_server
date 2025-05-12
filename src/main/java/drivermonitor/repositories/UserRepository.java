package drivermonitor.repositories;

import drivermonitor.models.Post;
import drivermonitor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsById(Integer id);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findById(Integer id);
    List<User> findByIdIn(List<Integer> ids);
    Optional<User> findByEmail(String email);
    List<User> findByPost(Post post);
}
