package p3l_8980.com.atmaauto.Controller;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {

    @POST("mobileauthenticate")
    @FormUrlEncoded
    Call<ResponseBody> GetLogin(
                @Field("username")String username,
                @Field("password")String password);

    @GET("suppliers")
    Call<SupplierList> getSuppliers();

    @POST("suppliers")
    @FormUrlEncoded
    Call<SupplierData> addSupplier(@Field("supplier_name") String supplier_name,
                                 @Field("supplier_address") String supplier_address,
                                 @Field("supplier_phone_number") String supplier_phone_number);

    @DELETE("suppliers/{id}")
    Call<ResponseBody> deleteSupplier(@Path("id") int id);

    @PUT("suppliers/{id}")
    @FormUrlEncoded
    Call<SupplierData> updateSupplier(@Path("id") int id,@Field("supplier_name") String supplier_name,
                                   @Field("supplier_address") String supplier_address,
                                   @Field("supplier_phone_number") String supplier_phone_number);

    @GET("spareparts")
    Call<SparepartList> getSpareparts();



}
