package pojo;

/**
 * Created by go on 10.01.2018.
 */

public class User {
    private long id;
    private String imageUrl;
    private String name;
    private String nick;
    private String location;
    private String description;
    private String followingCount;
    private String followersCount;

    public User(long id, String imageUrl, String name, String nick, String description, String location, String followingCount, String followersCount) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.nick = nick;
        this.description = description;
        this.location = location;
        this.followingCount = followingCount;
        this.followersCount = followersCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(String followingCount) {
        this.followingCount = followingCount;
    }

    public String getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(String followersCount) {
        this.followersCount = followersCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (imageUrl != null ? !imageUrl.equals(user.imageUrl) : user.imageUrl != null)
            return false;
        if (!name.equals(user.name)) return false;
        if (!nick.equals(user.nick)) return false;
        if (location != null ? !location.equals(user.location) : user.location != null)
            return false;
        if (description != null ? !description.equals(user.description) : user.description != null)
            return false;
        if (followingCount != null ? !followingCount.equals(user.followingCount) : user.followingCount != null)
            return false;
        return followersCount != null ? followersCount.equals(user.followersCount) : user.followersCount == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + nick.hashCode();
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (followingCount != null ? followingCount.hashCode() : 0);
        result = 31 * result + (followersCount != null ? followersCount.hashCode() : 0);
        return result;
    }
}
