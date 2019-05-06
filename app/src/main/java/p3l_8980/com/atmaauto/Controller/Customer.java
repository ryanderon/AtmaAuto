package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("id_customer")
    @Expose
    private int idCustomer;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("customer_address")
    @Expose
    private String customerAddress;
    @SerializedName("customer_phone_number")
    @Expose
    private String customerPhoneNumber;

    /**
     * No args constructor for use in serialization
     *
     */
    public Customer() {
    }

    /**
     *
     * @param customerName
     * @param idCustomer
     * @param customerPhoneNumber
     * @param customerAddress
     */
    public Customer(int idCustomer, String customerName, String customerAddress, String customerPhoneNumber) {
        super();
        this.idCustomer = idCustomer;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
}
