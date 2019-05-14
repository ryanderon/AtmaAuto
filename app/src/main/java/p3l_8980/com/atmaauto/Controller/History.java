package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History {
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
    @SerializedName("id_employee")
    @Expose
    private int idEmployee;
    @SerializedName("mechanic_name")
    @Expose
    private String mechanicName;
    @SerializedName("license_number")
    @Expose
    private String licenseNumber;
    @SerializedName("id_motorcycle")
    @Expose
    private int idMotorcycle;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("payment")
    @Expose
    private String payment;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     *
     */
    public History() {
    }

    /**
     *
     * @param detailServiceSubtotal
     * @param payment
     * @param status
     * @param detailServicePrice
     * @param idDetailService
     * @param mechanicName
     * @param idEmployee
     * @param type
     * @param date
     * @param serviceName
     * @param idService
     * @param licenseNumber
     * @param detailServiceAmount
     * @param idMotorcycle
     * @param idTransaction
     */
    public History(int idDetailService, int detailServiceAmount, int detailServicePrice, int detailServiceSubtotal, String idTransaction, int idService, String serviceName, int idEmployee, String mechanicName, String licenseNumber, int idMotorcycle, String status, String payment, String date, String type) {
        super();
        this.idDetailService = idDetailService;
        this.detailServiceAmount = detailServiceAmount;
        this.detailServicePrice = detailServicePrice;
        this.detailServiceSubtotal = detailServiceSubtotal;
        this.idTransaction = idTransaction;
        this.idService = idService;
        this.serviceName = serviceName;
        this.idEmployee = idEmployee;
        this.mechanicName = mechanicName;
        this.licenseNumber = licenseNumber;
        this.idMotorcycle = idMotorcycle;
        this.status = status;
        this.payment = payment;
        this.date = date;
        this.type = type;
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

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
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

    public int getIdMotorcycle() {
        return idMotorcycle;
    }

    public void setIdMotorcycle(int idMotorcycle) {
        this.idMotorcycle = idMotorcycle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
