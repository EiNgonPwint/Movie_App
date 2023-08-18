
package iss.workshop.adproject_team5_movieapp.Model;

import javax.annotation.Generated;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ProductionCompany implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("logo_path")
    @Expose
    private String logoPath;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("origin_country")
    @Expose
    private String originCountry;
    public final static Creator<ProductionCompany> CREATOR = new Creator<ProductionCompany>() {


        public ProductionCompany createFromParcel(android.os.Parcel in) {
            return new ProductionCompany(in);
        }

        public ProductionCompany[] newArray(int size) {
            return (new ProductionCompany[size]);
        }

    }
            ;

    @SuppressWarnings({
            "unchecked"
    })
    protected ProductionCompany(android.os.Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.logoPath = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.originCountry = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public ProductionCompany() {
    }

    /**
     *
     * @param logoPath
     * @param name
     * @param originCountry
     * @param id
     */
    public ProductionCompany(Integer id, String logoPath, String name, String originCountry) {
        super();
        this.id = id;
        this.logoPath = logoPath;
        this.name = name;
        this.originCountry = originCountry;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(logoPath);
        dest.writeValue(name);
        dest.writeValue(originCountry);
    }

    public int describeContents() {
        return  0;
    }

}
