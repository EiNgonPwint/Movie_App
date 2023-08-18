
package iss.workshop.adproject_team5_movieapp.Model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Flatrate__1 implements Parcelable
{

    @SerializedName("display_priority")
    @Expose
    private Integer displayPriority;
    @SerializedName("logo_path")
    @Expose
    private String logoPath;
    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("provider_name")
    @Expose
    private String providerName;
    public final static Creator<Flatrate__1> CREATOR = new Creator<Flatrate__1>() {


        public Flatrate__1 createFromParcel(android.os.Parcel in) {
            return new Flatrate__1(in);
        }

        public Flatrate__1 [] newArray(int size) {
            return (new Flatrate__1[size]);
        }

    }
            ;

    @SuppressWarnings({
            "unchecked"
    })
    protected Flatrate__1(android.os.Parcel in) {
        this.displayPriority = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.logoPath = ((String) in.readValue((String.class.getClassLoader())));
        this.providerId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.providerName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Flatrate__1() {
    }

    public Integer getDisplayPriority() {
        return displayPriority;
    }

    public void setDisplayPriority(Integer displayPriority) {
        this.displayPriority = displayPriority;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(displayPriority);
        dest.writeValue(logoPath);
        dest.writeValue(providerId);
        dest.writeValue(providerName);
    }

    public int describeContents() {
        return  0;
    }

}
