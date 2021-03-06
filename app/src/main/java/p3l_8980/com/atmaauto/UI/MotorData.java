package p3l_8980.com.atmaauto.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.SearchView;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Customer;
import p3l_8980.com.atmaauto.Controller.CustomerList;
import p3l_8980.com.atmaauto.Controller.Motorcycle;
import p3l_8980.com.atmaauto.Controller.MotorcycleList;
import p3l_8980.com.atmaauto.Controller.ProcurementDetail;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MotorData extends AppCompatActivity {

    RecyclerView rview;
    private AdapterMotor adapter;
    private SearchView search;
    private RecyclerView.LayoutManager layout;
    private List<Motorcycle> MotorBundleFull;
    Button btnAddNew,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_data);

        init();

        search.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Log.d("onQueryTextSubmit: ",query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("onQueryTextChange: ","true");
                String text = newText.toLowerCase(Locale.getDefault());
                adapter.getFilter().filter(text);
                return true;
            }
        });

        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("http://192.168.19.140/8991/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Call<MotorcycleList> motorcycleGet = apiClient.getMotorcycle(getIntent().getIntExtra("id",0));

        motorcycleGet.enqueue(new Callback<MotorcycleList>() {
            @Override
            public void onResponse(Call<MotorcycleList> call, Response<MotorcycleList> response) {
                try {
                    adapter = new AdapterMotor(response.body().getData(),MotorData.this);
                    MotorBundleFull =  response.body().getData();

                    rview.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Tidak Ada Motor!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MotorcycleList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }

        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MotorData.this, AddMotorcycle.class);
                intent.putExtra("id",getIntent().getIntExtra("id",0));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MotorData.this, AddTransaction.class);
                intent.putExtra("id",getIntent().getIntExtra("id",0));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                startActivity(intent);
            }
        });
    }

    private void init(){
        rview = findViewById(R.id.motorcycle_list);
        rview.setHasFixedSize(true);
        search = findViewById(R.id.searchMotor);
        layout = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layout);
        btnAddNew = findViewById(R.id.btnAddNew);
        next = findViewById(R.id.btnNext);
    }
}
