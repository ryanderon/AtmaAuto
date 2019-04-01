package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Supplier {
    @SerializedName("id_supplier")
    @Expose
    private int idSupplier;
    @SerializedName("supplier_name")
    @Expose
    private String supplierName;
    @SerializedName("supplier_address")
    @Expose
    private String supplierAddress;
    @SerializedName("supplier_phone_number")
    @Expose
    private String supplierPhoneNumber;

    /**
     * No args constructor for use in serialization
     *
     */
    public Supplier() {
    }

    /**
     *
     * @param supplierName
     * @param supplierAddress
     * @param idSupplier
     * @param supplierPhoneNumber
     */
    public Supplier(int idSupplier, String supplierName, String supplierAddress, String supplierPhoneNumber) {
        super();
        this.idSupplier = idSupplier;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }

    public void setSupplierPhoneNumber(String supplierPhoneNumber) {
        this.supplierPhoneNumber = supplierPhoneNumber;
    }
}
