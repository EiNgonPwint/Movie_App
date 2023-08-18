
package iss.workshop.adproject_team5_movieapp.Model;

import java.util.List;
import javax.annotation.Generated;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Actor implements Parcelable
{

    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("also_known_as")
    @Expose
    private List<Object> alsoKnownAs;
    @SerializedName("biography")
    @Expose
    private String biography;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("deathday")
    @Expose
    private Object deathday;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    @SerializedName("known_for_department")
    @Expose
    private String knownForDepartment;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("place_of_birth")
    @Expose
    private String placeOfBirth;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    public final static Creator<Actor> CREATOR = new Creator<Actor>() {


        public Actor createFromParcel(android.os.Parcel in) {
            return new Actor(in);
        }

        public Actor[] newArray(int size) {
            return (new Actor[size]);
        }

    }
    ;

    @SuppressWarnings({
        "unchecked"
    })
    protected Actor(android.os.Parcel in) {
        this.adult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.alsoKnownAs, (Object.class.getClassLoader()));
        this.biography = ((String) in.readValue((String.class.getClassLoader())));
        this.birthday = ((String) in.readValue((String.class.getClassLoader())));
        this.deathday = ((Object) in.readValue((Object.class.getClassLoader())));
        this.gender = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.homepage = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.imdbId = ((String) in.readValue((String.class.getClassLoader())));
        this.knownForDepartment = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.placeOfBirth = ((String) in.readValue((String.class.getClassLoader())));
        this.popularity = ((Double) in.readValue((Double.class.getClassLoader())));
        this.profilePath = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Actor() {
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public List<Object> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<Object> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Object getDeathday() {
        return deathday;
    }

    public void setDeathday(Object deathday) {
        this.deathday = deathday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(adult);
        dest.writeList(alsoKnownAs);
        dest.writeValue(biography);
        dest.writeValue(birthday);
        dest.writeValue(deathday);
        dest.writeValue(gender);
        dest.writeValue(homepage);
        dest.writeValue(id);
        dest.writeValue(imdbId);
        dest.writeValue(knownForDepartment);
        dest.writeValue(name);
        dest.writeValue(placeOfBirth);
        dest.writeValue(popularity);
        dest.writeValue(profilePath);
    }

    public int describeContents() {
        return  0;
    }

}
