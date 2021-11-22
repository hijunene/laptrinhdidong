package dung.hohoang.doandidong.UI.Item.ViewHolder;

import android.widget.ImageView;
import android.widget.TextView;

public class FoodViewHolder {
    private ImageView imgFood;
    private TextView txtPrice, txtQuality, txtTitle;

    public ImageView getImgFood() {
        return imgFood;
    }

    public void setImgFood(ImageView imgFood) {
        this.imgFood = imgFood;
    }

    public TextView getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(TextView txtPrice) {
        this.txtPrice = txtPrice;
    }

    public TextView getTxtQuality() {
        return txtQuality;
    }

    public void setTxtQuality(TextView txtQuality) {
        this.txtQuality = txtQuality;
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(TextView txtTitle) {
        this.txtTitle = txtTitle;
    }
}
