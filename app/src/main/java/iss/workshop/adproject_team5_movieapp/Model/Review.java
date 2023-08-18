package iss.workshop.adproject_team5_movieapp.Model;

public class Review {

    private int review_id;
    private int user_id;
    private int movie_id;
    private int rating;
    private String user_name;


    private String content;

    public Review(int userId, int movie_id,String user_name,String content) {
        this.user_id = userId;
        this.movie_id = movie_id;
        this.user_name = user_name;
        this.content = content;
    }

    private int getRating()
    {
        return rating;
    }

    private void setRating(int rating)
    {
        this.rating = rating;
    }

    public String getContent()
    {
        return content;
    }

    private void setContent(String content)
    {
        this.content = content;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
