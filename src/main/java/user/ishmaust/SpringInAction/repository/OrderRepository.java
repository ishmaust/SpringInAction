package user.ishmaust.SpringInAction.repository;

import user.ishmaust.SpringInAction.data.Order;

public interface OrderRepository {
    Order save(Order order);
}
