package com.example.alexrosenfeld10.w6_p2;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawableFragment extends Fragment {

    ArrayList<Drawable> drawables;  //keeping track of our images
    ArrayList<Float> ratings; // keep track of our ratings
    private int currDrawableIndex;
    private ImageView imgRateMe;
    private Button btnLeft;
    private Button btnRight;
    private RatingBar ratingBar;

    public DrawableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_drawable, container, false);

        View v = inflater.inflate(R.layout.fragment_drawable, container, false);   //MUST HAPPEN FIRST, otherwise components don't exist.

//getting all drawable resources, handy third party see method below.
        currDrawableIndex = 0;
        getDrawables();

        imgRateMe = (ImageView) v.findViewById(R.id.imgRateMe);
        btnRight = (Button) v.findViewById(R.id.btnRight);
        btnLeft = (Button) v.findViewById(R.id.btnLeft);
        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratings.set(currDrawableIndex, rating);
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currDrawableIndex == 0)
                    currDrawableIndex = drawables.size() - 1;
                else
                    currDrawableIndex--;
                changePicture();
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currDrawableIndex == drawables.size() - 1)
                    currDrawableIndex = 0;
                else
                    currDrawableIndex++;

                changePicture();
            }
        });


        return v;   //must happen last, it is a return statement after all, it can't happen sooner!
    }


    public void changePicture() {
        imgRateMe.setImageDrawable(drawables.get(currDrawableIndex));
        ratingBar.setRating(ratings.get(currDrawableIndex));
    }


    //REF: http://stackoverflow.com/questions/31921927/how-to-get-all-drawable-resources

    public void getDrawables() {
        Field[] drawablesFields = R.drawable.class.getFields();
        drawables = new ArrayList<>();
        ratings = new ArrayList<>();

        String fieldName;
        for (Field field : drawablesFields) {
            try {
                fieldName = field.getName();
                Log.i("LOG_TAG", "com.your.project.R.drawable." + fieldName);
                if (fieldName.startsWith("animals_")) {  //only add drawable resources that have our prefix.
                    drawables.add(getResources().getDrawable(field.getInt(null)));
                    ratings.add(0f);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}