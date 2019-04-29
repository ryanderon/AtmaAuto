package p3l_8980.com.atmaauto.UI;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierList;
import p3l_8980.com.atmaauto.R;
import p3l_8980.com.atmaauto.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentSupplier extends Fragment{
    View v;
    private RecyclerView rview;
    private AdapterSupplier adapter;
    private RecyclerView.LayoutManager layout;
    private List<SupplierList> supplierList;
    private AdapterSupplier adapterSupplier;
    private List<Supplier> SupplierBundleFull;
    private AdapterSupplier supplierAdapter;
    private SupplierList supplierList1;
    SessionManager session;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }



    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchSupplier = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchSupplier.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                List<Supplier> newList = new ArrayList<>();
                //Sparepart newList = new Sparepart();

                for (Supplier supplier : SupplierBundleFull)
                {
                    String alamat = supplier.getSupplierAddress().toLowerCase();

                    String name = supplier.getSupplierName().toLowerCase();
                    Log.d("supplierlower",supplier.getSupplierName().toLowerCase());
                    if(name.contains(newText) || alamat.contains(newText))
                        newList.add(supplier);
                }
                adapter.setFilter(newList);

                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frgsupplier,container,false);
        rview = v.findViewById(R.id.supplier_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getContext());
        rview.setLayoutManager(layout);

        supplierList = new ArrayList<>();
        supplierAdapter = new AdapterSupplier(supplierList1,this.getContext());

        session = new SessionManager(getContext());
        session.checkLogin();


        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("https://p3l.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Call<SupplierList> supplierGet = apiClient.getSuppliers();

        supplierGet.enqueue(new Callback<SupplierList>() {
            @Override
            public void onResponse(Call<SupplierList> call, Response<SupplierList> response) {
                try {
                    adapter = new AdapterSupplier(response.body(),getContext());
                    SupplierBundleFull =  response.body().getData();
 //                   adapter.notifyDataSetChanged();
//                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//                    rview.setLayoutManager(mLayoutManager);
//                    rview.setItemAnimator(new DefaultItemAnimator());
                    rview.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Tidak Ada Supplier!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SupplierList> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }

        });


        return v;
    }

}
