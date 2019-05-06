package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Services {
    @SerializedName("id_service")
    @Expose
    private int idService;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("price")
    @Expose
    private int price;

    /**
     * No args constructor for use in serialization
     *
     */
    public Services() {
    }

    /**
     *
     * @param price
     * @param idService
     * @param serviceName
     */
    public Services(int idService, String serviceName, int price) {
        super();
        this.idService = idService;
        this.serviceName = serviceName;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return serviceName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Services){
            Services c = (Services) obj;
            if(c.getServiceName().equals(serviceName) && c.getIdService()==idService ) return true;
        }

        return false;
    }
}
