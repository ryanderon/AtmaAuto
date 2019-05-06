package p3l_8980.com.atmaauto.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.CustomerData;
import p3l_8980.com.atmaauto.Controller.Motorcycle;
import p3l_8980.com.atmaauto.Controller.MotorcycleBrand;
import p3l_8980.com.atmaauto.Controller.MotorcycleBrandList;
import p3l_8980.com.atmaauto.Controller.MotorcycleData;
import p3l_8980.com.atmaauto.Controller.MotorcycleType;
import p3l_8980.com.atmaauto.Controller.MotorcycleTypeList;
import p3l_8980.com.atmaauto.Controller.Sales;
import p3l_8980.com.atmaauto.Controller.SalesList;
import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.Controller.SparepartList;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierList;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddMotorcycle extends AppCompatActivity {

    EditText licenseNumber;
    Button addBtn;
    Spinner brand,type;
    int idBrand,idType;
    private int simpan = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_motorcycle);
        init();

        simpan = getIntent().getIntExtra("simpan",-1);

        brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                MotorcycleBrand sp = (MotorcycleBrand) parentView.getSelectedItem();
                idBrand = sp.getIdMotorcycleBrand();

                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://p3l.yafetrakan.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient2 = retrofit.create(ApiClient.class);
                Call<MotorcycleTypeList> call2 = apiClient2.getMotorcycleType();
                call2.enqueue(new Callback<MotorcycleTypeList>() {
                    @Override
                    public void onResponse(Call<MotorcycleTypeList> call, Response<MotorcycleTypeList> response) {
                        ArrayList<MotorcycleType> types = new ArrayList<>();
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            if(response.body().getData().get(i).getIdMotorcycleBrand()==idBrand) {
                                types.add(new MotorcycleType(response.body().getData().get(i).getIdMotorcycleType(), response.body().getData().get(i).getMotorcycleTypeName()));
                            }
                        }

                        ArrayAdapter<MotorcycleType> adapter = new ArrayAdapter<MotorcycleType>(getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item
                                , types);

                        type.setAdapter(adapter);
//                Log.d("items",sparepart.getItemAtPosition(0).toString());
//                if(simpan > -1) {
//                    spinnerType.setSelection(getIndex(spinnerType, getIntent().getStringExtra("type")));
//                }

                    }

                    @Override
                    public void onFailure(Call<MotorcycleTypeList> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Data Dropdown gagal diambil", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                MotorcycleType sp = (MotorcycleType) parentView.getSelectedItem();
                idType = sp.getIdMotorcycleType();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if(simpan > -1){
//            addButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    update();
//                }
//            });
        }else{
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    store();
                }
            });
        }
    }

    public void store(){
        if(licenseNumber.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "plat nomor tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                final ProgressDialog progressDialog = new ProgressDialog(AddMotorcycle.this, R.style.AppTheme_Dark_Dialog);
                progressDialog.setMessage("Memproses Data...");
                progressDialog.show();
                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://p3l.yafetrakan.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                Call<MotorcycleData> addMotorcycle = apiClient.addMotorcycle(licenseNumber.getText().toString(), idType, getIntent().getIntExtra("id",0));

                addMotorcycle.enqueue(new Callback<MotorcycleData>() {
                    @Override
                    public void onResponse(Call<MotorcycleData> call, Response<MotorcycleData> response) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Berhasil Input Data", Toast.LENGTH_SHORT).show();
                        final Intent intent = new Intent(AddMotorcycle.this, MotorData.class);
                        intent.putExtra("id",getIntent().getIntExtra("id",0));
                        intent.putExtra("name",getIntent().getStringExtra("name"));
//                        intent.putExtra("addDialog", 4);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<MotorcycleData> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Gagal Input Data", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void init(){
        licenseNumber = findViewById(R.id.licenseNumber);
        brand = findViewById(R.id.brandMotor);
        type = findViewById(R.id.typeMotor);
        addBtn = findViewById(R.id.btnAdd);

        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://p3l.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);
        Call<MotorcycleBrandList> call = apiClient.getMotorcycleBrand();
        call.enqueue(new Callback<MotorcycleBrandList>() {
            @Override
            public void onResponse(Call<MotorcycleBrandList> call, Response<MotorcycleBrandList> response) {
                ArrayList<MotorcycleBrand> brands = new ArrayList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
                    brands.add(new MotorcycleBrand(response.body().getData().get(i).getIdMotorcycleBrand(),response.body().getData().get(i).getMotorcycleBrandName()));
                }

                ArrayAdapter<MotorcycleBrand> adapter = new ArrayAdapter<MotorcycleBrand>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item
                        , brands);

                brand.setAdapter(adapter);
//                Log.d("items",supplier.getItemAtPosition(0).toString());
//                if(simpan > -1) {
//                    supplier.setSelection(getIndex(supplier, getIntent().getStringExtra("supplier")));
//                }

            }

            @Override
            public void onFailure(Call<MotorcycleBrandList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Data Dropdown gagal diambil", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
