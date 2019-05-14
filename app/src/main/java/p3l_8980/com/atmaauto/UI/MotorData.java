package p3l_8980.com.atmaauto.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    private RecyclerView.LayoutManager layout;
    private List<Motorcycle> MotorBundleFull;
    Button btnAddNew,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_data);

        init();

        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("https://p3l.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Call<MotorcycleList> motorcycleGet = apiClient.getMotorcycle(getIntent().getIntExtra("id",0));

        motorcycleGet.enqueue(new Callback<MotorcycleList>() {
            @Override
            public void onResponse(Call<MotorcycleList> call, Response<MotorcycleList> response) {
                try {
                    adapter = new AdapterMotor(response.body(),MotorData.this);
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
        layout = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layout);
        btnAddNew = findViewById(R.id.btnAddNew);
        next = findViewById(R.id.btnNext);
    }
}
