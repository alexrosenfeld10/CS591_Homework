package com.example.megan.w7_p3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

// Step-by-Step Fragment Transactions

    private
    ListView lvEpisodes;     //Reference to the listview GUI component
    ListAdapter lvAdapter;   //Reference to the Adapter used to populate the listview.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvEpisodes = (ListView)findViewById(R.id.lvEpisodes);
        //instead of passing the boring default string adapter, let's pass our own, see class MyCustomAdapter below!
        lvAdapter = new MyCustomAdapter(this.getBaseContext());
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
        // get id of menu item clicked
        int id = item.getItemId();

        // if item one clicked, go to start trek merch website
        if (id == R.id.mnu_one) {
            // the website in the assignment doesn't exist, show a toast with a message
            Toast.makeText(getBaseContext(),
                    "http://shop.startrek.com/info.php does not exist, so I am sending you to shop.startrek.com",
                    Toast.LENGTH_LONG).show();
            // and instead show a different website
            String url = "http://shop.startrek.com";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }

        // if item two clicked, dial 1-800-STARTRK (but don't call)
        if (id == R.id.mnu_two) {
            String phoneNo = "tel:1800startrk";
            Intent phoneCall = new Intent(Intent.ACTION_DIAL);
            phoneCall.setData(Uri.parse(phoneNo));
            startActivity(phoneCall);
            return true;
        }

        // if item three clicked, spawn text message with body "Ouch!" (with no receiving number)
        if (id == R.id.mnu_three) {
            try {
                String message = "Ouch!";
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.putExtra("sms_body", message);
                sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "SMS faild, please try again later!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            return true;
        }

        // if item four clicked, play audio of "Live Long and Prosper"
        if (id == R.id.mnu_four) {
            return true;
        }

        // if item five clicked, play video of "KAHN" scene
        if (id == R.id.mnu_five) {
            return true;
        }

        //if none of the above are true, do the default and return a boolean.
        return super.onOptionsItemSelected(item);
    }
}

//STEP 1: Create references to needed resources for the ListView Object.  String Arrays, Images, etc.

class MyCustomAdapter extends BaseAdapter {

    private
    String episodes[];
    String  episodeDescriptions[];
    ArrayList<Integer> episodeImages;

    // create a variable for the context so we can use it later
    Context context;

    //STEP 2: Override the Constructor, be sure to:
    // grab the context, the callback gets it as a parm.
    // load the strings and images into object references.
    public MyCustomAdapter(Context aContext) {
        //initializing our data in the constructor.
        context = aContext;  //saving the context we'll need it again (for intents)

        //retrieving list of episodes predefined in strings-array "episodes" in strings.xml
        episodes =aContext.getResources().getStringArray(R.array.episodes);
        episodeDescriptions = aContext.getResources().getStringArray(R.array.episode_descriptions);

        // building ArrayList of apisode images
        episodeImages = new ArrayList<Integer>();
        episodeImages.add(R.drawable.st_spocks_brain);
        episodeImages.add(R.drawable.st_arena__kirk_gorn);
        episodeImages.add(R.drawable.st_this_side_of_paradise__spock_in_love);
        episodeImages.add(R.drawable.st_mirror_mirror__evil_spock_and_good_kirk);
        episodeImages.add(R.drawable.st_platos_stepchildren__kirk_spock);
        episodeImages.add(R.drawable.st_the_naked_time__sulu_sword);
        episodeImages.add(R.drawable.st_the_trouble_with_tribbles__kirk_tribbles);
    }

//STEP 3: Override and implement getCount(..), ListView uses this to determine how many rows to render.
    @Override
    public int getCount() {
        return episodes.length;   //all of the arrays are same length
    }

//STEP 4: Override getItem/getItemId, we aren't using these, but we must override anyway (base is abstract)
    @Override
    public Object getItem(int position) {
        return episodes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//THIS IS WHERE THE ACTION HAPPENS.  getView(..) is how each row gets rendered.
//STEP 5: Easy as A-B-C
    @Override
    //convertView is Row (it may be null), parent is the layout that has the row Views.
    public View getView(final int position, View convertView, ViewGroup parent) {

//STEP 5a: Inflate the listview row based on the xml.
        //this will refer to the row to be inflated or displayed if it's already been displayed. (listview_row.xml)
        View row;

        // Let's optimize a bit by checking to see if we need to inflate, or if it's already been inflated.
        if (convertView == null){  //indicates this is the first time we are creating this row.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.listview_row, parent, false);
        }
        else
        {
            row = convertView;
        }

//STEP 5b: Now that we have a valid row instance, we need to get references to the views

        //we prefixed findViewByID with row, because row is the container.
        ImageView imgEpisode = (ImageView) row.findViewById(R.id.imgEpisode);
        TextView tvEpisodeTitle = (TextView) row.findViewById(R.id.tvEpisodeTitle);
        TextView tvEpisodeDescription = (TextView) row.findViewById(R.id.tvEpisodeDescription);
        RatingBar ratingBar = (RatingBar) row.findViewById(R.id.rbEpisode);

        // fill with the appropriate text and images.
        tvEpisodeTitle.setText(episodes[position]);
        tvEpisodeDescription.setText(episodeDescriptions[position]);
        imgEpisode.setImageResource(episodeImages.get(position).intValue());
        ratingBar.setRating(retrieveSharedPreferenceInfo(position));

        // when image is clicked, open a website (Wikipedia of Start Trek episodes)
        imgEpisode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://en.wikipedia.org/wiki/List_of_Star_Trek:_The_Original_Series_episodes";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        // when episode title is clicked, open a website (Wikipedia of Start Trek episodes)
        tvEpisodeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://en.wikipedia.org/wiki/List_of_Star_Trek:_The_Original_Series_episodes";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        // make sure the ratings for each episode "stick"
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser == true) {
                    saveSharedPreferenceInfo(rating, position);
                }
            }
        });

//STEP 5c: That's it, the row has been inflated and filled with data, return it.
        return row;

    }

    // We want the rating for each episode to "stick," so we create SharedPreferences
    // this means the rating will remain even when we close the app, and they will be restored when we open it again
    void saveSharedPreferenceInfo(float rating, int position){
        //1. Refer to the SharedPreference Object.
        // (Private means no other Apps can access this.)
        SharedPreferences ratingsPreferences = context.getSharedPreferences("RatingsPreferences", Context.MODE_PRIVATE);

        //2. Create an Shared Preferences Editor for Editing Shared Preferences.
        SharedPreferences.Editor editor = ratingsPreferences.edit();

        // create variably to hold the string for the key for the key, value pair
        String key = "";

        // depending on which rating (value) was changed, choose the appropriate key
        switch (position) {
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

        //3. Store what's important!  (Key, Value Pair)
        editor.putFloat(key, rating);

        //4. Save your information.
        editor.apply();
    }

    // when we open the app, we want the ratings that we saved to be restored
    float retrieveSharedPreferenceInfo(int position){
        SharedPreferences ratingsPreferences = context.getSharedPreferences("RatingsPreferences", Context.MODE_PRIVATE);

        // create variable to hold key for key, value pair
        String key = "";

        // depending on which rating we are restoring, select that key
        switch (position) {
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

        // return the rating
        return ratingsPreferences.getFloat(key, 0);
    }
}