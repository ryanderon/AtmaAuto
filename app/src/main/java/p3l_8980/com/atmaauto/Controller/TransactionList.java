package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionList {
    @SerializedName("data")
    @Expose
    private List<Transaction> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public TransactionList() {
    }

    /**
     *
     * @param data
     */
    public TransactionList(List<Transaction> data) {
        super();
        this.data = data;
    }

    public List<Transaction> getData() {
        return data;
    }

    public void setData(List<Transaction> data) {
        this.data = data;
    }
}
