package tin.ngolamquang.sqlitedemo.Util;

import android.content.Context;

public class FakeData {

    public void addDataFoodType(Context context){
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO foodtype VALUES(null, \"Ăn sáng\")");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO foodtype VALUES(null, \"Ăn trưa\")");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO foodtype VALUES(null, \"Ăn tối\")");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO foodtype VALUES(null, \"Thức ăn nhanh\")");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO foodtype VALUES(null, \"Tráng miệng\")");
    }

    public void addDataFoood(Context context){
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO food VALUES(null, \"Cơm gà \",5, 60000, '', 1)");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO food VALUES(null, \"Cơm chiên hải sản \",5, 45000, '', 2)");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO food VALUES(null, \"Sushi \",5, 40000, '', 3)");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO food VALUES(null, \"Hamburger \",5, 50000, '', 4)");
        DBUtil.getDBManager(context).createOrEditData("INSERT INTO food VALUES(null, \"Tiramisu \",5, 30000, '', 5)");
    }
}
