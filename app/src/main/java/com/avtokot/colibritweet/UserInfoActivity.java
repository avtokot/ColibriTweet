package com.avtokot.colibritweet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class UserInfoActivity extends AppCompatActivity {

    private ImageView photoUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        photoUserProfile = (ImageView) findViewById(R.id.photo_user_profile);

        Picasso.with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .into(photoUserProfile);
    }
}
