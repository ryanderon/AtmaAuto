package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SparepartList {
    @SerializedName("data")
    @Expose
    private List<Sparepart> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public SparepartList() {
    }

    /**
     *
     * @param data
     */
    public SparepartList(List<Sparepart> data) {
        super();
        this.data = data;
    }

    public List<Sparepart> getData() {
        return data;
    }

    public void setData(List<Sparepart> data) {
        this.data = data;
    }
}
