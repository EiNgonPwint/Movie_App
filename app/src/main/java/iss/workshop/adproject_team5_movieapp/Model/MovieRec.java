package iss.workshop.adproject_team5_movieapp.Model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class MovieRec implements Parcelable
{

    @SerializedName("resultsRec")
    @Expose
    private List<ResultsRec> resultsRec;
    public final static Creator<MovieRec> CREATOR = new Creator<MovieRec>() {


        public MovieRec createFromParcel(android.os.Parcel in) {
            return new MovieRec(in);
        }

        public MovieRec[] newArray(int size) {
            return (new MovieRec[size]);
        }

    }
            ;

    @SuppressWarnings({
            "unchecked"
    })
    protected MovieRec(android.os.Parcel in) {
        in.readList(this.resultsRec, (iss.workshop.adproject_team5_movieapp.Model.ResultsRec.class.getClassLoader()));
    }

    public MovieRec() {
    }

    public List<ResultsRec> getResultsRec() {
        return resultsRec;
    }

    public void setResultsRec(List<ResultsRec> resultsRec) {
        this.resultsRec = resultsRec;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeList(resultsRec);
    }

    public int describeContents() {
        return  0;
    }

}

