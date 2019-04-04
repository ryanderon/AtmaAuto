package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SparepartData {

    @SerializedName("data")
    @Expose
    private Sparepart data;

    /**
     * No args constructor for use in serialization
     *
     */
    public SparepartData() {
    }

    /**
     *
     * @param data
     */
    public SparepartData(Sparepart data) {
        super();
        this.data = data;
    }

    public Sparepart getData() {
        return data;
    }

    public void setData(Sparepart data) {
        this.data = data;
    }
}
