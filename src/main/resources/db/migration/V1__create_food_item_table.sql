CREATE TABLE tb_food_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    food_category VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    expiration_date DATE
);