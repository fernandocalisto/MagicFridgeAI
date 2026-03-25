package br.com.calistofernando.MagicFridgeAI.service;

import br.com.calistofernando.MagicFridgeAI.Repository.FoodItemRepository;
import br.com.calistofernando.MagicFridgeAI.model.FoodItemModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public List<FoodItemModel> getAllFoodItem() {
        return foodItemRepository.findAll();
    }

    public FoodItemModel getFoodItemByID(Long id) {
        Optional<FoodItemModel> foodItemByID = foodItemRepository.findById(id);
        return foodItemByID.orElse(null);
    }

    public FoodItemModel addFoodItem (FoodItemModel foodItemModel) {
        return foodItemRepository.save(foodItemModel);
    }

    public void deleteFoodItem (Long id) {
        foodItemRepository.deleteById(id);
    }

    public FoodItemModel updateFoodItem(FoodItemModel foodItemModel, Long id) {
        if (foodItemRepository.existsById(id)){
            foodItemModel.setId(id);
            return foodItemRepository.save(foodItemModel);
        } else {
            return null;
        }
    }
}
