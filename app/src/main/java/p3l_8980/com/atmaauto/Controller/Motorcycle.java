package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Motorcycle {
    @SerializedName("id_motorcycle")
    @Expose
    private int idMotorcycle;
    @SerializedName("license_number")
    @Expose
    private String licenseNumber;
    @SerializedName("motorcycle_type")
    @Expose
    private String motorcycleType;
    @SerializedName("motorcycle_brand")
    @Expose
    private String motorcycleBrand;
    @SerializedName("id_motorcycle_type")
    @Expose
    private int idMotorcycleType;
    @SerializedName("id_motorcycle_brand")
    @Expose
    private int idMotorcycleBrand;
    @SerializedName("id_customer")
    @Expose
    private int idCustomer;
    @SerializedName("customer")
    @Expose
    private String customer;
    @SerializedName("edit")
    @Expose
    private boolean edit;

    /**
     * No args constructor for use in serialization
     *
     */
    public Motorcycle() {
    }

    /**
     *
     * @param idMotorcycleType
     * @param motorcycleBrand
     * @param idCustomer
     * @param licenseNumber
     * @param edit
     * @param idMotorcycleBrand
     * @param idMotorcycle
     * @param customer
     * @param motorcycleType
     */
    public Motorcycle(int idMotorcycle, String licenseNumber, String motorcycleType, String motorcycleBrand, int idMotorcycleType, int idMotorcycleBrand, int idCustomer, String customer, boolean edit) {
        super();
        this.idMotorcycle = idMotorcycle;
        this.licenseNumber = licenseNumber;
        this.motorcycleType = motorcycleType;
        this.motorcycleBrand = motorcycleBrand;
        this.idMotorcycleType = idMotorcycleType;
        this.idMotorcycleBrand = idMotorcycleBrand;
        this.idCustomer = idCustomer;
        this.customer = customer;
        this.edit = edit;
    }

    public Motorcycle(int idMotorcycle, String licenseNumber) {
        super();
        this.idMotorcycle = idMotorcycle;
        this.licenseNumber = licenseNumber;
    }

    public int getIdMotorcycle() {
        return idMotorcycle;
    }

    public void setIdMotorcycle(int idMotorcycle) {
        this.idMotorcycle = idMotorcycle;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getMotorcycleType() {
        return motorcycleType;
    }

    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }

    public String getMotorcycleBrand() {
        return motorcycleBrand;
    }

    public void setMotorcycleBrand(String motorcycleBrand) {
        this.motorcycleBrand = motorcycleBrand;
    }

    public int getIdMotorcycleType() {
        return idMotorcycleType;
    }

    public void setIdMotorcycleType(int idMotorcycleType) {
        this.idMotorcycleType = idMotorcycleType;
    }

    public int getIdMotorcycleBrand() {
        return idMotorcycleBrand;
    }

    public void setIdMotorcycleBrand(int idMotorcycleBrand) {
        this.idMotorcycleBrand = idMotorcycleBrand;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    @Override
    public String toString() {
        return licenseNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Motorcycle){
            Motorcycle c = (Motorcycle) obj;
            if(c.getLicenseNumber().equals(licenseNumber) && c.getIdMotorcycle()==idMotorcycle ) return true;
        }

        return false;
    }
}
