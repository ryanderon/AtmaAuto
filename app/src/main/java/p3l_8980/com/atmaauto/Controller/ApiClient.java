package p3l_8980.com.atmaauto.Controller;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient {

    @POST("mobileauthenticate")
    @FormUrlEncoded
    Call<ResponseBody> GetLogin(
                @Field("username")String username,
                @Field("password")String password);

    @PUT("users/{id}")
    @FormUrlEncoded
    Call<UserData> updateUser(@Path("id") String id,
                              @Field("password") String password);


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

    @POST("spareparts")
    @FormUrlEncoded
    Call<SparepartData> addSparepart(@Field("id_sparepart") String id_sparepart,
                                     @Field("sparepart_name") String sparepart_name,
                                     @Field("merk") String merk,
                                     @Field("stock") int stock,
                                     @Field("min_stock") int min_stock,
                                     @Field("purchase_price") double purchase_price,
                                     @Field("sell_price") double sell_price,
                                     @Field("placement") String placement,
                                     @Field("position") String position,
                                     @Field("place") String place,
                                     @Field("number") int number,
                                     @Field("id_sparepart_type") int id_sparepart_type);

    @DELETE("spareparts/{id}")
    Call<ResponseBody> deleteSparepart(@Path("id") String id);

    @PUT("spareparts/{id}")
    @FormUrlEncoded
    Call<SparepartData> updateSparepart(@Path("id") String id,
                                       @Field("sparepart_name") String sparepart_name,
                                       @Field("merk") String merk,
                                       @Field("stock") int stock,
                                       @Field("min_stock") int min_stock,
                                       @Field("purchase_price") double purchase_price,
                                       @Field("sell_price") double sell_price,
                                       @Field("placement") String placement,
                                       @Field("id_sparepart_type") int id_sparepart_type);

}
