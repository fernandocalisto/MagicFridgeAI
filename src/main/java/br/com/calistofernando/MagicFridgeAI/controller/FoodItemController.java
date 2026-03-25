package br.com.calistofernando.MagicFridgeAI.controller;

import br.com.calistofernando.MagicFridgeAI.model.FoodItemModel;
import br.com.calistofernando.MagicFridgeAI.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor

public class FoodItemController {

    private final FoodItemService foodItemService;

    @GetMapping("/all")
    public ResponseEntity<List<FoodItemModel>> getAllFoodItem() {
        List<FoodItemModel> foodList = foodItemService.getAllFoodItem();
        return ResponseEntity.ok(foodList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getFoodItemByID(@PathVariable Long id) {
        FoodItemModel foodItemByID = foodItemService.getFoodItemByID(id);
        if (foodItemByID != null) {
            return ResponseEntity.ok(foodItemByID);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The food item with ID " + id + " was not found!");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFoodItem (@RequestBody FoodItemModel foodItemModel) {
        FoodItemModel newFoodItem = foodItemService.addFoodItem(foodItemModel);
        return ResponseEntity.ok("The food item " + newFoodItem.getName() + " was successfully added!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFoodItem (@PathVariable Long id) {
        if (foodItemService.getFoodItemByID(id) != null) {
            foodItemService.deleteFoodItem(id);
            return ResponseEntity.ok("Food item successfully deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food item with id " + id + " was not found!");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFoodItem (@PathVariable Long id, @RequestBody FoodItemModel foodItemModel) {
        if (foodItemService.getFoodItemByID(id) !=  null) {
            FoodItemModel updatedFoodItem = foodItemService.updateFoodItem(foodItemModel, id);
            return ResponseEntity.ok("The " + updatedFoodItem.getName() + "food item has been successfully updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Food item with id " + id + "was not found!");
        }
    }
}
