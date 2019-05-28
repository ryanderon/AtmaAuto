package p3l_8980.com.atmaauto.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Detail;
import p3l_8980.com.atmaauto.Controller.History;
import p3l_8980.com.atmaauto.Controller.HistoryList;
import p3l_8980.com.atmaauto.Controller.ProcurementDetail;
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

public class HistoryTransaction extends AppCompatActivity {

    Button homeButton;
    RecyclerView rview;
    private AdapterHistory adapter;
    private RecyclerView.LayoutManager layout;
    private List<History> HistoryBundleFull;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_transaction);
        init();

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryTransaction.this, HomePengguna.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("http://192.168.19.140/8991/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Call<HistoryList> getHistory = apiClient.checkMotor(getIntent().getStringExtra("license_number"),getIntent().getStringExtra("phone_number"));

        getHistory.enqueue(new Callback<HistoryList>() {
            @Override
            public void onResponse(Call<HistoryList> call, Response<HistoryList> response) {
                try {
                    adapter = new AdapterHistory(response.body());
                    HistoryBundleFull =  response.body().getData();

                    rview.setAdapter(adapter);
                } catch (Exception e) {
                    Intent intent = new Intent(HistoryTransaction.this, CheckStatus.class);
                    Toast.makeText(HistoryTransaction.this, "Data Tidak ditemukan2!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<HistoryList> call, Throwable t) {
                Toast.makeText(HistoryTransaction.this, "Fail", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void init(){
        rview = findViewById(R.id.motorcycleHistory_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layout);
        homeButton = findViewById(R.id.btnHome);
    }
}
