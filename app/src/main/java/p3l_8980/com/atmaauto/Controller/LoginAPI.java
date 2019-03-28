package p3l_8980.com.atmaauto.Controller;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginAPI {
    @FormUrlEncoded
    @POST("mobileauthenticate")
    Call<ResponseUser>GetLogin(
            @Field("username")String username,
            @Field("password")String password);
}
