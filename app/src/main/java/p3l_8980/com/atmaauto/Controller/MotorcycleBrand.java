package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MotorcycleBrand {
    @SerializedName("id_motorcycle_brand")
    @Expose
    private int idMotorcycleBrand;
    @SerializedName("motorcycle_brand_name")
    @Expose
    private String motorcycleBrandName;

    /**
     * No args constructor for use in serialization
     *
     */
    public MotorcycleBrand() {
    }

    /**
     *
     * @param motorcycleBrandName
     * @param idMotorcycleBrand
     */
    public MotorcycleBrand(int idMotorcycleBrand, String motorcycleBrandName) {
        super();
        this.idMotorcycleBrand = idMotorcycleBrand;
        this.motorcycleBrandName = motorcycleBrandName;
    }

    public int getIdMotorcycleBrand() {
        return idMotorcycleBrand;
    }

    public void setIdMotorcycleBrand(int idMotorcycleBrand) {
        this.idMotorcycleBrand = idMotorcycleBrand;
    }

    public String getMotorcycleBrandName() {
        return motorcycleBrandName;
    }

    public void setMotorcycleBrandName(String motorcycleBrandName) {
        this.motorcycleBrandName = motorcycleBrandName;
    }

    @Override
    public String toString() {
        return motorcycleBrandName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MotorcycleBrand){
            MotorcycleBrand c = (MotorcycleBrand) obj;
            if(c.getMotorcycleBrandName().equals(motorcycleBrandName) && c.getIdMotorcycleBrand()==idMotorcycleBrand ) return true;
        }

        return false;
    }
}
