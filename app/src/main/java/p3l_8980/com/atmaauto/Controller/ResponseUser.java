package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseUser {
    @SerializedName("data")
    @Expose
    private UserDAO data;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseUser() {
    }

    /**
     *
     * @param data
     */
    public ResponseUser(UserDAO data) {
        super();
        this.data = data;
    }

    public UserDAO getData() {
        return data;
    }

    public void setData(UserDAO data) {
        this.data = data;
    }
}
