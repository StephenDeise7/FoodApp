package ie.wit.foodapp.activities;

import android.os.Bundle;

import ie.wit.foodapp.R;
import ie.wit.foodapp.fragments.SearchFragment;


public class Search extends Base  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
    }

    @Override
    protected void onResume() {
        super.onResume();

        foodFragment = SearchFragment.newInstance(); //get a new Fragment instance
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, foodFragment)
                .commit(); // add it to the current activity
    }
}
