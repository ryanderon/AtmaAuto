package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailSparepart {
    @SerializedName("data")
    @Expose
    private List<SparepartTransaction> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public DetailSparepart() {
    }

    /**
     *
     * @param data
     */
    public DetailSparepart(List<SparepartTransaction> data) {
        super();
        this.data = data;
    }

    public List<SparepartTransaction> getData() {
        return data;
    }

    public void setData(List<SparepartTransaction> data) {
        this.data = data;
    }
}
