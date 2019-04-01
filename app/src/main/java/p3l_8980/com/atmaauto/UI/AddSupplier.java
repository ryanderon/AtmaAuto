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

import okhttp3.ResponseBody;
import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierData;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddSupplier extends AppCompatActivity {

    ImageView backButton;
    Button addButton;
    EditText supplierName,supplierAddress,supplierNumber;
    TextView title;
    private List<Supplier> SupplierBundle = new ArrayList<>();
    private int simpan = -1;
    private String cek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);
        init();
        simpan = getIntent().getIntExtra("simpan",-1);

        if(simpan > -1){
//            final Supplier data = SupplierBundle.get(simpan);
              title.setText("Ubah Supplier");
              supplierName.setText(getIntent().getStringExtra("name"));
              supplierAddress.setText(getIntent().getStringExtra("address"));
              supplierNumber.setText(getIntent().getStringExtra("number"));
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(AddSupplier.this, Beranda.class);
                intent.putExtra("addDialog", 1);
                startActivity(intent);
            }
        });

        if(simpan > -1){
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update();
                }
            });
        }else{
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    store();
                }
            });
        }


    }

    private void init(){
        backButton = findViewById(R.id.btnBack);
        addButton = findViewById(R.id.btnAdd);
        supplierName = findViewById(R.id.supplierName);
        supplierAddress = findViewById(R.id.supplierAddress);
        supplierNumber = findViewById(R.id.supplierNumber);
        title = findViewById(R.id.title);
    }

    public void store(){
        if(supplierName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nama supplier tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(supplierNumber.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nomor hp supplier tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(supplierAddress.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "alamat supplier tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                final ProgressDialog progressDialog = new ProgressDialog(AddSupplier.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setMessage("Memproses Data...");
                progressDialog.show();
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://p3l.yafetrakan.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                Call<SupplierData> addSupplier = apiClient.addSupplier(supplierName.getText().toString(), supplierAddress.getText().toString(), supplierNumber.getText().toString());

                addSupplier.enqueue(new Callback<SupplierData>() {
                    @Override
                    public void onResponse(Call<SupplierData> call, Response<SupplierData> response) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                        final Intent intent = new Intent(AddSupplier.this, Beranda.class);
                        intent.putExtra("addDialog", 1);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<SupplierData> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Gagal Input Data", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        if(supplierName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nama supplier tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(supplierNumber.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nomor hp supplier tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(supplierAddress.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "alamat supplier tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                final ProgressDialog progressDialog = new ProgressDialog(AddSupplier.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setMessage("Memproses Data...");
                progressDialog.show();
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://p3l.yafetrakan.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                Call<SupplierData> updateSupplier = apiClient.updateSupplier(getIntent().getIntExtra("id",0),supplierName.getText().toString(), supplierAddress.getText().toString(), supplierNumber.getText().toString());

                updateSupplier.enqueue(new Callback<SupplierData>() {
                    @Override
                    public void onResponse(Call<SupplierData> call, Response<SupplierData> response) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                        final Intent intent = new Intent(AddSupplier.this, Beranda.class);
                        intent.putExtra("addDialog", 1);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<SupplierData> call, Throwable t) {
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
