package dung.hohoang.doandidong.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dung.hohoang.doandidong.Model.Food;
import dung.hohoang.doandidong.R;
import dung.hohoang.doandidong.UI.ToolBar.ToolBarCustom;

public class Util {
    public static final int REQUEST_LOAD_IMAGE = 1;
    public static final int NO_ACTION = -1;
    public static final int ACTION_CODE_ADD = 0;
    public static final int ACTION_CODE_EDIT = 1;
    public static final int REQUEST_PERMISSION_CODE = 2;



    private static final String URL_REGEX = "((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._\\+~#?&//=]"
            + "{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%"
            + "._\\+~#?&//=]*)";

    public static boolean validateURL(String dataString) {
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(dataString);

        return matcher.find();
    }

    public static String getRealPathFormURI(Context context, Uri contentUri) {
        String wholeID = DocumentsContract.getDocumentId(contentUri);
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().
                query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        column, sel, new String[]{ id }, null);

        String filePath = "";

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }

        cursor.close();

        return filePath;
    }

    public static String formatCurrey(int input){
        NumberFormat formatter = new DecimalFormat("#,###");

        String formattedNumber = formatter.format(input);

        return formattedNumber;
    }

    public static void updateTitleToolBar(ToolBarCustom toolBarCustom, String title) {
        toolBarCustom.updateTitle(title);
    }

    public static int getIdFoodType(Intent intentInput){
        return intentInput.getIntExtra("ID_FOODTYPE", -1);
    }

    public static int getActionCode(Intent intentInput){
        return intentInput.getIntExtra("ACTION_CODE", -1);
    }

    public static Food getFood(Intent intentInput){
        return (Food) intentInput.getSerializableExtra("FOOD");
    }
}
