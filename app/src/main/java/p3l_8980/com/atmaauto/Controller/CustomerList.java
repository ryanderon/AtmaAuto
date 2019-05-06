package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerList {
    @SerializedName("data")
    @Expose
    private List<Customer> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public CustomerList() {
    }

    /**
     *
     * @param data
     */
    public CustomerList(List<Customer> data) {
        super();
        this.data = data;
    }

    public List<Customer> getData() {
        return data;
    }

    public void setData(List<Customer> data) {
        this.data = data;
    }
}
