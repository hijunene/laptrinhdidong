package dung.hohoang.doandidong.UI.Item;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import dung.hohoang.doandidong.Model.FoodType;
import dung.hohoang.doandidong.R;
import dung.hohoang.doandidong.UI.Item.ViewHolder.FoodtypeViewHolder;
import dung.hohoang.doandidong.Util.Util;

public class FoodTypeItems extends BaseAdapter {

    List<FoodType> foodTypes;
    Context context;

    public FoodTypeItems(List<FoodType> foodTypes, Context context) {
        this.foodTypes = foodTypes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return foodTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return foodTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodtypeViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food_type,null);

            viewHolder = new FoodtypeViewHolder();

            viewHolder.setImgFoodType(convertView.findViewById(R.id.imgAvatarFoodType));
            viewHolder.setTxtTitle(convertView.findViewById(R.id.txtTitleFoodType));

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (FoodtypeViewHolder) convertView.getTag();
        }

        FoodType foodType = (FoodType) getItem(position);

        if(foodType.getImage().isEmpty()){
            viewHolder.getImgFoodType().setImageResource(R.drawable.no_image);
        }else{
            if(Util.validateURL(foodType.getImage())){
                Picasso.with(context)
                        .load(foodType.getImage()).into(viewHolder.getImgFoodType());
            }else{
                File fileImageFoodType = new File(foodType.getImage());

                Bitmap bmImageFood = BitmapFactory.decodeFile(fileImageFoodType.getAbsolutePath());

                viewHolder.getImgFoodType().setImageBitmap(bmImageFood);
            }
        }

        viewHolder.getTxtTitle().setText(foodType.getName());

        return convertView;
    }
}
