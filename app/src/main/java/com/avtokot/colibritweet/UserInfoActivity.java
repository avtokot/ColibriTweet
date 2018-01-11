package com.avtokot.colibritweet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.Collection;

import adapter.TweetAdapter;
import pojo.Tweet;
import pojo.User;

public class UserInfoActivity extends AppCompatActivity {

    private ImageView photoUserImageView;
    private TextView nameTextView;
    private TextView nickTextView;
    private TextView descriptionTextView;
    private TextView locationTextView;
    private TextView followingCountTextView;
    private TextView followersCountTextView;

    private RecyclerView tweetsRecyclerView;

    private TweetAdapter tweetAdapter;


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
        initRecyclerView();
        loadTweets();
    }

    private void loadTweets() {
        Collection<Tweet> tweets = getTweets();
        tweetAdapter.setItems(tweets);
    }

    private Collection<Tweet> getTweets() {
        return Arrays.asList(
                new Tweet(getUser(), 1L, "Thu Dec 10 07:31:08 +0000 2017", "Использование RecyclerView говорит сам за себя 1",
                        23L, 39L, "https://www.w3schools.com/w3css/img_fjords.jpg"),
                new Tweet(getUser(), 2L, "Thu Dec 12 07:31:08 +0000 2017", "Использование RecyclerView дает больше возможностей нежели вы ожидали",
                        8L, 21L, "https://www.w3schools.com/w3images/lights.jpg"),
                new Tweet(getUser(), 3L, "Thu Dec 11 07:31:08 +0000 2017", "Картинки картинки и еще раз красивые картинки",
                        3L, 78L, "https://www.w3schools.com/css/img_mountains.jpg")
        );
    }

    private void initRecyclerView() {
        tweetsRecyclerView = (RecyclerView) findViewById(R.id.tweets_recycler_view);
        tweetsRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // отображение списка в виде линейного списка

        tweetAdapter = new TweetAdapter();
        tweetsRecyclerView.setAdapter(tweetAdapter);
    }

    private void loadUserInfo() {
        User user = getUser();
        displayUserInfo(user);
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
                        Toast.makeText(UserInfoActivity.this, "Соединения нет", Toast.LENGTH_SHORT).show();
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
