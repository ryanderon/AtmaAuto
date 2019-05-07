package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicesData {
    @SerializedName("data")
    @Expose
    private Services data;

    /**
     * No args constructor for use in serialization
     *
     */
    public ServicesData() {
    }

    /**
     *
     * @param data
     */
    public ServicesData(Services data) {
        super();
        this.data = data;
    }

    public Services getData() {
        return data;
    }

    public void setData(Services data) {
        this.data = data;
    }
}
