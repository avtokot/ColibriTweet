package activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.avtokot.colibritweet.R;

import java.util.Arrays;
import java.util.Collection;

import adapter.UsersAdapter;
import pojo.User;

public class SearchUsersActivity extends AppCompatActivity {

    private RecyclerView usersRecyclerView;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_users);

        initRecyclerView();
        searchUsers();
    }

    private void initRecyclerView() {
        usersRecyclerView = (RecyclerView) findViewById(R.id.users_recycler_view);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        UsersAdapter.OnUserClickListener onUserClickListener = new UsersAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(User user) {
                //Toast.makeText(SearchUsersActivity.this, "user " + user.getName(), Toast.LENGTH_SHORT).show();
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
                        "23",
                        "12"
                ),
                new User(
                        676875443L,
                        "http://pbs.twimg.com/profile_images/782474226020200448/zDo-gAo0_400x400.jpg",
                        "Elon Musk",
                        "@elonmusk",
                        "Hat Salesman",
                        "Borning",
                        "14",
                        "10"
                )
        );
    }
}
