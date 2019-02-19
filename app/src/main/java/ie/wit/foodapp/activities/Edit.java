package ie.wit.foodapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import ie.wit.foodapp.R;
import ie.wit.foodapp.models.Food;


public class Edit extends Base {
    public Context context;
    public boolean isFavourite;
    public Food aFood;
    public ImageView editFavourite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        context = this;
        activityInfo = getIntent().getExtras();
        aFood = getCoffeeObject(activityInfo.getString("FoodId"));

        ((TextView)findViewById(R.id.editTitleTV)).setText(aFood.foodName);

        ((EditText)findViewById(R.id.editNameET)).setText(aFood.foodName);
        ((EditText)findViewById(R.id.editShopET)).setText(aFood.shop);
        ((EditText)findViewById(R.id.editPriceET)).setText(""+aFood.price);
        ((RatingBar) findViewById(R.id.editRatingBar)).setRating((float)aFood.rating);

        editFavourite = findViewById(R.id.editFavourite);

        if (aFood.favourite == true) {
            editFavourite.setImageResource(R.drawable.favourites_72_on);
            isFavourite = true;
        } else {
            editFavourite.setImageResource(R.drawable.favourites_72);
            isFavourite = false;
        }
    }

    private Food getCoffeeObject(String id) {

        for (Food c : app.foodList)
            if (c.FoodId.equalsIgnoreCase(id))
                return c;

        return null;
    }

//    private int getCoffeeIndex(Coffee obj) {
//
//        for (Coffee c : coffeeList)
//            if (c.coffeeId == obj.coffeeId)
//                return coffeeList.indexOf(c);
//
//        return -1;
//    }

    public void saveFood(View v) {

        String foodName = ((EditText) findViewById(R.id.editNameET)).getText().toString();
        String foodShop = ((EditText) findViewById(R.id.editShopET)).getText().toString();
        String foodPriceStr = ((EditText) findViewById(R.id.editPriceET)).getText().toString();
        double ratingValue =((RatingBar) findViewById(R.id.editRatingBar)).getRating();
        double foodPrice;

        try {
            foodPrice = Double.parseDouble(foodPriceStr);
        } catch (NumberFormatException e) {
            foodPrice = 0.0;
        }

        if ((foodName.length() > 0) && (foodShop.length() > 0) && (foodPriceStr.length() > 0)) {
            aFood.foodName = foodName;
            aFood.shop = foodShop;
            aFood.price = foodPrice;
            aFood.rating = ratingValue;

            startActivity(new Intent(this,Home.class));

        } else
            Toast.makeText(this, "You must Enter Something for Name and Shop",Toast.LENGTH_SHORT).show();
    }

    public void toggle(View view) {

        if (isFavourite) {
            aFood.favourite = false;
            Toast.makeText(this,"Removed From Favourites",Toast.LENGTH_SHORT).show();
            isFavourite = false;
            editFavourite.setImageResource(R.drawable.favourites_72);
        } else {
            aFood.favourite = true;
            Toast.makeText(this,"Added to Favourites !!",Toast.LENGTH_SHORT).show();
            isFavourite = true;
            editFavourite.setImageResource(R.drawable.favourites_72_on);
        }
    }
}
