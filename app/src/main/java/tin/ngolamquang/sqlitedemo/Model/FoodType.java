package tin.ngolamquang.sqlitedemo.Model;

import java.util.List;

public class FoodType {
    private String id;
    private String name;
    private List<Food> foods;


    public FoodType(String idClass, String name) {
        this.id = idClass;
        this.name = name;

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

    public List<Food> getStudents() {
        return foods;
    }

    public void setStudents(List<Food> foods) {
        this.foods = foods;
    }
}
