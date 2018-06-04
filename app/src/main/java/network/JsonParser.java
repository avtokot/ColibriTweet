package network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import pojo.Tweet;
import pojo.User;

public class JsonParser {
    public User getUser(String responce) throws JSONException {
        JSONObject userJson = new JSONObject(responce);
        return getUser(userJson);
    }

    private User getUser(JSONObject userJson) throws JSONException {
        long id = userJson.getLong("id");
        String name = userJson.getString("name");
        String nick = userJson.getString("screen_name");
        String location = userJson.getString("location");
        String description = userJson.getString("description");
        String imageUrl = userJson.getString("profile_image_url");
        int followersCount = userJson.getInt("followers_count");
        int followingCount = userJson.getInt("favourites_count");
        return new User(id, imageUrl, name, nick, description, location, followingCount, followersCount);
    }

    public Collection<User> getUsers(String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);
        Collection<User> usersResult = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject usersJson = jsonArray.getJSONObject(i);
            User user = getUser(usersJson);
            usersResult.add(user);
        }

        return usersResult;
    }

    public Collection<Tweet> getTweets(String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);
        Collection<Tweet> tweetsResult = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject tweetJson = jsonArray.getJSONObject(i);
            long id = tweetJson.getLong("id");
            String createDate = tweetJson.getString("created_at");
            String fullText = tweetJson.getString("full_text");
            long retweetCount = tweetJson.getLong("retweet_count");
            long likesCount = tweetJson.getLong("favorite_count");

            String imageUrl = getTweetImageUrl(tweetJson);
            JSONObject userJson = tweetJson.getJSONObject("user");
            User user = getUser(userJson);

            Tweet tweet = new Tweet(user, id, createDate, fullText, retweetCount, likesCount, imageUrl);
            tweetsResult.add(tweet);
        }
        return tweetsResult;
    }

    private String getTweetImageUrl(JSONObject tweetJson) throws JSONException {
        JSONObject entities = tweetJson.getJSONObject("entities");
        JSONArray mediaArray = entities.has("media") ? entities.getJSONArray("media") : null;
        JSONObject firstMedia = mediaArray != null ? mediaArray.getJSONObject(0) : null;
        return firstMedia != null ? firstMedia.getString("media_url") : null;
    }
}
