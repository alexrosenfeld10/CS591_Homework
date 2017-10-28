package com.example.megan.w7_p3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

//Step-By-Step, Fragment Transactions

    private
    ListView lvEpisodes;     //Reference to the listview GUI component
    ListAdapter lvAdapter;   //Reference to the Adapter used to populate the listview.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvEpisodes = (ListView)findViewById(R.id.lvEpisodes);
        lvAdapter = new MyCustomAdapter(this.getBaseContext());  //instead of passing the boring default string adapter, let's pass our own, see class MyCustomAdapter below!
        lvEpisodes.setAdapter(lvAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar
        getMenuInflater().inflate(R.menu.my_test_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.mnu_zero) {
            Toast.makeText(getBaseContext(), "Menu Zero.", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.mnu_one) {
            Toast.makeText(getBaseContext(), "Ring ring, Hi Mom.", Toast.LENGTH_LONG).show();
            return true;
        }


        return super.onOptionsItemSelected(item);  //if none of the above are true, do the default and return a boolean.
    }
}

//STEP 1: Create references to needed resources for the ListView Object.  String Arrays, Images, etc.

class MyCustomAdapter extends BaseAdapter {

    private
    String episodes[];
    String  episodeDescriptions[];
    ArrayList<Integer> episodeImages;
    float ratings[];

    Context context;

    //STEP 2: Override the Constructor, be sure to:
    // grab the context, the callback gets it as a parm.
    // load the strings and images into object references.
    public MyCustomAdapter(Context aContext) {
        //initializing our data in the constructor.
        context = aContext;  //saving the context we'll need it again.

        episodes =aContext.getResources().getStringArray(R.array.episodes);  //retrieving list of episodes predefined in strings-array "episodes" in strings.xml
        episodeDescriptions = aContext.getResources().getStringArray(R.array.episode_descriptions);

        episodeImages = new ArrayList<Integer>();   //Could also use helper function "getDrawables(..)" below to auto-extract drawable resources, but keeping things as simple as possible.
        episodeImages.add(R.drawable.st_spocks_brain);
        episodeImages.add(R.drawable.st_arena__kirk_gorn);
        episodeImages.add(R.drawable.st_this_side_of_paradise__spock_in_love);
        episodeImages.add(R.drawable.st_mirror_mirror__evil_spock_and_good_kirk);
        episodeImages.add(R.drawable.st_platos_stepchildren__kirk_spock);
        episodeImages.add(R.drawable.st_the_naked_time__sulu_sword);
        episodeImages.add(R.drawable.st_the_trouble_with_tribbles__kirk_tribbles);

        ratings = retrieveSharedPreferenceInfo();
    }

    //STEP 3: Override and implement getCount(..), ListView uses this to determine how many rows to render.
    @Override
    public int getCount() {
//        return episodes.size(); //all of the arrays are same length, so return length of any... ick!  But ok for now. :)
        return episodes.length;   //all of the arrays are same length, so return length of any... ick!  But ok for now. :)
        //Q: How else could we have done this (better)? ________________
    }

    //STEP 4: Override getItem/getItemId, we aren't using these, but we must override anyway.
    @Override
    public Object getItem(int position) {
//        return episodes.get(position);  //In Case you want to use an ArrayList
        return episodes[position];        //really should be returning entire set of row data, but it's up to us, and we aren't using this call.
    }

    @Override
    public long getItemId(int position) {
        return position;  //Another call we aren't using, but have to do something since we had to implement (base is abstract).
    }

    //THIS IS WHERE THE ACTION HAPPENS.  getView(..) is how each row gets rendered.
//STEP 5: Easy as A-B-C
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {  //convertView is Row (it may be null), parent is the layout that has the row Views.

//STEP 5a: Inflate the listview row based on the xml.
        View row;  //this will refer to the row to be inflated or displayed if it's already been displayed. (listview_row.xml)
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        row = inflater.inflate(R.layout.listview_row, parent, false);  //

// Let's optimize a bit by checking to see if we need to inflate, or if it's already been inflated...
        if (convertView == null){  //indicates this is the first time we are creating this row.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.listview_row, parent, false);
        }
        else
        {
            row = convertView;
        }

//STEP 5b: Now that we have a valid row instance, we need to get references to the views within that row and fill with the appropriate text and images.
        ImageView imgEpisode = (ImageView) row.findViewById(R.id.imgEpisode);  //Q: Notice we prefixed findViewByID with row, why?  A: Row, is the container.
        TextView tvEpisodeTitle = (TextView) row.findViewById(R.id.tvEpisodeTitle);
        TextView tvEpisodeDescription = (TextView) row.findViewById(R.id.tvEpisodeDescription);
        RatingBar ratingBar = (RatingBar) row.findViewById(R.id.rbEpisode);

        tvEpisodeTitle.setText(episodes[position]);
        tvEpisodeDescription.setText(episodeDescriptions[position]);
        imgEpisode.setImageResource(episodeImages.get(position).intValue());
        ratingBar.setRating(ratings[position]);

        imgEpisode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://en.wikipedia.org/wiki/List_of_Star_Trek:_The_Original_Series_episodes";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        tvEpisodeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://en.wikipedia.org/wiki/List_of_Star_Trek:_The_Original_Series_episodes";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser = true) {
                    saveSharedPreferenceInfo(rating, position);
                }
            }
        });

//STEP 5c: That's it, the row has been inflated and filled with data, return it.
        return row;  //once the row is fully constructed, return it.

    }

    void saveSharedPreferenceInfo(float rating, int id){
        //1. Refer to the SharedPreference Object.
        SharedPreferences ratingsPreferences = context.getSharedPreferences("RatingsPreferences", Context.MODE_PRIVATE);  //Private means no other Apps can access this.

        //2. Create an Shared Preferences Editor for Editing Shared Preferences.
        //Note, not a real editor, just an object that allows editing...

        SharedPreferences.Editor editor = ratingsPreferences.edit();

        String key = "";

        switch (id) {
            case 0:
                key = "rbRatings0";
                break;
            case 1:
                key = "rbRatings1";
                break;
            case 2:
                key = "rbRatings2";
                break;
            case 3:
                key = "rbRatings3";
                break;
            case 4:
                key = "rbRatings4";
                break;
            case 5:
                key = "rbRatings5";
                break;
            case 6:
                key = "rbRatings6";
                break;
        }

        //3. Store what's important!  Key Value Pair, what else is new...
        editor.putFloat(key, rating);
        //4. Save your information.
        editor.apply();


        // for debugging: which rating was just updated
        Toast.makeText(context, key, Toast.LENGTH_SHORT).show();
    }


    float[] retrieveSharedPreferenceInfo(){
        SharedPreferences ratingsPreferences = context.getSharedPreferences("RatingsPreferences", Context.MODE_PRIVATE);

//        String key = "";
//
//        //Retrieving data from shared preferences hashmap.
//
//        switch (position) {
//            case 1:
//                key = "rbRatings1";
//                break;
//            case 2:
//                key = "rbRatings2";
//                break;
//            case 3:
//                key = "rbRatings3";
//                break;
//            case 4:
//                key = "rbRatings4";
//                break;
//            case 5:
//                key = "rbRatings5";
//                break;
//            case 6:
//                key = "rbRatings6";
//                break;
//            case 7:
//                key = "rbRatings7";
//                break;
//        }

        float r0 = ratingsPreferences.getFloat("rbRatings0", 0);
        float r1 = ratingsPreferences.getFloat("rbRatings1", 0);
        float r2 = ratingsPreferences.getFloat("rbRatings2", 0);
        float r3 = ratingsPreferences.getFloat("rbRatings3", 0);
        float r4 = ratingsPreferences.getFloat("rbRatings4", 0);
        float r5 = ratingsPreferences.getFloat("rbRatings5", 0);
        float r6 = ratingsPreferences.getFloat("rbRatings6", 0);

//        float r0 = 1;
//        float r1 = 2;
//        float r2 = 3;
//        float r3 = 4;
//        float r4 = 5;
//        float r5 = 4;
//        float r6 = 3;

        float[] ratings = new float[] {r0,r1,r2,r3,r4,r5,r6};

        return ratings;
    }
}