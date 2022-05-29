package user.ishmaust.SpringInAction.repository;

import org.springframework.data.repository.CrudRepository;
import user.ishmaust.SpringInAction.data.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
