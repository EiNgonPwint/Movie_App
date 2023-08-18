
package iss.workshop.adproject_team5_movieapp.Model;

import java.util.List;
import javax.annotation.Generated;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Credits implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cast")
    @Expose
    private List<Cast> cast;
    @SerializedName("crew")
    @Expose
    private List<Crew> crew;
    public final static Creator<Credits> CREATOR = new Creator<Credits>() {


        public Credits createFromParcel(android.os.Parcel in) {
            return new Credits(in);
        }

        public Credits[] newArray(int size) {
            return (new Credits[size]);
        }

    }
            ;

    @SuppressWarnings({
            "unchecked"
    })
    protected Credits(android.os.Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.cast, (iss.workshop.adproject_team5_movieapp.Model.Cast.class.getClassLoader()));
        in.readList(this.crew, (iss.workshop.adproject_team5_movieapp.Model.Crew.class.getClassLoader()));
    }

    public Credits() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(cast);
        dest.writeList(crew);
    }

    public int describeContents() {
        return  0;
    }

}
