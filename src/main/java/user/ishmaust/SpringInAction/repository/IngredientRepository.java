package user.ishmaust.SpringInAction.repository;

import org.springframework.data.repository.CrudRepository;
import user.ishmaust.SpringInAction.data.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
