package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sparepart {

    @SerializedName("id_sparepart")
    @Expose
    private String idSparepart;

    @SerializedName("sparepart_name")
    @Expose
    private String sparepartName;

    @SerializedName("merk")
    @Expose
    private String merk;

    @SerializedName("stock")
    @Expose
    private String stock;

    @SerializedName("min_stock")
    @Expose
    private String minStock;

    @SerializedName("purchase_price")
    @Expose
    private String purchasePrice;

    @SerializedName("sell_price")
    @Expose
    private String sellPrice;

    @SerializedName("placement")
    @Expose
    private String placement;

    @SerializedName("position")
    @Expose
    private String position;

    @SerializedName("place")
    @Expose
    private String place;

    @SerializedName("number")
    @Expose
    private String number;
//    @SerializedName("image")
//    @Expose
//    private String image;
    @SerializedName("sparepart_type_name")
    @Expose
    private String sparepartTypeName;
    @SerializedName("id_sparepart_type")
    @Expose
    private String idSparepartType;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sparepart() {
    }

    /**
     *
     * @param merk
     * @param sellPrice
     * @param placement
     * @param position
     * @param idSparepartType
     * @param sparepartTypeName
     * @param number
     * @param idSparepart
     * @param stock
     * @param minStock
     * @param sparepartName
     * @param place
     * @param purchasePrice
     */
     /* @param *image */

    public Sparepart(String idSparepart, String sparepartName, String merk, String stock, String minStock, String purchasePrice, String sellPrice, String placement, String position, String place, String number, /*String image ,*/ String sparepartTypeName, String idSparepartType) {
        super();
        this.idSparepart = idSparepart;
        this.sparepartName = sparepartName;
        this.merk = merk;
        this.stock = stock;
        this.minStock = minStock;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
        this.placement = placement;
        this.position = position;
        this.place = place;
        this.number = number;
/*        this.image = image; */
        this.sparepartTypeName = sparepartTypeName;
        this.idSparepartType = idSparepartType;
    }

    public String getIdSparepart() {
        return idSparepart;
    }

    public void setIdSparepart(String idSparepart) {
        this.idSparepart = idSparepart;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getMinStock() {
        return minStock;
    }

    public void setMinStock(String minStock) {
        this.minStock = minStock;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
/*
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
*/
    public String getSparepartTypeName() {
        return sparepartTypeName;
    }

    public void setSparepartTypeName(String sparepartTypeName) {
        this.sparepartTypeName = sparepartTypeName;
    }

    public String getIdSparepartType() {
        return idSparepartType;
    }

    public void setIdSparepartType(String idSparepartType) {
        this.idSparepartType = idSparepartType;
    }

}