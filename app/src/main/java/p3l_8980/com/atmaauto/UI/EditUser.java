package p3l_8980.com.atmaauto.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.SupplierData;
import p3l_8980.com.atmaauto.Controller.User;
import p3l_8980.com.atmaauto.Controller.UserData;
import p3l_8980.com.atmaauto.R;
import p3l_8980.com.atmaauto.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditUser extends AppCompatActivity {

    ImageView backButton;
    Button addButton;
    EditText oldPassword,newPassword,newPassword2;
    TextView title;
    SessionManager session;
    private List<User> UserBundle = new ArrayList<>();
    private String cek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frguser);
        init();
        session = new SessionManager(EditUser.this);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(EditUser.this, Beranda.class);
                intent.putExtra("addDialog", 1);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update();
                }
            });

    }

    private void init(){
        backButton = findViewById(R.id.btnBack);
        addButton = findViewById(R.id.btnAdd);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        newPassword2 = findViewById(R.id.newPassword2);
        title = findViewById(R.id.title);
    }

    public void update(){
        if(oldPassword.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Password Lama tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(newPassword.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Password Baru tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(newPassword2.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Konfirmasi Password Baru tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else  if(!newPassword2.getText().toString().equals(newPassword.getText().toString())){
            Toast.makeText(getApplicationContext(), "Konfirmasi Password Baru dan Password Baru tidak sama", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                final ProgressDialog progressDialog = new ProgressDialog(EditUser.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setMessage("Memproses Data...");
                progressDialog.show();
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("http://192.168.19.140/8991/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                Call<UserData> updateUser = apiClient.updateUser( session.getKeyId(),newPassword.getText().toString());

                updateUser.enqueue(new Callback<UserData>() {
                    @Override
                    public void onResponse(Call<UserData> call, Response<UserData> response) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                        final Intent intent = new Intent(EditUser.this, Beranda.class);
                        intent.putExtra("addDialog", 1);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<UserData> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Gagal Input Data", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
