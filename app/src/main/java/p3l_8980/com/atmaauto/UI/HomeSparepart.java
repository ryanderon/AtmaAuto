package p3l_8980.com.atmaauto.UI;

import android.content.Intent;
import okhttp3.ResponseBody;
import retrofit2.Response;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

public class HomeSparepart extends AppCompatActivity {

    View v;
    RecyclerView rview;
    private AdapterSparepart2 adapter;
    private SearchView search;
    private Button sortButton;
    private RecyclerView.LayoutManager layout;
    private List<Sparepart> SparepartBundleFull;
    private ImageView backButton;
    private List<SparepartList> sparepartList;
    private AdapterSparepart2 sparepartAdapter;
    private SparepartList sparepartList1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sparepart);

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
                .baseUrl("https://p3l.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        final Call<SparepartList> sparepartGet = apiClient.getSpareparts();

        sparepartGet.enqueue(new Callback<SparepartList>() {
            @Override
            public void onResponse(Call<SparepartList> call, Response<SparepartList> response) {
                try {
                    adapter = new AdapterSparepart2(response.body().getData(),HomeSparepart.this);
                    SparepartBundleFull = response.body().getData();

                    adapter.notifyDataSetChanged();
//                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//                    rview.setLayoutManager(mLayoutManager);
//                    rview.setItemAnimator(new DefaultItemAnimator());
                    rview.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(HomeSparepart.this, "Tidak Ada Sparepart!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SparepartList> call, Throwable t) {
                Toast.makeText(HomeSparepart.this, "Fail", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void init(){

        rview = findViewById(R.id.sparepart_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(HomeSparepart.this);
        rview.setLayoutManager(new GridLayoutManager(this, 3));
        rview.setAdapter(adapter);
        backButton = findViewById(R.id.btnBack);
        search = findViewById(R.id.searchSparepart);
        sortButton = findViewById(R.id.btnSort);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeSparepart.this, HomePengguna.class);
                startActivity(intent);
            }
        });

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(SparepartBundleFull, new Comparator<Sparepart>() {
                    @Override
                    public int compare(Sparepart o1, Sparepart o2) {
//                        return o1.getSparepartName().toLowerCase().compareTo(o2.getSparepartName().toLowerCase());
//                        return Integer.compare(o1.getStock(), o2.getStock());
                        return Double.compare(o1.getSellPrice(), o2.getSellPrice());
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });
        sparepartList = new ArrayList<>();
//        sparepartAdapter = new AdapterSparepart2(response.body().getData(),HomeSparepart.this);

    }
}
