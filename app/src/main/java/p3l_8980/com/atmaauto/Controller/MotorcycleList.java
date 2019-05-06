package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MotorcycleList {
    @SerializedName("data")
    @Expose
    private List<Motorcycle> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public MotorcycleList() {
    }

    /**
     *
     * @param data
     */
    public MotorcycleList(List<Motorcycle> data) {
        super();
        this.data = data;
    }

    public List<Motorcycle> getData() {
        return data;
    }

    public void setData(List<Motorcycle> data) {
        this.data = data;
    }
}
