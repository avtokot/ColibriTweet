package adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avtokot.colibritweet.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pojo.Tweet;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetVieHolder> {

    private static final String TWITTER_RESPONSE_FORMAT = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
    private static final String MONTH_DAY_FORMAT = "MMM d";

    private List<Tweet> tweetList = new ArrayList<>();


    @Override
    public TweetAdapter.TweetVieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweet_item_view, parent, false);
        return new TweetVieHolder(view);
    }

    @Override
    public void onBindViewHolder(TweetAdapter.TweetVieHolder holder, int position) {
        holder.bind(tweetList.get(position));
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    } // кол-во элементов в списке


    public void setItems(Collection<Tweet> tweets) {
        tweetList.addAll(tweets);
        notifyDataSetChanged(); // служит для перерисовки экрана
    } // наполнение коллекции


    public void clearItems() {
        tweetList.clear();
        notifyDataSetChanged(); // служит для перерисовки экрана
    } // чистка коллекции

    class TweetVieHolder extends RecyclerView.ViewHolder {

        private ImageView userImageView;
        private TextView nameTextView;
        private TextView nickTextView;
        private TextView createDateTextView;
        private TextView contentTextView;
        private ImageView tweetImageView;
        private TextView retweetsTextView;
        private TextView likesTextView;

        // Инициализация View - компонентов происходит в конструкторе
        public TweetVieHolder(View itemView) {
            super(itemView);

            userImageView = itemView.findViewById(R.id.profile_image_view);
            nameTextView = itemView.findViewById(R.id.author_name_text_view);
            nickTextView = itemView.findViewById(R.id.author_nick_text_view);
            createDateTextView = itemView.findViewById(R.id.create_date_text_view);
            contentTextView = itemView.findViewById(R.id.tweet_content_text_view);
            tweetImageView = itemView.findViewById(R.id.tweet_image_view);
            retweetsTextView = itemView.findViewById(R.id.retweet_text_view);
            likesTextView = itemView.findViewById(R.id.likes_text_view);
        }

        public void bind(Tweet tweet) {
            nameTextView.setText(tweet.getUser().getName());
            nickTextView.setText(tweet.getUser().getNick());
            contentTextView.setText(tweet.getText());
            retweetsTextView.setText(String.valueOf(tweet.getRetweetCount()));
            likesTextView.setText(String.valueOf(tweet.getFavoriteCount()));

            String createDateFormatted = getFormattedDate(tweet.getCreateDate());
            createDateTextView.setText(createDateFormatted);

            Picasso.with(itemView.getContext())
                    .load(tweet.getUser().getImageUrl())
                    .into(userImageView);

            String tweetPhotoUrl = tweet.getImageUrl();
            Picasso.with(itemView.getContext())
                    .load(tweetPhotoUrl)
                    .into(tweetImageView);

            tweetImageView.setVisibility(tweetPhotoUrl != null ? View.VISIBLE : View.GONE);
        }

        private String getFormattedDate(String rawDate) {
            SimpleDateFormat utcFormat = new SimpleDateFormat(TWITTER_RESPONSE_FORMAT, Locale.US);
            SimpleDateFormat displayedFormat = new SimpleDateFormat(MONTH_DAY_FORMAT, Locale.getDefault());
            try {
                Date date = utcFormat.parse(rawDate);
                return displayedFormat.format(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
