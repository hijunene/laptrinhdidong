package dung.hohoang.doandidong.Util;

import android.content.Context;

public class FakeData {

    public void addDataFoodType(Context context){
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO foodtype VALUES(null, \"Ăn sáng\", \"https://quangtin131299.000webhostapp.com/kisspng-takikomi-gohan-cooked-rice-sushi-bowl-hand-painted-cartoon-rice-pattern-5aa452d002a6f9.9736055715207185440109.png\")");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO foodtype VALUES(null, \"Ăn trưa\", \"https://quangtin131299.000webhostapp.com/kisspng-bento-lunch-school-meal-clip-art-cute-cartoon-lunch-5aa861d7f33159.6463090115209845359961.png\")");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO foodtype VALUES(null, \"Ăn tối\", \"https://quangtin131299.000webhostapp.com/kisspng-sushi-japanese-cuisine-food-who-together-sushi-5a9afc907c1406.0217734015201066405082.png\")");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO foodtype VALUES(null, \"Thức ăn nhanh\", \"https://quangtin131299.000webhostapp.com/5a1d253a033727.1778691015118595140132.png\")");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO foodtype VALUES(null, \"Tráng miệng\", \"https://quangtin131299.000webhostapp.com/kisspng-doughnut-chocolate-pudding-banana-pudding-breakfas-kawaii-png-5a75ac342f0cb3.6391336115176612361927.png\")");
    }

    public void addDataFoood(Context context){
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO food VALUES(null, \"Cơm gà \",5, 60000, '', 1)");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO food VALUES(null, \"Cơm chiên hải sản \",5, 45000, '', 2)");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO food VALUES(null, \"Sushi \",5, 40000, '', 3)");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO food VALUES(null, \"Hamburger \",5, 50000, '', 4)");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO food VALUES(null, \"Tiramisu \",5, 30000, '', 5)");
    }
}
