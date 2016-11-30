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
    @SerializedName("createDate")
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

    public EventModel() {}

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getDateString() {
        return dateString;
    }

    public void setDateString(@Nullable String dateString) {
        this.dateString = dateString;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Nullable
    public String getType() {
        return type;
    }

    public void setType(@Nullable String type) {
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
}
