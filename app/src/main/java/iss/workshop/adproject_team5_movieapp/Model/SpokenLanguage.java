
package iss.workshop.adproject_team5_movieapp.Model;

import javax.annotation.Generated;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class SpokenLanguage implements Parcelable
{

    @SerializedName("english_name")
    @Expose
    private String englishName;
    @SerializedName("iso_639_1")
    @Expose
    private String iso6391;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<SpokenLanguage> CREATOR = new Creator<SpokenLanguage>() {


        public SpokenLanguage createFromParcel(android.os.Parcel in) {
            return new SpokenLanguage(in);
        }

        public SpokenLanguage[] newArray(int size) {
            return (new SpokenLanguage[size]);
        }

    }
            ;

    @SuppressWarnings({
            "unchecked"
    })
    protected SpokenLanguage(android.os.Parcel in) {
        this.englishName = ((String) in.readValue((String.class.getClassLoader())));
        this.iso6391 = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public SpokenLanguage() {
    }

    /**
     *
     * @param englishName
     * @param name
     * @param iso6391
     */
    public SpokenLanguage(String englishName, String iso6391, String name) {
        super();
        this.englishName = englishName;
        this.iso6391 = iso6391;
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(englishName);
        dest.writeValue(iso6391);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
