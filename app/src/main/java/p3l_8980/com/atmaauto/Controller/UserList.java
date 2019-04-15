package p3l_8980.com.atmaauto.Controller;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserList {

    @SerializedName("data")
    @Expose
    private List<User> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserList() {
    }

    /**
     *
     * @param data
     */
    public UserList(List<User> data) {
        super();
        this.data = data;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

}