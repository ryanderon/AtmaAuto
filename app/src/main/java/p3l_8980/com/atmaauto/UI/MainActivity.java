package p3l_8980.com.atmaauto.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.R;
import p3l_8980.com.atmaauto.Session.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button signInBtn;
    private TextView tError, logPengguna;
    private EditText txtUsername, txtPassword;


    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());
        if(session.isLoggedIn())
        {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                Log.w("getInstanceId failed", task.getException());
                                return;
                            }

                            // Get new Instance ID token
                            String token = task.getResult().getToken();

                            // Log and toast

                            Log.d("XXXXX", token);
                            Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                        }
                    });
            Intent intent = new Intent(getApplicationContext(), Beranda.class);
            startActivity(intent);
        }

        init();
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        logPengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomePengguna.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        signInBtn = (Button) findViewById(R.id.btnRegisterLogin);
        txtUsername = (EditText)findViewById(R.id.eUsernameRegister);
        txtPassword = (EditText)findViewById(R.id.ePasswordRegister);
        tError = findViewById(R.id.tvErrorLogin);
        logPengguna = findViewById(R.id.logPengguna);
        tError.setText("");
    }
    
    public void openHome(){
        Intent intent = new Intent(this, Beranda.class);
        startActivity(intent);
    }

    public void login(){
        if(txtUsername.getText().toString().isEmpty()||
                txtPassword.getText().toString().isEmpty()){
            tError.setText("Inputan Tidak Boleh Kosong");
        }else{

            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,R.style.AppTheme_Dark_Dialog);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            Retrofit retrofit= new retrofit2.Retrofit.Builder()
                    .baseUrl("https://p3l.yafetrakan.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiClient apiClient = retrofit.create(ApiClient.class);
//            Call<ResponseUser> call = apiUser.GetLogin(txtUsername.getText().toString(),txtPassword.getText().toString());
            Call<ResponseBody> call = apiClient.GetLogin(txtUsername.getText().toString(), txtPassword.getText().toString());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONObject jsonRes = new JSONObject(response.body().string());
                        Toast.makeText(getApplicationContext(), jsonRes.getJSONObject("data").getString("role"), Toast.LENGTH_SHORT).show();
                        session.createLoginSessions(
                                jsonRes.getJSONObject("data").getString("role"),
                                jsonRes.getJSONObject("data").getString("username"), jsonRes.getJSONObject("data").getString("id"));
                        final Intent intent = new Intent(MainActivity.this, Beranda.class);
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        progressDialog.dismiss();
                                    }
                                },3000);

                    } catch (JSONException e){
                        progressDialog.dismiss();
                        e.printStackTrace();
                    } catch (IOException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    } catch (Throwable e){
                        progressDialog.dismiss();
                        tError.setText("Username dan Password tidak cocok");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    tError.setText("Koneksi Internet Tidak Stabil");
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Internet err\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}
