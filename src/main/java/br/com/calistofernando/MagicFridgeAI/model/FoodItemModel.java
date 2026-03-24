package br.com.calistofernando.MagicFridgeAI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "tb_food_item")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FoodItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;
    private Integer quantity;
    private LocalDate expirationDate;
}
