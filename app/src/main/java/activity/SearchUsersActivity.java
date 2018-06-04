package activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.avtokot.colibritweet.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.Collection;

import adapter.UsersAdapter;
import network.HttpClient;
import pojo.User;

public class SearchUsersActivity extends AppCompatActivity {

    private RecyclerView usersRecyclerView;
    private UsersAdapter usersAdapter;

    private Toolbar toolbar;
    private EditText searchEditText;
    private ImageButton searchImageBtn;

    private HttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_users);

        initRecyclerView();

        searchToolbarEditUsers(); // поиск users

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        httpClient = new HttpClient();

        // Слушатель для поиска users
        searchImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchUsers();
            }
        });
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    searchUsers();
                    return true;
                }
                return false;
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void searchUsers() {
        final String query = searchEditText.getText().toString();
        if (query.length() == 0) {
            Toast.makeText(SearchUsersActivity.this, R.string.not_symbols_msg, Toast.LENGTH_SHORT).show();
            return;
        }
        new UsersAsyncTask().execute(query);
    }

    @SuppressLint("StaticFieldLeak")
    private class UsersAsyncTask extends AsyncTask<String, Integer, Collection<User>> {

        @Override
        protected Collection<User> doInBackground(String... strings) {
            String query = strings[0];
            try {
                return httpClient.readUsers(query);
            } catch (IOException | JSONException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Collection<User> users) {
            if (users != null) {
                usersAdapter.clearItems();
                usersAdapter.setItems(users);
            } else {
                Toast.makeText(SearchUsersActivity.this, R.string.str_loading_error_msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchToolbarEditUsers() {
        toolbar = findViewById(R.id.toolbar);
        searchEditText = toolbar.findViewById(R.id.search_edit);
        searchImageBtn = toolbar.findViewById(R.id.search_button);
    }

    private void initRecyclerView() {
        usersRecyclerView = findViewById(R.id.users_recycler_view);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        UsersAdapter.OnUserClickListener onUserClickListener = new UsersAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(User user) {

                Intent intent = new Intent(SearchUsersActivity.this, UserInfoActivity.class);
                intent.putExtra(UserInfoActivity.USER_ID, user.getId());
                startActivity(intent);
            }
        };

        usersAdapter = new UsersAdapter(onUserClickListener);
        usersRecyclerView.setAdapter(usersAdapter);
    }
}
