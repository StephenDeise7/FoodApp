package ie.wit.foodapp.fragments;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import ie.wit.foodapp.R;
import ie.wit.foodapp.activities.Base;
import ie.wit.foodapp.activities.Edit;
import ie.wit.foodapp.activities.Favourites;
import ie.wit.foodapp.adapters.FoodFilter;
import ie.wit.foodapp.adapters.FoodListAdapter;
import ie.wit.foodapp.models.Food;


public class FoodFragment extends ListFragment implements View.OnClickListener,
        AbsListView.MultiChoiceModeListener
{
    public Base activity;
    public static FoodListAdapter listAdapter;
    public ListView listView;
    public FoodFilter foodFilter;

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Bundle activityInfo = new Bundle(); // Creates a new Bundle object
        activityInfo.putString("FoodId", (String) v.getTag());
        Intent goEdit = new Intent(getActivity(), Edit.class); // Creates a new Intent
        /* Add the bundle to the intent here */
        goEdit.putExtras(activityInfo);
        getActivity().startActivity(goEdit); // Launch the Intent
    }

    public static FoodFragment newInstance() {
        FoodFragment fragment = new FoodFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.activity = (Base) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        listAdapter = new FoodListAdapter(activity, this, activity.app.foodList);
        foodFilter = new FoodFilter(activity.app.foodList,"all",listAdapter);

        if (getActivity() instanceof Favourites) {
            foodFilter.setFilter("favourites"); // Set the filter text field from 'all' to 'favourites'
            foodFilter.filter(null); // Filter the data, but don't use any prefix
            listAdapter.notifyDataSetChanged(); // Update the adapter
        }
        setListAdapter (listAdapter);
        setRandomFood();
        checkEmptyList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        listView = v.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);

        return v;
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onClick(View view)
    {
        if (view.getTag() instanceof Food)
        {
            onFoodDelete ((Food) view.getTag());
        }
    }

    public void onFoodDelete(final Food food)
    {
        String stringName = food.foodName;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure you want to Delete the \'Food\' " + stringName + "?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                activity.app.foodList.remove(food); // remove from our list
                listAdapter.foodList.remove(food); // update adapters data
                setRandomFood();
                listAdapter.notifyDataSetChanged(); // refresh adapter
                checkEmptyList();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /* ************ MultiChoiceModeListener methods (begin) *********** */
    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu)
    {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.delete_list_context, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.menu_item_delete_food:
                deleteFoods(actionMode);
                return true;
            default:
                return false;
        }
    }

    public void deleteFoods(ActionMode actionMode)
    {
        for (int i = listAdapter.getCount() -1 ; i >= 0; i--)
        {
            if (listView.isItemChecked(i))
            {
                activity.app.foodList.remove(listAdapter.getItem(i));
                if (activity instanceof Favourites)
                   listAdapter.foodList.remove(listAdapter.getItem(i));
            }
        }
        setRandomFood();
        listAdapter.notifyDataSetChanged(); // refresh adapter
        checkEmptyList();

        actionMode.finish();
    }

    public void setRandomFood() {

        ArrayList<Food> foodList = new ArrayList<>();

        for(Food c : activity.app.foodList)
            if (c.favourite)
                foodList.add(c);

        if (activity instanceof Favourites)
            if( !foodList.isEmpty()) {
                Food randomFood = foodList.get(new Random()
                            .nextInt(foodList.size()));

                ((TextView) getActivity().findViewById(R.id.favouriteFoodName)).setText(randomFood.foodName);
                ((TextView) getActivity().findViewById(R.id.favouriteRestuarant)).setText(randomFood.shop);
                ((TextView) getActivity().findViewById(R.id.favouriteFoodPrice)).setText("€ " + randomFood.price);
                ((TextView) getActivity().findViewById(R.id.favouriteFoodRating)).setText(randomFood.rating + " *");
            }
            else {
                ((TextView) getActivity().findViewById(R.id.favouriteFoodName)).setText("N/A");
                ((TextView) getActivity().findViewById(R.id.favouriteRestuarant)).setText("N/A");
                ((TextView) getActivity().findViewById(R.id.favouriteFoodPrice)).setText("N/A");
                ((TextView) getActivity().findViewById(R.id.favouriteFoodRating)).setText("N/A");
            }
    }

    public void checkEmptyList()
    {
        TextView recentList = getActivity().findViewById(R.id.emptyList);

        if(activity.app.foodList.isEmpty())
            recentList.setText(getString(R.string.emptyMessageLbl));
        else
            recentList.setText("");
    }
    @Override
    public void onDestroyActionMode(ActionMode actionMode)
    {}

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked)
    {}
    /* ************ MultiChoiceModeListener methods (end) *********** */
}
