package emerald411.com.monarchmeetup;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexander.dohrn on 11/27/16.
 */

public class EventModel {

    @Nullable
    @SerializedName("_id")
    private String id;

    @Nullable
    @SerializedName("Title")
    private String name;

    @Nullable
    @SerializedName("description")
    private String description;

    @Nullable
    @SerializedName("date")
    private String dateString;

    @Nullable
    @SerializedName("type")
    private String type;

    @SerializedName("time")
    private String time;
    @SerializedName("password")
    private String password;

    @SerializedName("attendence")
    private int attendence;

    @SerializedName("location")
    private String location;

    @SerializedName("createDate")
    private String createDate;

    public EventModel() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateString() {
        if(dateString != null)
            return dateString;
        else
            return Util.timeStampToDate(createDate);
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getTime() {
        if(time != null)
            return time;
        else
            return "5:30pm";
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Nullable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAttendence() {
        return attendence;
    }

    public void setAttendence(int attendence) {
        this.attendence = attendence;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
