package iss.workshop.adproject_team5_movieapp.Model;

import com.google.gson.annotations.SerializedName;

public class MachineLearningTest {

    @SerializedName("title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MachineLearningTest(){}

    public MachineLearningTest(String title) {
        this.title = title;
    }
}
