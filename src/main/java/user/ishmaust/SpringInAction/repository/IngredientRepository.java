package user.ishmaust.SpringInAction.repository;

import user.ishmaust.SpringInAction.data.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
