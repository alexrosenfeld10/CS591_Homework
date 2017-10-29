package com.example.sse.fragmenttransactions_sse;

//import android.app.Activity;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;



// 1b) Broken things: Frag2 and Frag3 Button weren't displaying anything.
//      Hitting back button would go out of the app.
//      Re-entering app would reset everything
//      Fixes: Added all three fragments on the onCreate lifecycle.
//      Upon clicking the fragment, the current fragment would be replaced
//      and there would a transaction to add the new fragment to the backstack.
//      If nothing is in the fragment to begin with, we instantiate a new fragment
//      from the respective fragment class. We used replace instead of the other possibilities
//      because of the idea that hiding too many fragments could be costly
//      if one key feature is to have the back button functional
//      (many fragments = many backs to go to the start), so removing it completely
//      could be more effective since these fragments are not costly to add.

public class MainActivity extends AppCompatActivity{


    //Two basic ways of working with fragments.
    //
    //1. Just include them in the Activity's layout.
    //
    //2. Instantiate and work with them in code.
    // in code you have much more control.

    //3. create objects to reference the views, including fragments.
private
    Frag_One  f1;
    Frag_Two  f2;
    Frag_Three  f3;

    android.support.v4.app.FragmentManager fm;  // we will need this later.



    private Button btnFrag1;
    private Button btnFrag2;
    private Button btnFrag3;
    private LinearLayout FragLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check that the activity is using the fragment
        if (findViewById(R.id.FragLayout) != null){
            // If restored from a previous state,
            // just return or else fragments can overlap
            if (savedInstanceState != null) {
                return;
            }
        }
        //4. get references for our views.
        btnFrag1 = (Button) findViewById(R.id.btnFrag1);
        btnFrag2 = (Button) findViewById(R.id.btnFrag2);
        btnFrag3 = (Button) findViewById(R.id.btnFrag3);
        FragLayout = (LinearLayout) findViewById(R.id.FragLayout);

    //5a.  We actually have to create the fragments ourselves.  Where is our friend, R?
        f1 = new Frag_One();
        f2 = new Frag_Two();
        f3 = new Frag_Three();

    //5b. Grab a reference to the Activity's Fragment Manager, Every Activity has one!
        // Used getSupportFragmentManager() over getFragmentManager() because we need to
        // support the Android Frameworks below 5.0
        fm = getSupportFragmentManager();


    //5c. Now we can "plop" fragment(s) into our container.
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction ();  //Create a reference to a fragment transaction.

        ft.add(R.id.FragLayout, f1, "tag1");
        ft.add(R.id.FragLayout, f2, "tag2");
        ft.add(R.id.FragLayout, f3, "tag3");
        ft.commit ();  //don't forget to commit your changes.  It is a transaction after all.

    btnFrag1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showFrag1();
        }
    });

        btnFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFrag2();
            }
        });

        btnFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFrag3();
            }
        });

    }

public void showFrag1() {

    // Checks if frag even exists, otherwise instantiates new frag
    if (f1 == null)
        f1 = new Frag_One();
    f1 = (Frag_One) fm.findFragmentByTag("tag1");

    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction ();  //Create a reference to a fragment transaction.
    // Adds onto the backstack so that the back button knows what fragment to back on
    ft.addToBackStack ("myFrag1");
    //
    ft.replace(R.id.FragLayout, f1);
    ft.commit();
}

    public void showFrag2() {
        // Checks if frag even exists, otherwise instantiates new frag
        if (f2 == null)
          f2 = new Frag_Two();
        f2 = (Frag_Two) fm.findFragmentByTag("tag2");

        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction ();  //Create a reference to a fragment transaction.
        // Adds onto the backstack so that the back button knows what fragment to back on
        ft.addToBackStack ("myFrag2");
        ft.replace(R.id.FragLayout, f2);
        ft.commit();
    }


    public void showFrag3() {
        // Checks if frag even exists, otherwise instantiates new frag
        if (f3 == null)
            f3 = new Frag_Three();
        f3 = (Frag_Three) fm.findFragmentByTag("tag3");

        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction ();  //Create a reference to a fragment transaction.
        // Adds onto the backstack so that the back button knows what fragment to back on
        ft.addToBackStack ("myFrag3");
        ft.replace(R.id.FragLayout, f3);
        ft.commit();
    }
}
