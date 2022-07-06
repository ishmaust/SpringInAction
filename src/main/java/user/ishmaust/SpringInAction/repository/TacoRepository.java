package user.ishmaust.SpringInAction.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import user.ishmaust.SpringInAction.data.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
