package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MotorcycleBrandList {
    @SerializedName("data")
    @Expose
    private List<MotorcycleBrand> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public MotorcycleBrandList() {
    }

    /**
     *
     * @param data
     */
    public MotorcycleBrandList(List<MotorcycleBrand> data) {
        super();
        this.data = data;
    }

    public List<MotorcycleBrand> getData() {
        return data;
    }

    public void setData(List<MotorcycleBrand> data) {
        this.data = data;
    }
}
