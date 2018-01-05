package com.avtokot.colibritweet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class UserInfoActivity extends AppCompatActivity {

    private ImageView photoUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        photoUserProfile = (ImageView) findViewById(R.id.photo_user_profile);


        Picasso.with(this)
                .load("http://i.imgur.com/DvpvklR.png") // откуда загружать
                .placeholder(R.drawable.do_it) // заглушка при отсутствии интернета
                .into(photoUserProfile, new Callback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(UserInfoActivity.this, "Фото профиля загружено", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {

                    }
                }); // место загрузки

    }
}
