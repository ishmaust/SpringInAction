package user.ishmaust.SpringInAction.repository;

import org.springframework.data.repository.CrudRepository;
import user.ishmaust.SpringInAction.data.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
