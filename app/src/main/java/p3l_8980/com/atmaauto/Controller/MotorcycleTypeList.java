package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MotorcycleTypeList {
    @SerializedName("data")
    @Expose
    private List<MotorcycleType> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public MotorcycleTypeList() {
    }

    /**
     *
     * @param data
     */
    public MotorcycleTypeList(List<MotorcycleType> data) {
        super();
        this.data = data;
    }

    public List<MotorcycleType> getData() {
        return data;
    }

    public void setData(List<MotorcycleType> data) {
        this.data = data;
    }
}
