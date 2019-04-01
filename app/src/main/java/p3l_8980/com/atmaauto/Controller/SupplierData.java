package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupplierData {

    @SerializedName("data")
    @Expose
    private Supplier data;

    /**
     * No args constructor for use in serialization
     *
     */
    public SupplierData() {
    }

    /**
     *
     * @param data
     */
    public SupplierData(Supplier data) {
        super();
        this.data = data;
    }

    public Supplier getData() {
        return data;
    }

    public void setData(Supplier data) {
        this.data = data;
    }
}
