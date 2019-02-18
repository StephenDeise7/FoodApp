package ie.wit.foodapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import ie.wit.foodapp.models.Food;
import ie.wit.foodapp.R;

public class Add extends Base {

    private String foodName, restuarant;
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

    public void addCoffee(View v) {

        foodName = name.getText().toString();
        restuarant = shop.getText().toString();
        try {
            foodPrice = Double.parseDouble(price.getText().toString());
        } catch (NumberFormatException e) {
            foodPrice = 0.0;
        }
        ratingValue = ratingBar.getRating();

        if ((foodName.length() > 0) && (restuarant.length() > 0)
                && (price.length() > 0)) {
            Food c = new Food(foodName, restuarant, ratingValue,
                    foodPrice, false);

            app.foodList.add(c);
            startActivity(new Intent(this, Home.class));
        } else
            Toast.makeText(
                    this,
                    "You must Enter Something for "
                            + "\'Name\', \'Shop\' and \'Price\'",
                    Toast.LENGTH_SHORT).show();
    }
}
