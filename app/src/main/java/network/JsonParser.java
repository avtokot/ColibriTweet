package network;

import org.json.JSONException;
import org.json.JSONObject;

import pojo.User;

public class JsonParser {
    public User getUser(String responce) throws JSONException {
        JSONObject userJson = new JSONObject(responce);

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
}
