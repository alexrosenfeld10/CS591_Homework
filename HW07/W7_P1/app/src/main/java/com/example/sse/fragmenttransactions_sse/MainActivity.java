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

import java.util.ArrayList;

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


        // Check that the activity is using the layout version with
        if (findViewById(R.id.FragLayout) != null){
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
        }


        //4. get references for our views.
        btnFrag1 = (Button) findViewById(R.id.btnFrag1);
        btnFrag2 = (Button) findViewById(R.id.btnFrag2);
        btnFrag3 = (Button) findViewById(R.id.btnFrag3);
        FragLayout = (LinearLayout) findViewById(R.id.FragLayout);

//        f1 = (Frag_One) findViewById(R.id.frag1);  //Hey, why won't this work for fragments?  Wait does the fragment even exist?

    //5a.  We actually have to create the fragments ourselves.  Where is our friend, R?
        f1 = new Frag_One();
        f2 = new Frag_Two();
        f3 = new Frag_Three();

    //5b. Grab a reference to the Activity's Fragment Manager, Every Activity has one!
     //  fm = getFragmentManager ();  //that was easy.
        fm = getSupportFragmentManager();  // **//use this call instead, if your activity extends AppCompatActivity
        fm.addOnBackStackChangedListener(new android.support.v4.app.FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                getFragmentManager().popBackStack();
            }
        });

    //5c. Now we can "plop" fragment(s) into our container.
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction ();  //Create a reference to a fragment transaction.

        ft.add(R.id.FragLayout, f1, "tag1");  //now we have added our fragment to our Activity programmatically.  The other fragments exist, but have not been added yet.
        ft.add(R.id.FragLayout, f2, "tag2");  //now we have added our fragment to our Activity programmatically.  The other fragments exist, but have not been added yet.
        ft.add(R.id.FragLayout, f3, "tag3");  //now we have added our fragment to our Activity programmatically.  The other fragments exist, but have not been added yet.
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

    if (f1 == null)
        f1 = new Frag_One();

    f1 = (Frag_One) fm.findFragmentByTag("tag1");   //what should we do if f1 doesn't exist anymore?  How do we check and how do we fix?
    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction ();  //Create a reference to a fragment transaction.
    //ft.add(R.id.FragLayout, f1, "tag1");
    ft.addToBackStack ("myFrag1");
    ft.replace(R.id.FragLayout, f1);
//    ft.hide(f2);
//    ft.hide(f3);
//    ft.show(f1);   //why does this not *always* crash?
    ft.commit();
}

    public void showFrag2() {

        if (f2 == null)
          f2 = new Frag_Two();
        f2 = (Frag_Two) fm.findFragmentByTag("tag2");   //what should we do if f1 doesn't exist anymore?  How do we check and how do we fix?

        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction ();  //Create a reference to a fragment transaction.
        ft.addToBackStack ("myFrag2");  //why do we do this?
        ft.replace(R.id.FragLayout, f2);
//
//        ft.hide(f1);
//        ft.hide(f3);
//        ft.show(f2);
        ft.commit();
    }


    public void showFrag3() {
        if (f3 == null)
            f3 = new Frag_Three();
        f3 = (Frag_Three) fm.findFragmentByTag("tag3");   //what should we do if f1 doesn't exist anymore?  How do we check and how do we fix?
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction ();  //Create a reference to a fragment transaction.
        ft.replace(R.id.FragLayout, f3);
        ft.addToBackStack ("myFrag3");
//        ft.hide(f2);
//        ft.hide(f1);
//        ft.show(f3);
        ft.commit();
    }
}
