package tin.ngolamquang.sqlitedemo.Util;

import android.content.Context;

import tin.ngolamquang.sqlitedemo.DBManger.DBManager;

public class DBUtil {

    private static DBManager dbManager;

    public static DBManager getDBManager(Context context){
        if(dbManager == null){
            dbManager = new DBManager(context, NAME_DB,null, VERSION_DB);
        }

        return dbManager;
    }

    public static final int VERSION_DB = 1;

    public static final String NAME_DB = "foodmanager";

    public static final String CREATE_TABLE_FOOD_TYPE = "CREATE TABLE IF NOT EXISTS foodtype(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50) NOT NULL)";

    public static final String CREATE_TABLE_FOOD = "CREATE TABLE IF NOT EXISTS food(id INTEGER PRIMARY KEY AUTOINCREMENT, name_food VARCHAR(50) NOT NULL, quantity INTEGER, amount INTEGER, image VARCHAR(300), idfoodtype INTEGER)";
}
