package p3l_8980.com.atmaauto.Controller;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SparepartType {
    @SerializedName("id_sparepart_type")
    @Expose
    private int idSparepartType;
    @SerializedName("sparepart_type_name")
    @Expose
    private String sparepartTypeName;

    public SparepartType() {
    }

    public SparepartType(int idSparepartType, String sparepartTypeName) {
        this.idSparepartType = idSparepartType;
        this.sparepartTypeName = sparepartTypeName;
    }

    public int getIdSparepartType() {
        return idSparepartType;
    }

    public void setIdSparepartType(int idSparepartType) {
        this.idSparepartType = idSparepartType;
    }

    public String getSparepartTypeName() {
        return sparepartTypeName;
    }

    public void setSparepartTypeName(String sparepartTypeName) {
        this.sparepartTypeName = sparepartTypeName;
    }

    @Override
    public String toString() {
        return sparepartTypeName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SparepartType){
            SparepartType c = (SparepartType) obj;
            if(c.getSparepartTypeName().equals(sparepartTypeName) && c.getIdSparepartType()==idSparepartType ) return true;
        }

        return false;
    }
}
