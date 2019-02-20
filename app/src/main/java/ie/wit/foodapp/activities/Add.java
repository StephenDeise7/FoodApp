package ie.wit.foodapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import ie.wit.foodapp.Menus.MainActivity;
import ie.wit.foodapp.R;
import ie.wit.foodapp.models.Food;


public class Add extends Base {

    private String 		foodName, foodShop;
    private double 		foodPrice, ratingValue;
    private EditText name, shop, price;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        name = findViewById(R.id.addNameET);
        shop =  findViewById(R.id.addShopET);
        price =  findViewById(R.id.editPriceET);
        ratingBar =  findViewById(R.id.addRatingBar);
    }

    public void addFood(View v) {

        foodName = name.getText().toString();
        foodShop = shop.getText().toString();
        try {
            foodPrice = Double.parseDouble(price.getText().toString());
        } catch (NumberFormatException e) {
            foodPrice = 0.0;
        }
        ratingValue = ratingBar.getRating();

        if ((foodName.length() > 0) && (foodShop.length() > 0)
                && (price.length() > 0)) {
            Food c = new Food(foodName, foodShop, ratingValue,
                    foodPrice, false);

            app.foodList.add(c);
            startActivity(new Intent(this, MainActivity.class));
        } else
            Toast.makeText(
                    this,
                    "You must Enter Something for "
                            + "\'Name\', \'Shop\' and \'Price\'",
                    Toast.LENGTH_SHORT).show();
    }
}
