package p3l_8980.com.atmaauto.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Customer;
import p3l_8980.com.atmaauto.Controller.CustomerList;
import p3l_8980.com.atmaauto.Controller.Detail;
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

public class CustomerOld extends AppCompatActivity {

    RecyclerView rview;
    private AdapterCustomer adapter;
    private SearchView search;
    private RecyclerView.LayoutManager layout;
    private ImageView backButton;
    private List<Customer> CustomerBundleFull;
    private List<ProcurementDetail> details = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_old);
        init();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        Call<CustomerList> customerGet = apiClient.getCustomer();

        customerGet.enqueue(new Callback<CustomerList>() {
            @Override
            public void onResponse(Call<CustomerList> call, Response<CustomerList> response) {
                try {
                    adapter = new AdapterCustomer(response.body().getData(),CustomerOld.this);
                    CustomerBundleFull =  response.body().getData();

                    rview.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Tidak Ada Customer!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void init(){
        rview = findViewById(R.id.procurement_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layout);
        search = findViewById(R.id.searchCustomer);
        backButton = findViewById(R.id.btnBack);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerOld.this, MenuCustomer.class);
                startActivity(intent);
            }
        });

    }
}
