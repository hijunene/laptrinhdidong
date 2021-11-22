package dung.hohoang.doandidong.Model;

import java.io.Serializable;

public class Food implements Serializable {
    private String id;
    private String name;
    private int quantity;
    private String image;
    private int price;

    private FoodType foodType;

    public Food(String id, String name, int quantity, String image, int amount) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.image = image;
        this.price = amount;
        foodType = new FoodType();
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public Food() {
        foodType = new FoodType();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int amount) {
        this.price = amount;
    }
}
