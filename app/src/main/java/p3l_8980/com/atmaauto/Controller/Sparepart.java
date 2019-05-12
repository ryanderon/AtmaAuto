package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

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
    private int stock;

    @SerializedName("min_stock")
    @Expose
    private int minStock;

    @SerializedName("purchase_price")
    @Expose
    private double purchasePrice;

    @SerializedName("sell_price")
    @Expose
    private double sellPrice;

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
    private int number;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("sparepart_type_name")
    @Expose
    private String sparepartTypeName;
    @SerializedName("id_sparepart_type")
    @Expose
    private int idSparepartType;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sparepart(){

    }

    public Sparepart(String idSparepart,String nama) {
        super();
        this.idSparepart=idSparepart;
        this.sparepartName=nama;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSparepartTypeName() {
        return sparepartTypeName;
    }

    public void setSparepartTypeName(String sparepartTypeName) {
        this.sparepartTypeName = sparepartTypeName;
    }

    public int getIdSparepartType() {
        return idSparepartType;
    }

    public void setIdSparepartType(int idSparepartType) {
        this.idSparepartType = idSparepartType;
    }

    @Override
    public String toString() {
        return sparepartName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Sparepart){
            Sparepart c = (Sparepart) obj;
            if(c.getSparepartName().equals(sparepartName) && c.getIdSparepart().equalsIgnoreCase(idSparepart) ) return true;
        }

        return false;
    }
}