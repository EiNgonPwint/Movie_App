
package iss.workshop.adproject_team5_movieapp.Model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Results implements Parcelable
{

    @SerializedName("SG")
    @Expose
    private Sg sg;
    public final static Creator<Results> CREATOR = new Creator<Results>() {


        public Results createFromParcel(android.os.Parcel in) {
            return new Results(in);
        }

        public Results[] newArray(int size) {
            return (new Results[size]);
        }

    }
            ;

    @SuppressWarnings({
            "unchecked"
    })
    protected Results(android.os.Parcel in) {

        this.sg = ((Sg) in.readValue((Sg.class.getClassLoader())));
    }

    public Results() {
    }

    public Sg getSg() {
        return sg;
    }

    public void setSg(Sg sg) {
        this.sg = sg;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {

        dest.writeValue(sg);
    }

    public int describeContents() {
        return  0;
    }

}
