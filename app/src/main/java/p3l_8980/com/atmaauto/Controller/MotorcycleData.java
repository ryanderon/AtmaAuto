package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MotorcycleData {
    @SerializedName("data")
    @Expose
    private Motorcycle data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public MotorcycleData() {
    }

    /**
     *
     * @param data
     */
    public MotorcycleData(Motorcycle data) {
        super();
        this.data = data;
    }

    public Motorcycle getData() {
        return data;
    }

    public void setData(Motorcycle data) {
        this.data = data;
    }
}
