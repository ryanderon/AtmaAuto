package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Detail {
    @SerializedName("data")
    @Expose
    private List<ProcurementDetail> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Detail() {
    }

    /**
     *
     * @param data
     */
    public Detail(List<ProcurementDetail> data) {
        super();
        this.data = data;
    }

    public List<ProcurementDetail> getData() {
        return data;
    }

    public void setData(List<ProcurementDetail> data) {
        this.data = data;
    }
}
