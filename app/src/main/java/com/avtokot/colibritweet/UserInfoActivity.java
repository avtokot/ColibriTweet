package com.avtokot.colibritweet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import pojo.User;

public class UserInfoActivity extends AppCompatActivity {

    private ImageView photoUserImageView;
    private TextView nameTextView;
    private TextView nickTextView;
    private TextView descriptionTextView;
    private TextView locationTextView;
    private TextView followingCountTextView;
    private TextView followersCountTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        photoUserImageView = (ImageView) findViewById(R.id.photo_user_profile);
        nameTextView = (TextView) findViewById(R.id.user_name_text_view);
        nickTextView = (TextView) findViewById(R.id.user_nick_text_view);
        descriptionTextView = (TextView) findViewById(R.id.user_description_text_view);
        locationTextView = (TextView) findViewById(R.id.user_location_text_view);
        followersCountTextView = (TextView) findViewById(R.id.followers_count_text_view);
        followingCountTextView = (TextView) findViewById(R.id.following_count_text_view);

        loadUserInfo();


    }

    private void displayUserInfo(User user) {
        Picasso.with(this)
                .load(user.getImageUrl()) // откуда загружать
                .placeholder(R.drawable.do_it) // заглушка
                .into(photoUserImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(UserInfoActivity.this, "Фото профиля загружено", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {

                    }
                }); // куда выгружать
        nameTextView.setText(user.getName());
        nickTextView.setText(user.getNick());
        descriptionTextView.setText(user.getDescription());
        locationTextView.setText(user.getLocation());

        String followingCount = String.valueOf(user.getFollowingCount());
        followingCountTextView.setText(followingCount);

        String followersCount = String.valueOf(user.getFollowersCount());
        followersCountTextView.setText(followersCount);
    }

    private void loadUserInfo() {
        User user = getUser();
        displayUserInfo(user);
    }

    private User getUser() {
        return new User(
                1L,
                "http://i.imgur.com/DvpvklR.png",
                "Devcolibri",
                "@devcolibri",
                "Sample description",
                "Usa",
                "23",
                "45"
        );
    }
}
