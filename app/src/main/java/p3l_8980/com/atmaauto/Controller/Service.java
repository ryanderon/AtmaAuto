package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {
    @SerializedName("id_detail_service")
    @Expose
    private int idDetailService;
    @SerializedName("detail_service_amount")
    @Expose
    private int detailServiceAmount;
    @SerializedName("detail_service_price")
    @Expose
    private int detailServicePrice;
    @SerializedName("detail_service_subtotal")
    @Expose
    private int detailServiceSubtotal;
    @SerializedName("id_transaction")
    @Expose
    private String idTransaction;
    @SerializedName("id_service")
    @Expose
    private int idService;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
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
    public Service() {
    }

    /**
     *
     * @param detailServiceSubtotal
     * @param idService
     * @param detailServicePrice
     * @param licenseNumber
     * @param detailServiceAmount
     * @param mechanicName
     * @param idDetailService
     * @param idMechanic
     * @param idTransaction
     * @param serviceName
     */
    public Service(int idDetailService, int detailServiceAmount, int detailServicePrice, int detailServiceSubtotal, String idTransaction, int idService, String serviceName, int idMechanic, String mechanicName, String licenseNumber) {
        super();
        this.idDetailService = idDetailService;
        this.detailServiceAmount = detailServiceAmount;
        this.detailServicePrice = detailServicePrice;
        this.detailServiceSubtotal = detailServiceSubtotal;
        this.idTransaction = idTransaction;
        this.idService = idService;
        this.serviceName = serviceName;
        this.idMechanic = idMechanic;
        this.mechanicName = mechanicName;
        this.licenseNumber = licenseNumber;
    }

    public int getIdDetailService() {
        return idDetailService;
    }

    public void setIdDetailService(int idDetailService) {
        this.idDetailService = idDetailService;
    }

    public int getDetailServiceAmount() {
        return detailServiceAmount;
    }

    public void setDetailServiceAmount(int detailServiceAmount) {
        this.detailServiceAmount = detailServiceAmount;
    }

    public int getDetailServicePrice() {
        return detailServicePrice;
    }

    public void setDetailServicePrice(int detailServicePrice) {
        this.detailServicePrice = detailServicePrice;
    }

    public int getDetailServiceSubtotal() {
        return detailServiceSubtotal;
    }

    public void setDetailServiceSubtotal(int detailServiceSubtotal) {
        this.detailServiceSubtotal = detailServiceSubtotal;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
