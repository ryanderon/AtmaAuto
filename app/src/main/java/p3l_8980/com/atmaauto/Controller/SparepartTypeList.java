package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SparepartTypeList {
    @SerializedName("data")
    @Expose
    private List<SparepartType> data = null;

    public SparepartTypeList() {
    }

    public SparepartTypeList(List<SparepartType> data) {
        this.data = data;
    }

    public List<SparepartType> getData() {
        return data;
    }

    public void setData(List<SparepartType> data) {
        this.data = data;
    }
}
