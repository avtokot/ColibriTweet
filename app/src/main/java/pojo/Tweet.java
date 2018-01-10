package pojo;

public class Tweet {
    private User user;
    private long id;
    private String createDate;
    private String text;
    private long retweetCount;
    private long favoriteCount;
    private String imageUrl;

    public Tweet(User user, long id, String createDate, String text, long retweetCount, long favoriteCount, String imageUrl) {
        this.user = user;
        this.id = id;
        this.createDate = createDate;
        this.text = text;
        this.retweetCount = retweetCount;
        this.favoriteCount = favoriteCount;
        this.imageUrl = imageUrl;
    }

    public User getUser() {
        return user;
    }

    public long getId() {
        return id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getText() {
        return text;
    }

    public long getRetweetCount() {
        return retweetCount;
    }

    public long getFavoriteCount() {
        return favoriteCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (id != tweet.id) return false;
        if (retweetCount != tweet.retweetCount) return false;
        if (favoriteCount != tweet.favoriteCount) return false;
        if (!user.equals(tweet.user)) return false;
        if (!createDate.equals(tweet.createDate)) return false;
        if (!text.equals(tweet.text)) return false;
        return imageUrl != null ? imageUrl.equals(tweet.imageUrl) : tweet.imageUrl == null;

    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + createDate.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + (int) (retweetCount ^ (retweetCount >>> 32));
        result = 31 * result + (int) (favoriteCount ^ (favoriteCount >>> 32));
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }
}
