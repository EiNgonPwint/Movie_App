package iss.workshop.adproject_team5_movieapp.Model;

public class Social {

    private int social_id;
    private int userId;
    private int followingId;

    public int getSocial_id() {
        return social_id;
    }

    public int getUserId() {
        return userId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public  Social(){}

    public Social(int social_id, int userId, int followingId) {
        this.social_id = social_id;
        this.userId = userId;
        this.followingId = followingId;
    }
    public Social(int userId, int followerId) {

        this.userId = userId;
        this.followingId = followerId;
    }
}
