package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Procurement {
    @SerializedName("id_procurement")
    @Expose
    private int id_procurement;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("procurement_status")
    @Expose
    private String procurementStatus;
    @SerializedName("id_sales")
    @Expose
    private int id_sales;
    @SerializedName("id_supplier")
    @Expose
    private int idSupplier;
    @SerializedName("sales")
    @Expose
    private String sales;
    @SerializedName("supplier")
    @Expose
    private String supplier;
    @SerializedName("detail")
    @Expose
    private Detail detail;

    /**
     * No args constructor for use in serialization
     *
     */
    public Procurement() {
    }

    /**
     *
     * @param detail
     * @param idSupplier
     * @param sales
     * @param procurementStatus
     * @param date
     * @param idProcurement
     * @param idSales
     */
    public Procurement(int idProcurement, String date, String procurementStatus, int idSales, int idSupplier, String sales, Detail detail) {
        super();
        this.id_procurement = idProcurement;
        this.date = date;
        this.procurementStatus = procurementStatus;
        this.id_sales = idSales;
        this.idSupplier = idSupplier;
        this.sales = sales;
        this.detail = detail;
    }

    public int getIdProcurement() {
        return id_procurement;
    }

    public void setIdProcurement(int idProcurement) {
        this.id_procurement = idProcurement;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProcurementStatus() {
        return procurementStatus;
    }

    public void setProcurementStatus(String procurementStatus) {
        this.procurementStatus = procurementStatus;
    }

    public int getIdSales() {
        return id_sales;
    }

    public void setIdSales(int idSales) {
        this.id_sales = idSales;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String suppliers) {
        this.supplier = suppliers;
    }
}
