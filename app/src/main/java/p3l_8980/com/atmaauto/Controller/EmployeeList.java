package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeList {
    @SerializedName("data")
    @Expose
    private List<Employee> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public EmployeeList() {
    }

    /**
     *
     * @param data
     */
    public EmployeeList(List<Employee> data) {
        super();
        this.data = data;
    }

    public List<Employee> getData() {
        return data;
    }

    public void setData(List<Employee> data) {
        this.data = data;
    }
}
