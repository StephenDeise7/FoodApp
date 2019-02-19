package ie.wit.foodapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ie.wit.foodapp.R;
import ie.wit.foodapp.fragments.FoodFragment;

public class Foods extends Base{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foods);

        if(app.foodList.isEmpty()) setupFoods();
    }


    @Override
    protected void onResume() {
        super.onResume();

        foodFragment = FoodFragment.newInstance(); //get a new Fragment instance
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, foodFragment)
                .commit(); // add it to the current activity
    }

    public void setupFoods(){

    }
}

