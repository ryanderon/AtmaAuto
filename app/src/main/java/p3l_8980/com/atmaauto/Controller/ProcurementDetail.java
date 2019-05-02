package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProcurementDetail {
    @SerializedName("id_procurement_detail")
    @Expose
    private int idProcurementDetail;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("sell_price")
    @Expose
    private double sell_price;
    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("subtotal")
    @Expose
    private double subtotal;
    @SerializedName("id_procurement")
    @Expose
    private int id_procurement;
    @SerializedName("id_sparepart")
    @Expose
    private String id_sparepart;
    @SerializedName("sparepart_name")
    @Expose
    private String sparepartName;
    @SerializedName("merk")
    @Expose
    private String merk;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProcurementDetail() {
    }

    /**
     *
     * @param amount
     * @param sellPrice
     * @param idSparepart
     * @param price
     * @param sparepartName
     * @param idProcurementDetail
     * @param idProcurement
     * @param subtotal
     */
    public ProcurementDetail(int idProcurementDetail, int price, int sellPrice, int amount, int subtotal, int idProcurement, String idSparepart, String sparepartName) {
        super();
        this.idProcurementDetail = idProcurementDetail;
        this.price = price;
        this.sell_price = sellPrice;
        this.amount = amount;
        this.subtotal = subtotal;
        this.id_procurement = idProcurement;
        this.id_sparepart = idSparepart;
        this.sparepartName = sparepartName;
    }

    public ProcurementDetail(double price,int amount, double subtotal,String idSparepart, String sparepartName) {
        super();
        this.price = price;
        this.amount = amount;
        this.subtotal = subtotal;
        this.id_sparepart = idSparepart;
        this.sparepartName = sparepartName;
    }

    public int getIdProcurementDetail() {
        return idProcurementDetail;
    }

    public void setIdProcurementDetail(int idProcurementDetail) {
        this.idProcurementDetail = idProcurementDetail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getSellPrice() {
        return sell_price;
    }

    public void setSellPrice(double sellPrice) {
        this.sell_price = sellPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getIdProcurement() {
        return id_procurement;
    }

    public void setIdProcurement(int idProcurement) {
        this.id_procurement = idProcurement;
    }

    public String getIdSparepart() {
        return id_sparepart;
    }

    public void setIdSparepart(String idSparepart) {
        this.id_sparepart = idSparepart;
    }

    public String getSparepartName() {
        return sparepartName;
    }

    public void setSparepartName(String sparepartName) {
        this.sparepartName = sparepartName;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

}
