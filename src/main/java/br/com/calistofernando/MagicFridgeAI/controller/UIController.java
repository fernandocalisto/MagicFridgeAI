package br.com.calistofernando.MagicFridgeAI.controller;

import br.com.calistofernando.MagicFridgeAI.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UIController {

    private final FoodItemService foodItemService;

    @GetMapping("/geladeira")
    public String abrirGeladeira(Model model) {
        model.addAttribute("ingredientes", foodItemService.getAllFoodItem());
        return "geladeira";
    }
}