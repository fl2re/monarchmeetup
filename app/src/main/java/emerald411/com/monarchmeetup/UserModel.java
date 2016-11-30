package emerald411.com.monarchmeetup;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexander.dohrn on 11/21/16.
 */

public class UserModel {
    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    private String description;
    private int points;
    private int attended;
    private int created;
    private String attendedEvents;

    public UserModel() {
        points = 0;
        description = "";
        attended = 0;
        created = 0;
        attendedEvents = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAttended() {
        return attended;
    }

    public void setAttended(int attended) {
        this.attended = attended;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public String getAttendedEvents() {
        return attendedEvents;
    }

    public void setAttendedEvents(String attendedEvents) {
        this.attendedEvents = attendedEvents;
    }
}
