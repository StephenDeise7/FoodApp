package ie.wit.foodapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


import com.google.firebase.auth.FirebaseAuth;

import ie.wit.foodapp.Menus.HomeMenu;
import ie.wit.foodapp.Menus.MainActivity;
import ie.wit.foodapp.R;
import ie.wit.foodapp.fragments.FoodFragment;
import ie.wit.foodapp.main.FoodApp;

public class Base extends AppCompatActivity {

    public FoodApp app;
    public Bundle activityInfo; // Used for persistence (of sorts)
    public FoodFragment foodFragment; // How we'll 'share' our List of Coffees between Activities
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (FoodApp) getApplication();
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void menuHome(MenuItem m) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void menuProfile(MenuItem m) {
        startActivity(new Intent(this, HomeMenu.class));
    }

    public void menuLogout(MenuItem m) {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, HomeMenu.class));
    }

    public void menuInfo(MenuItem m) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.appAbout))
                .setMessage(getString(R.string.appDesc)
                        + "\n\n"
                        + getString(R.string.appMoreInfo))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // we could put some code here too
                    }
                })
                .show();
    }

    public void menuHelp(MenuItem m) {
        startActivity(new Intent(this, Help.class));
    }
}
