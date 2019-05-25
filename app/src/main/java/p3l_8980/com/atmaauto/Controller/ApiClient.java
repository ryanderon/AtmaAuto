package p3l_8980.com.atmaauto.Controller;

import java.util.List;

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

    @GET("spareparts/{id}")
    Call<SparepartData> getSparepart(@Path("id") String id);

    @GET("spareparts")
    Call<SparepartList> getSpareparts();


    @POST("spareparts")
    @Multipart
    Call<ResponseBody> addSparepart(@Part MultipartBody.Part image,
                                    @Part("id_sparepart") RequestBody id_sparepart,
                                    @Part("sparepart_name") RequestBody sparepart_name,
                                    @Part("merk") RequestBody merk,
                                    @Part("stock") RequestBody stock,
                                    @Part("min_stock") RequestBody min_stock,
                                    @Part("purchase_price") RequestBody purchase_price,
                                    @Part("sell_price") RequestBody sell_price,
                                    @Part("placement") RequestBody placement,
                                    @Part("id_sparepart_type") RequestBody id_sparepart_type);

    @DELETE("spareparts/{id}")
    Call<ResponseBody> deleteSparepart(@Path("id") String id);

    @POST("updatesparepart/{id}")
    @Multipart
    Call<ResponseBody> updateSparepart( @Path("id") String id,
                                        @Part MultipartBody.Part image,
                                        @Part("sparepart_name") RequestBody sparepart_name,
                                        @Part("merk") RequestBody merk,
                                        @Part("stock") RequestBody stock,
                                        @Part("min_stock") RequestBody min_stock,
                                        @Part("purchase_price") RequestBody purchase_price,
                                        @Part("sell_price") RequestBody sell_price,
                                        @Part("placement") RequestBody placement,
                                        @Part("id_sparepart_type") RequestBody id_sparepart_type);

    @PUT("updatesparepartmobile/{id}")
    @FormUrlEncoded
    Call<ResponseBody> updateSparepartVerif(@Path("id") String id,@Field("sell_price") double sell_price,
                                      @Field("price") double price,
                                      @Field("amount") int amount);

    @GET("sparepart_types")
    Call<SparepartTypeList> getSparepartTypes();

    @GET("procurements")
    Call<ProcurementList> getProcurement();

    @DELETE("procurements/{id}")
    Call<ResponseBody> deleteProcurement(@Path("id") int id);

    @POST("procurements")
    @FormUrlEncoded
    Call<ResponseBody> addProcurement(@Field("date") String date,
                                      @Field("procurement_status") String procurement_status,
                                      @Field("id_sales") int id_sales);

    @PUT("procurements/{id}")
    @FormUrlEncoded
    Call<ResponseBody> updateProcurement(@Path("id") int id,@Field("date") String date,
                                      @Field("procurement_status") String procurement_status,
                                      @Field("id_sales") int id_sales);

    @POST("procurementDetails")
    @FormUrlEncoded
    Call<ResponseBody> addProcurementDetail(@Field("price") double price,
                                      @Field("amount") int amount,
                                      @Field("subtotal") double subtotal,
                                      @Field("id_sparepart") String idSparepart,
                                      @Field("id_procurement") int idProcurement);

    @PUT("procurementDetails/{id}")
    @FormUrlEncoded
    Call<ResponseBody> updateProcurementDetail(@Path("id") int id,@Field("subtotal") double subtotal,
                                            @Field("price") double price,
                                            @Field("amount") int amount);

    @GET("procurementDetails/{id}")
    Call<Detail> getProcurementDetail(@Path("id") int id);

    @GET("sales")
    Call<SalesList> getSales();

    @GET("motorcycle_brands")
    Call<MotorcycleBrandList> getMotorcycleBrand();

    @GET("motorcycle_types")
    Call<MotorcycleTypeList> getMotorcycleType();

    @GET("transactions")
    Call<TransactionList> getTransaction();

    @GET("customers")
    Call<CustomerList> getCustomer();

    @GET("services")
    Call<ServicesList> getServices();

    @GET("employees")
    Call<EmployeeList> getEmployee();

    @POST("customers")
    @FormUrlEncoded
    Call<CustomerData> addCustomer(@Field("customer_name") String customer_name,
                                   @Field("customer_address") String customer_address,
                                   @Field("customer_phone_number") String customer_phone_number);

    @PUT("customers/{id}")
    @FormUrlEncoded
    Call<CustomerData> updateCustomer(@Path("id") int id,@Field("customer_name") String customer_name,
                                      @Field("customer_address") String customer_address,
                                      @Field("customer_phone_number") String customer_phone_number);

    @DELETE("customers/{id}")
    Call<ResponseBody> deleteCustomer(@Path("id") int id);

    @GET("usermotorcycles/{id}")
    Call<MotorcycleList> getMotorcycle(@Path("id") int id);

    @DELETE("motorcycles/{id}")
    Call<ResponseBody> deleteMotor(@Path("id") int id);


    @POST("motorcycles")
    @FormUrlEncoded
    Call<MotorcycleData> addMotorcycle(@Field("license_number") String license_number,
                                   @Field("id_motorcycle_type") int id_motorcycle_type,
                                   @Field("id_customer") int id_customer);
    @PUT("motorcycles/{id}")
    @FormUrlEncoded
    Call<MotorcycleData> updateMotorcycle(@Path("id") int id,@Field("license_number") String license_number,
                                          @Field("id_motorcycle_type") int id_motorcycle_type,
                                          @Field("id_customer") int id_customer);

    @GET("services/{id}")
    Call<ServicesData> getService(@Path("id") int id);

    @POST("transactions")
    @FormUrlEncoded
    Call<ResponseBody> addTransaction(@Field("transaction_type") String transaction_type,
                                      @Field("transaction_status") String transaction_status,
                                      @Field("transaction_total") double transaction_total,
                                      @Field("id_customer") int id_customer);

    @PUT("transactions/{id}")
    @FormUrlEncoded
    Call<ResponseBody> updateTransaction(@Path("id") String id,@Field("transaction_type") String transaction_type,
                                      @Field("transaction_status") String transaction_status,
                                      @Field("transaction_total") double transaction_total,
                                      @Field("id_customer") int id_customer);

    @DELETE("transactions/{id}")
    Call<ResponseBody> deleteTransaction(@Path("id") String id);

    @POST("detailService")
    @FormUrlEncoded
    Call<ResponseBody> addDetailService(@Field("detail_service_price") double price,
                                        @Field("detail_service_amount") int amount,
                                        @Field("detail_service_subtotal") double subtotal,
                                        @Field("id_motorcycle") int idMotorcycle,
                                        @Field("id_employee") int idEmployee,
                                        @Field("id_service") int idService,
                                        @Field("id_transaction") String idTransaction);

    @POST("detailSparepart")
    @FormUrlEncoded
    Call<ResponseBody> addDetailSparepart(@Field("detail_sparepart_price") double price,
                                         @Field("detail_sparepart_amount") int amount,
                                         @Field("detail_sparepart_subtotal") double subtotal,
                                         @Field("id_motorcycle") int idMotorcycle,
                                         @Field("id_employee") int idEmployee,
                                         @Field("id_sparepart") String idSparepart,
                                         @Field("id_transaction") String idTransaction);

    @GET("detailService/{id}")
    Call<DetailService> getDetailService(@Path("id") String id);

    @GET("detailSparepart/{id}")
    Call<DetailSparepart> getDetailSparepart(@Path("id") String id);

    @POST("searchDetail")
    @FormUrlEncoded
    Call<HistoryList> checkMotor(@Field("license_number") String license_number,
                                 @Field("phone_number") String phone_number);

    @POST("notifications")
    @FormUrlEncoded
    Call<ResponseBody> addToken(@Field("token") String token);
}