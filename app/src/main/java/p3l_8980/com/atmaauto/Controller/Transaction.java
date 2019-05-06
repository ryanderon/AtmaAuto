package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction {
    @SerializedName("id_transaction")
    @Expose
    private String idTransaction;
    @SerializedName("transaction_status")
    @Expose
    private String transactionStatus;
    @SerializedName("transaction_date")
    @Expose
    private String transactionDate;
    @SerializedName("transaction_paid")
    @Expose
    private String transactionPaid;
    @SerializedName("transaction_type")
    @Expose
    private String transactionType;
    @SerializedName("transaction_discount")
    @Expose
    private int transactionDiscount;
    @SerializedName("transaction_total")
    @Expose
    private int transactionTotal;
    @SerializedName("id_customer")
    @Expose
    private int idCustomer;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("service")
    @Expose
    private DetailService service;
    @SerializedName("sparepart")
    @Expose
    private DetailSparepart sparepart;
//    @SerializedName("employee")
//    @Expose
//    private Employee employee;

    /**
     * No args constructor for use in serialization
     *
     */
    public Transaction() {
    }

    /**
     *
     * @param customerName
     * @param transactionType
     * @param idCustomer
     * @param service
     * @param sparepart
     * @param transactionTotal
     * @param transactionPaid
     * @param transactionStatus
     * @param idTransaction
     * @param transactionDate
     * @param transactionDiscount
     */
    public Transaction(String idTransaction, String transactionStatus, String transactionDate, String transactionPaid, String transactionType, int transactionDiscount, int transactionTotal, int idCustomer, String customerName, DetailService service, DetailSparepart sparepart) {
        super();
        this.idTransaction = idTransaction;
        this.transactionStatus = transactionStatus;
        this.transactionDate = transactionDate;
        this.transactionPaid = transactionPaid;
        this.transactionType = transactionType;
        this.transactionDiscount = transactionDiscount;
        this.transactionTotal = transactionTotal;
        this.idCustomer = idCustomer;
        this.customerName = customerName;
        this.service = service;
        this.sparepart = sparepart;    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionPaid() {
        return transactionPaid;
    }

    public void setTransactionPaid(String transactionPaid) {
        this.transactionPaid = transactionPaid;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getTransactionDiscount() {
        return transactionDiscount;
    }

    public void setTransactionDiscount(int transactionDiscount) {
        this.transactionDiscount = transactionDiscount;
    }

    public int getTransactionTotal() {
        return transactionTotal;
    }

    public void setTransactionTotal(int transactionTotal) {
        this.transactionTotal = transactionTotal;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public DetailService getService() {
        return service;
    }

    public void setService(DetailService service) {
        this.service = service;
    }

    public DetailSparepart getSparepart() {
        return sparepart;
    }

    public void setSparepart(DetailSparepart sparepart) {
        this.sparepart = sparepart;
    }

}
