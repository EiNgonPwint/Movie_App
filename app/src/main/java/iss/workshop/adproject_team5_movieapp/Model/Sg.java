
package iss.workshop.adproject_team5_movieapp.Model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Sg implements Parcelable
{

    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("flatrate")
    @Expose
    private List<Flatrate__2> flatrate;
    @SerializedName("buy")
    @Expose
    private List<Buy__3> buy;
    @SerializedName("rent")
    @Expose
    private List<Rent__3> rent;
    public final static Creator<Sg> CREATOR = new Creator<Sg>() {


        public Sg createFromParcel(android.os.Parcel in) {
            return new Sg(in);
        }

        public Sg[] newArray(int size) {
            return (new Sg[size]);
        }

    }
            ;

    @SuppressWarnings({
            "unchecked"
    })
    protected Sg(android.os.Parcel in) {
        this.link = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.flatrate, (Flatrate__2.class.getClassLoader()));
        in.readList(this.buy, (Buy__3.class.getClassLoader()));
        in.readList(this.rent, (Rent__3.class.getClassLoader()));
    }

    public Sg() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Flatrate__2> getFlatrate() {
        return flatrate;
    }

    public void setFlatrate(List<Flatrate__2> flatrate) {
        this.flatrate = flatrate;
    }

    public List<Buy__3> getBuy() {
        return buy;
    }

    public void setBuy(List<Buy__3> buy) {
        this.buy = buy;
    }

    public List<Rent__3> getRent() {
        return rent;
    }

    public void setRent(List<Rent__3> rent) {
        this.rent = rent;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(link);
        dest.writeList(flatrate);
        dest.writeList(buy);
        dest.writeList(rent);
    }

    public int describeContents() {
        return  0;
    }

}
