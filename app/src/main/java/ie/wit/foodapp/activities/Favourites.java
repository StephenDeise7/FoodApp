package ie.wit.foodapp.activities;

import android.os.Bundle;
import android.widget.TextView;


import ie.wit.foodapp.fragments.FoodFragment;
import ie.wit.foodapp.R;

public class Favourites extends Base {

    private TextView emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites);

        emptyList = findViewById(R.id.emptyList);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(app.foodList.isEmpty())
            emptyList.setText(getString(R.string.emptyMessageLbl));
        else
            emptyList.setText("");

        foodFragment = FoodFragment.newInstance(); //get a new Fragment instance
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, foodFragment)
                .commit(); // add it to the current activity
    }
}
