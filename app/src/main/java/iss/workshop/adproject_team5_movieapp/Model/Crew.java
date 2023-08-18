
package iss.workshop.adproject_team5_movieapp.Model;

import javax.annotation.Generated;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Crew implements Parcelable
{

    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("known_for_department")
    @Expose
    private String knownForDepartment;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("profile_path")
    @Expose
    private Object profilePath;
    @SerializedName("credit_id")
    @Expose
    private String creditId;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("job")
    @Expose
    private String job;
    public final static Creator<Crew> CREATOR = new Creator<Crew>() {


        public Crew createFromParcel(android.os.Parcel in) {
            return new Crew(in);
        }

        public Crew[] newArray(int size) {
            return (new Crew[size]);
        }

    }
            ;

    @SuppressWarnings({
            "unchecked"
    })
    protected Crew(android.os.Parcel in) {
        this.adult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.gender = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.knownForDepartment = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.originalName = ((String) in.readValue((String.class.getClassLoader())));
        this.popularity = ((Double) in.readValue((Double.class.getClassLoader())));
        this.profilePath = ((Object) in.readValue((Object.class.getClassLoader())));
        this.creditId = ((String) in.readValue((String.class.getClassLoader())));
        this.department = ((String) in.readValue((String.class.getClassLoader())));
        this.job = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Crew() {
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Object getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(Object profilePath) {
        this.profilePath = profilePath;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(adult);
        dest.writeValue(gender);
        dest.writeValue(id);
        dest.writeValue(knownForDepartment);
        dest.writeValue(name);
        dest.writeValue(originalName);
        dest.writeValue(popularity);
        dest.writeValue(profilePath);
        dest.writeValue(creditId);
        dest.writeValue(department);
        dest.writeValue(job);
    }

    public int describeContents() {
        return  0;
    }

}
