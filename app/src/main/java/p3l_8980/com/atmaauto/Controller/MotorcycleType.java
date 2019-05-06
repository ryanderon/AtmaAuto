package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MotorcycleType {
    @SerializedName("id_motorcycle_type")
    @Expose
    private int idMotorcycleType;
    @SerializedName("motorcycle_type_name")
    @Expose
    private String motorcycleTypeName;
    @SerializedName("id_motorcycle_brand")
    @Expose
    private int idMotorcycleBrand;
    @SerializedName("motorcycle_brand")
    @Expose
    private String motorcycleBrand;

    /**
     * No args constructor for use in serialization
     *
     */
    public MotorcycleType() {
    }

    /**
     *
     * @param idMotorcycleType
     * @param motorcycleBrand
     * @param idMotorcycleBrand
     * @param motorcycleTypeName
     */
    public MotorcycleType(int idMotorcycleType, String motorcycleTypeName, int idMotorcycleBrand, String motorcycleBrand) {
        super();
        this.idMotorcycleType = idMotorcycleType;
        this.motorcycleTypeName = motorcycleTypeName;
        this.idMotorcycleBrand = idMotorcycleBrand;
        this.motorcycleBrand = motorcycleBrand;
    }

    public MotorcycleType(int idMotorcycleType, String motorcycleTypeName) {
        super();
        this.idMotorcycleType = idMotorcycleType;
        this.motorcycleTypeName = motorcycleTypeName;
    }

    public int getIdMotorcycleType() {
        return idMotorcycleType;
    }

    public void setIdMotorcycleType(int idMotorcycleType) {
        this.idMotorcycleType = idMotorcycleType;
    }

    public String getMotorcycleTypeName() {
        return motorcycleTypeName;
    }

    public void setMotorcycleTypeName(String motorcycleTypeName) {
        this.motorcycleTypeName = motorcycleTypeName;
    }

    public int getIdMotorcycleBrand() {
        return idMotorcycleBrand;
    }

    public void setIdMotorcycleBrand(int idMotorcycleBrand) {
        this.idMotorcycleBrand = idMotorcycleBrand;
    }

    public String getMotorcycleBrand() {
        return motorcycleBrand;
    }

    public void setMotorcycleBrand(String motorcycleBrand) {
        this.motorcycleBrand = motorcycleBrand;
    }

    @Override
    public String toString() {
        return motorcycleTypeName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MotorcycleType){
            MotorcycleType c = (MotorcycleType) obj;
            if(c.getMotorcycleTypeName().equals(motorcycleTypeName) && c.getIdMotorcycleType()==idMotorcycleType ) return true;
        }

        return false;
    }
}
