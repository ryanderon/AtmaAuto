package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sales {
    @SerializedName("id_sales")
    @Expose
    private int idSales;
    @SerializedName("sales_name")
    @Expose
    private String salesName;
    @SerializedName("id_supplier")
    @Expose
    private int idSupplier;
    @SerializedName("supplier_name")
    @Expose
    private String supplierName;
    @SerializedName("sales_phone_number")
    @Expose
    private String salesPhoneNumber;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sales() {
    }

    /**
     *
     * @param supplierName
     * @param idSupplier
     * @param salesName
     * @param salesPhoneNumber
     * @param idSales
     */
    public Sales(int idSales, String salesName, int idSupplier, String supplierName, String salesPhoneNumber) {
        super();
        this.idSales = idSales;
        this.salesName = salesName;
        this.idSupplier = idSupplier;
        this.supplierName = supplierName;
        this.salesPhoneNumber = salesPhoneNumber;
    }

    public Sales(int idSales, String salesName) {
        super();
        this.idSales = idSales;
        this.salesName = salesName;
    }

    public int getIdSales() {
        return idSales;
    }

    public void setIdSales(int idSales) {
        this.idSales = idSales;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
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

    public String getSalesPhoneNumber() {
        return salesPhoneNumber;
    }

    public void setSalesPhoneNumber(String salesPhoneNumber) {
        this.salesPhoneNumber = salesPhoneNumber;
    }

    @Override
    public String toString() {
        return salesName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Sales){
            Sales c = (Sales) obj;
            if(c.getSalesName().equals(salesName) && c.getIdSales()==idSales ) return true;
        }

        return false;
    }
}
