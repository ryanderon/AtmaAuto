package p3l_8980.com.atmaauto.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Customer;
import p3l_8980.com.atmaauto.Controller.CustomerData;
import p3l_8980.com.atmaauto.Controller.SupplierData;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddCustomer extends AppCompatActivity {

    ImageView backButton;
    Button addButton;
    private int simpan = -1;
    EditText customerName,customerPhone,customerAddress;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        init();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(AddCustomer.this, MenuCustomer.class);
                intent.putExtra("addDialog", 4);
                startActivity(intent);
            }
        });

        simpan = getIntent().getIntExtra("simpan",-1);

        if(simpan > -1){

            customerName.setText(getIntent().getStringExtra("name"));
            customerAddress.setText(getIntent().getStringExtra("address"));
            customerPhone.setText(getIntent().getStringExtra("number"));

        }

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
        customerName = findViewById(R.id.customerName);
        customerAddress = findViewById(R.id.customerAddress);
        customerPhone = findViewById(R.id.customerNumber);
        addButton = findViewById(R.id.btnAdd);
    }

    public void store(){
        if(customerName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nama customer tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(customerPhone.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nomor hp customer tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(customerAddress.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "alamat customer tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                final ProgressDialog progressDialog = new ProgressDialog(AddCustomer.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setMessage("Memproses Data...");
                progressDialog.show();
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("http://192.168.19.140/8991/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                Call<CustomerData> addSupplier = apiClient.addCustomer(customerName.getText().toString(), customerAddress.getText().toString(), customerPhone.getText().toString());

                addSupplier.enqueue(new Callback<CustomerData>() {
                    @Override
                    public void onResponse(Call<CustomerData> call, Response<CustomerData> response) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                        final Intent intent = new Intent(AddCustomer.this, MotorData.class);
                        intent.putExtra("id",response.body().getData().getIdCustomer());
                        intent.putExtra("name",response.body().getData().getCustomerName());
                        Log.d("namecustomer",response.body().getData().getCustomerName());
                        intent.putExtra("addDialog", 4);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<CustomerData> call, Throwable t) {
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
        if(customerName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nama customer tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(customerPhone.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "nomor hp customer tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else if(customerAddress.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "alamat customer tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                final ProgressDialog progressDialog = new ProgressDialog(AddCustomer.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setMessage("Memproses Data...");
                progressDialog.show();
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("http://192.168.19.140/8991/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                Call<CustomerData> updateCustomer = apiClient.updateCustomer(getIntent().getIntExtra("id",0),customerName.getText().toString(), customerAddress.getText().toString(), customerPhone.getText().toString());

                updateCustomer.enqueue(new Callback<CustomerData>() {
                    @Override
                    public void onResponse(Call<CustomerData> call, Response<CustomerData> response) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                        final Intent intent = new Intent(AddCustomer.this, MotorData.class);
                        intent.putExtra("id",getIntent().getIntExtra("id",0));
                        intent.putExtra("name",response.body().getData().getCustomerName());
                        intent.putExtra("addDialog", 4);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<CustomerData> call, Throwable t) {
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
