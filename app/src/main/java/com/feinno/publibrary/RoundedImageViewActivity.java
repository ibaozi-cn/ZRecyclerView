package com.feinno.publibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.feinno.publibrary.widget.RoundedImageView;

public class RoundedImageViewActivity extends AppCompatActivity {

    private RoundedImageView roundedImageView;

    public static void startActivity(String imageUrl, Activity activity, Pair pair) {
        Intent i = new Intent(activity, RoundedImageViewActivity.class);
        i.putExtra("imageURl", imageUrl);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
        ActivityCompat.startActivity(activity, i, transitionActivityOptions.toBundle());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounded_image_view);

        roundedImageView = (RoundedImageView) findViewById(R.id.roundedImageView);

        Glide.with(this).load(getIntent().getStringExtra("imageURl")).into(roundedImageView);

    }


}
