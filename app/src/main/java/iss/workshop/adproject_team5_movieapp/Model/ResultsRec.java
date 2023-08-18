
package iss.workshop.adproject_team5_movieapp.Model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class ResultsRec implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("similarity")
    @Expose
    private Double similarity;
    @SerializedName("title")
    @Expose
    private String title;
    public final static Creator<ResultsRec> CREATOR = new Creator<ResultsRec>() {


        public ResultsRec createFromParcel(android.os.Parcel in) {
            return new ResultsRec(in);
        }

        public ResultsRec[] newArray(int size) {
            return (new ResultsRec[size]);
        }

    }
            ;

    @SuppressWarnings({
            "unchecked"
    })
    protected ResultsRec(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.similarity = ((Double) in.readValue((Double.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ResultsRec() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(similarity);
        dest.writeValue(title);
    }

    public int describeContents() {
        return  0;
    }

}
