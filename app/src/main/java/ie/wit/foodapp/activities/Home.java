package ie.wit.foodapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import ie.wit.foodapp.models.Food;
import ie.wit.foodapp.R;

public class Home extends Base {

    int[] images = {R.drawable.add_72, R.drawable.search_72,R.drawable.favourites_72};

    String[] version = { "Check in", "Search","Favourites"};

    String[] versionNumber = {"", "","",""};

    ListView lView;

    ListAdapter lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if(app.foodList.isEmpty()) setupFoods();

        lView = (ListView) findViewById(R.id.androidList);

        lAdapter = new ListAdapter(Home.this, version, versionNumber, images);

        lView.setAdapter(lAdapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(Home.this, version[i]+" "+versionNumber[i], Toast.LENGTH_SHORT).show();

                if (i==0)
                {
                    Intent intent = new Intent (view.getContext(), Add.class);
                    startActivity(intent);
                }
                else if (i==1)
                {
                    Intent intent = new Intent (view.getContext(), Search.class);
                    startActivity(intent);
                }
                else if (i==2)
                {
                    Intent intent = new Intent (view.getContext(), Favourites.class);
                    startActivity(intent);
                }

            }
        });
    }


    public void setupFoods(){
        app.foodList.add(new Food("Sausage Special", " Brennans",2.5,6.00,false));
    }
}

