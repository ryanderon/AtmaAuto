package p3l_8980.com.atmaauto.Controller;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @Multipart
    @POST("spareparts")
    @FormUrlEncoded
    Call<ResponseBody> addSparepart(@Part MultipartBody.Part image,
                                    @Part("id_sparepart") RequestBody id_sparepart,
                                    @Part("sparepart_name") RequestBody sparepart_name,
                                    @Part("merk") RequestBody merk,
                                    @Part("stock") RequestBody stock,
                                    @Part("min_stock") RequestBody min_stock,
                                    @Part("purchase_price") RequestBody purchase_price,
                                    @Part("sell_price") RequestBody sell_price,
                                    @Part("placement") RequestBody placement,
                                    @Part("position") RequestBody position,
                                    @Part("place") RequestBody place,
                                    @Part("number") RequestBody number,
                                    @Part("id_sparepart_type") RequestBody id_sparepart_type);

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