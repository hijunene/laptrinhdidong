package tin.ngolamquang.sqlitedemo.Model;

import java.io.Serializable;
import java.util.List;

public class Food implements Serializable {
    private String id;
    private String name;
    private int quantity;
    private String image;
    private int amount;

    private FoodType foodType;

    public Food(String id, String name, int quantity, String image, int amount) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.image = image;
        this.amount = amount;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public Food() {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
