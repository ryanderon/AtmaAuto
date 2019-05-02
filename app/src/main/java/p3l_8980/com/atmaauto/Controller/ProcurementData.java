package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProcurementData {
    @SerializedName("data")
    @Expose
    private Procurement data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProcurementData() {
    }

    /**
     *
     * @param data
     */
    public ProcurementData(Procurement data) {
        super();
        this.data = data;
    }

    public Procurement getData() {
        return data;
    }

    public void setData(Procurement data) {
        this.data = data;
    }
}
