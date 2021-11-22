package dung.hohoang.doandidong.Model;

import java.io.Serializable;
import java.util.List;

public class FoodType implements Serializable {
    private String id;
    private String name;
    private String image;

    private List<Food> foods;


    public FoodType(String idClass, String name, String image) {
        this.id = idClass;
        this.name = name;
        this.image = image;
    }

    public FoodType() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
