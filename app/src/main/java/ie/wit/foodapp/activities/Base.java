package ie.wit.foodapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;


import com.google.firebase.auth.FirebaseAuth;

import ie.wit.foodapp.Menus.HomeMenu;
import ie.wit.foodapp.Menus.MainActivity;
import ie.wit.foodapp.Menus.ProfileActivity;
import ie.wit.foodapp.R;
import ie.wit.foodapp.main.FoodApp;
import ie.wit.foodapp.main.MapsActivity;


public class Base extends AppCompatActivity {

    public FoodApp app;
    public Bundle activityInfo; // Used for persistence (of sorts)
    private FirebaseAuth firebaseAuth;
    private DrawerLayout drawer;


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

    public void menuCamera(MenuItem m) { startActivity(new Intent( this, Camera.class));}

    public void menuProfile(MenuItem m) {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void menuLogout(MenuItem m) {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, HomeMenu.class));
    }





}
