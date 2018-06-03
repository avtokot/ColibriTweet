package activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avtokot.colibritweet.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.util.Collection;

import adapter.TweetAdapter;
import network.HttpClient;
import pojo.Tweet;
import pojo.User;

public class UserInfoActivity extends AppCompatActivity {

    public static final String USER_ID = "user_id";

    private ImageView photoUserImageView;
    private TextView nameTextView;
    private TextView nickTextView;
    private TextView descriptionTextView;
    private TextView locationTextView;
    private TextView followingCountTextView;
    private TextView followersCountTextView;

    private RecyclerView tweetsRecyclerView;

    private TweetAdapter tweetAdapter;

    private Toolbar toolbar;
    private EditText searchEditText;
    private ImageButton searchImageBtn;

    private HttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        long userId = getIntent().getLongExtra(USER_ID, -1);

        photoUserImageView = findViewById(R.id.photo_user_profile);
        nameTextView = findViewById(R.id.user_name_text_view);
        nickTextView = findViewById(R.id.user_nick_text_view);
        descriptionTextView = findViewById(R.id.user_description_text_view);
        locationTextView = findViewById(R.id.user_location_text_view);
        followersCountTextView = findViewById(R.id.followers_count_text_view);
        followingCountTextView = findViewById(R.id.following_count_text_view);

        httpClient = new HttpClient();

        loadUserInfo(userId);
        initRecyclerView();
        loadTweets(userId);
        initToolbar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_info_menu, menu);
        return true;
    } // создание меню

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_action:
                Intent intent = new Intent(this, SearchUsersActivity.class);
                startActivity(intent);
        }
        return true;
    } // выбор элементов меню

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void loadTweets(long userId) {
        new TweetsAsyncTask().execute(userId);
    }

    @SuppressLint("StaticFieldLeak")
    private class TweetsAsyncTask extends AsyncTask<Long, Integer, Collection<Tweet>> {

        @Override
        protected Collection<Tweet> doInBackground(Long... longs) {

            try {
                long userId = longs[0];
                return httpClient.readTweets(userId);

            } catch (JSONException | IOException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Collection<Tweet> tweets) {
            tweetAdapter.setItems(tweets);
        }
    }


    private void initRecyclerView() {
        tweetsRecyclerView = findViewById(R.id.tweets_recycler_view);
        tweetsRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // отображение списка в виде линейного списка

        tweetAdapter = new TweetAdapter();
        tweetsRecyclerView.setAdapter(tweetAdapter);
    }

    private void loadUserInfo(final long user_id) {
        new UserInfoAsyncTask().execute(user_id); // передаем user_id в метод execute
    }

    @SuppressLint("StaticFieldLeak")
    private class UserInfoAsyncTask extends AsyncTask<Long, Integer, User> {

        @Override
        protected User doInBackground(Long... id) {

            try {
                Long user_id = id[0];
                return httpClient.readUserInfo(user_id);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(User user) {
            displayUserInfo(user);
        }
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

        //getSupportActionBar().setTitle(user.getName());
    }
}
