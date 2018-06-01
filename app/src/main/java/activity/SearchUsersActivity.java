package activity;


import android.content.Intent;
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

import com.avtokot.colibritweet.R;

import java.util.Arrays;
import java.util.Collection;

import adapter.UsersAdapter;
import pojo.User;

public class SearchUsersActivity extends AppCompatActivity {

    private RecyclerView usersRecyclerView;
    private UsersAdapter usersAdapter;

    private Toolbar toolbar;
    private EditText searchEditText;
    private ImageButton searchImageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_users);

        initRecyclerView();
        searchUsers();
        searchToolbarEditUsers(); // поиск users

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Слушатель для поиска users
        searchEditText.setOnClickListener(new View.OnClickListener() {
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchEditText = toolbar.findViewById(R.id.search_edit);
        searchImageBtn = toolbar.findViewById(R.id.search_button);
    }

    private void initRecyclerView() {
        usersRecyclerView = (RecyclerView) findViewById(R.id.users_recycler_view);
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

    private void searchUsers() {
        Collection<User> users = getUsers();
        usersAdapter.clearItems(); // удаляет старые элементы из адаптера
        usersAdapter.setItems(users);
    }

    private Collection<User> getUsers() {
        return Arrays.asList(
                new User(
                        34354647675443L,
                        "http://i.imgur.com/DvpvklR.png",
                        "Devcolibri",
                        "@devcolibri",
                        "Sample description",
                        "USA",
                        23,
                        12
                ),
                new User(
                        676875443L,
                        "http://pbs.twimg.com/profile_images/782474226020200448/zDo-gAo0_400x400.jpg",
                        "Elon Musk",
                        "@elonmusk",
                        "Hat Salesman",
                        "Borning",
                        14,
                        10
                )
        );
    }
}
