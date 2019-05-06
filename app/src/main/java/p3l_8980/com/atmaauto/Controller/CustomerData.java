package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerData {
    @SerializedName("data")
    @Expose
    private Customer data;

    /**
     * No args constructor for use in serialization
     *
     */
    public CustomerData() {
    }

    /**
     *
     * @param data
     */
    public CustomerData(Customer data) {
        super();
        this.data = data;
    }

    public Customer getData() {
        return data;
    }

    public void setData(Customer data) {
        this.data = data;
    }
}
