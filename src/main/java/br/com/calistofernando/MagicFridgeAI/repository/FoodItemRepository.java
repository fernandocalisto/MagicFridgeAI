package br.com.calistofernando.MagicFridgeAI.repository;

import br.com.calistofernando.MagicFridgeAI.model.FoodItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItemModel, Long> {
}
