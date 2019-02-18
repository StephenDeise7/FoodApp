package ie.wit.foodapp.main;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ie.wit.foodapp.models.Food;

public class FoodApp extends Application
{
    public List<Food> foodList = new ArrayList<>();

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("coffeemate", "CoffeeMate App Started");
    }
}