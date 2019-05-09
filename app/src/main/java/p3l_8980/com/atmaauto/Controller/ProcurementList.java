package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProcurementList {
    @SerializedName("data")
    @Expose
    private List<Procurement> data = new ArrayList<>();

    /**
     * No args constructor for use in serialization
     *
     */
    public ProcurementList() {
    }

    /**
     *
     * @param data
     */
    public ProcurementList(List<Procurement> data) {
        super();
        this.data = data;
    }

    public List<Procurement> getData() {
        return data;
    }

    public void setData(List<Procurement> data) {
        this.data = data;
    }
}
