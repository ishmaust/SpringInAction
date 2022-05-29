package user.ishmaust.SpringInAction.repository;

import org.springframework.data.repository.CrudRepository;
import user.ishmaust.SpringInAction.data.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
