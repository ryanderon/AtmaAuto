package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ServicesList {
    @SerializedName("data")
    @Expose
    private List<Services> data = new ArrayList<>();

    /**
     * No args constructor for use in serialization
     *
     */
    public ServicesList() {
    }

    /**
     *
     * @param data
     */
    public ServicesList(List<Services> data) {
        super();
        this.data = data;
    }

    public List<Services> getData() {
        return data;
    }

    public void setData(List<Services> data) {
        this.data = data;
    }
}
