package ie.wit.foodapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;


import ie.wit.foodapp.R;
import ie.wit.foodapp.fragments.FoodFragment;

public class Home extends Base {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if(app.foodList.isEmpty()) setupFoods();
    }

    public void add(View v) {
        startActivity(new Intent(this, Add.class));
    }

    public void search(View v) {
        startActivity(new Intent(this, Search.class));
    }

    public void favourites(View v) { startActivity(new Intent(this, Favourites.class));
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

