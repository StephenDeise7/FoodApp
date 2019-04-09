package ie.wit.foodapp.Menus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;

import android.widget.Toast;

import ie.wit.foodapp.R;
import ie.wit.foodapp.activities.Add;
import ie.wit.foodapp.activities.Base;
import ie.wit.foodapp.activities.Foods;
import ie.wit.foodapp.activities.MainImageActivity;
import ie.wit.foodapp.main.MapsActivity;

public class MainActivity extends Base {

    int[] images = {R.drawable.food, R.drawable.add_72,R.drawable.maps,R.drawable.camera_lg};

    String[] version = { "Foods", "Add","Maps","Add Image"};

    String[] versionNumber = {"","","",""};

    ListView lView;

    ListAdapter lAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);




        lView = (ListView) findViewById(R.id.androidList);

        lAdapter = new ListAdapter(MainActivity.this, version, versionNumber, images);

        lView.setAdapter(lAdapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this, version[i]+" "+versionNumber[i], Toast.LENGTH_SHORT).show();

                if (i==0)
                {

                    Intent intent = new Intent (view.getContext(), Foods.class);
                    startActivity(intent);
                }
                else if (i==1)
                {
                    Intent intent = new Intent (view.getContext(), Add.class);
                    startActivity(intent);
                }

                else if (i==2)
                {
                    Intent intent = new Intent (view.getContext(), MapsActivity.class);
                    startActivity(intent);
                }
                else if (i==3)
                {
                    Intent intent = new Intent (view.getContext(), MainImageActivity.class);
                    startActivity(intent);
                }

            }
        });

    }


}
