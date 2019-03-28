package p3l_8980.com.atmaauto.Controller;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDAO implements Parcelable{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("role")
    @Expose
    private String role;

    public UserDAO() {
    }

    /**
     *
     * @param id
     * @param username
     * @param name
     * @param role
     */
    public UserDAO(int id, String username, String name, String role) {
        super();
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
    }

    protected UserDAO(Parcel in) {
        id = in.readInt();
        username = in.readString();
        name = in.readString();
        role = in.readString();
    }

    public static final Creator<UserDAO> CREATOR = new Creator<UserDAO>() {
        @Override
        public UserDAO createFromParcel(Parcel in) {
            return new UserDAO(in);
        }

        @Override
        public UserDAO[] newArray(int size) {
            return new UserDAO[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(role);
        dest.writeInt(id);
    }
}
