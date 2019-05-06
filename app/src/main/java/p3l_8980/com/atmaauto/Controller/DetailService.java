package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailService {
    @SerializedName("data")
    @Expose
    private List<Service> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public DetailService() {
    }

    /**
     *
     * @param data
     */
    public DetailService(List<Service> data) {
        super();
        this.data = data;
    }

    public List<Service> getData() {
        return data;
    }

    public void setData(List<Service> data) {
        this.data = data;
    }
}
