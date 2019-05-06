package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SparepartTransaction {
    @SerializedName("id_detail_sparepart")
    @Expose
    private int idDetailSparepart;
    @SerializedName("detail_sparepart_amount")
    @Expose
    private int detailSparepartAmount;
    @SerializedName("detail_sparepart_price")
    @Expose
    private int detailSparepartPrice;
    @SerializedName("detail_sparepart_subtotal")
    @Expose
    private int detailSparepartSubtotal;
    @SerializedName("id_transaction")
    @Expose
    private String idTransaction;
    @SerializedName("id_sparepart")
    @Expose
    private String idSparepart;
    @SerializedName("sparepart_type")
    @Expose
    private String sparepartType;
    @SerializedName("sparepart_name")
    @Expose
    private String sparepartName;
    @SerializedName("merk")
    @Expose
    private String merk;
    @SerializedName("id_mechanic")
    @Expose
    private int idMechanic;
    @SerializedName("mechanic_name")
    @Expose
    private String mechanicName;
    @SerializedName("license_number")
    @Expose
    private String licenseNumber;

    /**
     * No args constructor for use in serialization
     *
     */
    public SparepartTransaction() {
    }

    /**
     *
     * @param merk
     * @param idSparepart
     * @param detailSparepartPrice
     * @param idDetailSparepart
     * @param licenseNumber
     * @param sparepartName
     * @param sparepartType
     * @param detailSparepartAmount
     * @param mechanicName
     * @param idMechanic
     * @param idTransaction
     * @param detailSparepartSubtotal
     */
    public SparepartTransaction(int idDetailSparepart, int detailSparepartAmount, int detailSparepartPrice, int detailSparepartSubtotal, String idTransaction, String idSparepart, String sparepartType, String sparepartName, String merk, int idMechanic, String mechanicName, String licenseNumber) {
        super();
        this.idDetailSparepart = idDetailSparepart;
        this.detailSparepartAmount = detailSparepartAmount;
        this.detailSparepartPrice = detailSparepartPrice;
        this.detailSparepartSubtotal = detailSparepartSubtotal;
        this.idTransaction = idTransaction;
        this.idSparepart = idSparepart;
        this.sparepartType = sparepartType;
        this.sparepartName = sparepartName;
        this.merk = merk;
        this.idMechanic = idMechanic;
        this.mechanicName = mechanicName;
        this.licenseNumber = licenseNumber;
    }

    public int getIdDetailSparepart() {
        return idDetailSparepart;
    }

    public void setIdDetailSparepart(int idDetailSparepart) {
        this.idDetailSparepart = idDetailSparepart;
    }

    public int getDetailSparepartAmount() {
        return detailSparepartAmount;
    }

    public void setDetailSparepartAmount(int detailSparepartAmount) {
        this.detailSparepartAmount = detailSparepartAmount;
    }

    public int getDetailSparepartPrice() {
        return detailSparepartPrice;
    }

    public void setDetailSparepartPrice(int detailSparepartPrice) {
        this.detailSparepartPrice = detailSparepartPrice;
    }

    public int getDetailSparepartSubtotal() {
        return detailSparepartSubtotal;
    }

    public void setDetailSparepartSubtotal(int detailSparepartSubtotal) {
        this.detailSparepartSubtotal = detailSparepartSubtotal;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getIdSparepart() {
        return idSparepart;
    }

    public void setIdSparepart(String idSparepart) {
        this.idSparepart = idSparepart;
    }

    public String getSparepartType() {
        return sparepartType;
    }

    public void setSparepartType(String sparepartType) {
        this.sparepartType = sparepartType;
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

    public int getIdMechanic() {
        return idMechanic;
    }

    public void setIdMechanic(int idMechanic) {
        this.idMechanic = idMechanic;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

}
