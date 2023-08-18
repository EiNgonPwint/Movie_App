
package iss.workshop.adproject_team5_movieapp.Model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class WatchProviders implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private Results results;
    public final static Creator<WatchProviders> CREATOR = new Creator<WatchProviders>() {


        public WatchProviders createFromParcel(android.os.Parcel in) {
            return new WatchProviders(in);
        }

        public WatchProviders[] newArray(int size) {
            return (new WatchProviders[size]);
        }

    }
            ;

    @SuppressWarnings({
            "unchecked"
    })
    protected WatchProviders(android.os.Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.results = ((Results) in.readValue((Results.class.getClassLoader())));
    }

    public WatchProviders() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(results);
    }

    public int describeContents() {
        return  0;
    }

}
