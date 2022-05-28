package user.ishmaust.SpringInAction.repository;

import user.ishmaust.SpringInAction.data.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
