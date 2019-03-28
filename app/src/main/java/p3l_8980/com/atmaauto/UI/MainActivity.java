package p3l_8980.com.atmaauto.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import p3l_8980.com.atmaauto.Controller.LoginAPI;
import p3l_8980.com.atmaauto.Controller.ResponseUser;
import p3l_8980.com.atmaauto.Controller.UserDAO;
import p3l_8980.com.atmaauto.R;
import p3l_8980.com.atmaauto.UI.Beranda;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button signInBtn;
    private EditText txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void init(){
        signInBtn = (Button) findViewById(R.id.btnRegisterLogin);
        txtUsername = (EditText)findViewById(R.id.eUsernameRegister);
        txtPassword = (EditText)findViewById(R.id.ePasswordRegister);
    }
    
    public void openHome(){
        Intent intent = new Intent(this, Beranda.class);
        startActivity(intent);
    }

    public void login(){
        if(txtUsername.getText().toString().isEmpty()||
                txtPassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else{

            Retrofit retrofit= new retrofit2.Retrofit.Builder()
                    .baseUrl("https://p3l.yafetrakan.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            LoginAPI apiUser = retrofit.create(LoginAPI.class);
            Call<ResponseUser> call = apiUser.GetLogin(txtUsername.getText().toString(),txtPassword.getText().toString());
            call.enqueue(new Callback<ResponseUser>() {
                @Override
                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                    if(response.isSuccessful()){
                        Log.d("response api", response.body().toString());
                        if(response.body().getData()!=null){
                            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,R.style.AppTheme_Dark_Dialog);
                            progressDialog.setIndeterminate(true);
                            progressDialog.setMessage("Authenticating...");
                            progressDialog.show();
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

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Username dan password tidak sesuai", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Error code " , Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseUser> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Internet err\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}
