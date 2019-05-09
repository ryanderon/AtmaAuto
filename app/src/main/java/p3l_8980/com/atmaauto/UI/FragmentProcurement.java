package p3l_8980.com.atmaauto.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Procurement;
import p3l_8980.com.atmaauto.Controller.ProcurementList;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierList;
import p3l_8980.com.atmaauto.R;
import p3l_8980.com.atmaauto.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentProcurement extends Fragment {
    View v;
    private RecyclerView rview;
    private AdapterProcurement adapter;
    private RecyclerView.LayoutManager layout;
    private List<ProcurementList> procurementList;
    private AdapterProcurement adapterProcurement;
    private List<Procurement> ProcurementBundleFull;
    private AdapterProcurement procurementAdapter;
    private ProcurementList procurementList1;


    SessionManager session;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchProcurement = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchProcurement.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                List<Procurement> newList = new ArrayList<>();
                //Sparepart newList = new Sparepart();

                for (Procurement procurement : ProcurementBundleFull)
                {
                    String tanggal = procurement.getDate().toLowerCase();
                    String sales = procurement.getSales().toLowerCase();
                    String status = procurement.getProcurementStatus().toLowerCase();
                    Log.d("procurementlower",procurement.getDate().toLowerCase());
                    if(tanggal.contains(newText) || sales.contains(newText) || status.contains(newText))
                        newList.add(procurement);
                }
                adapter.setFilter(newList);

                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frgprocurement,container,false);
        rview = v.findViewById(R.id.procurement_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getContext());
        rview.setLayoutManager(layout);

        procurementList = new ArrayList<>();
        procurementAdapter = new AdapterProcurement(procurementList1,this.getContext());

        session = new SessionManager(getContext());
        session.checkLogin();

        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("https://p3l.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Call<ProcurementList> procurementGet = apiClient.getProcurement();

        procurementGet.enqueue(new Callback<ProcurementList>() {
            @Override
            public void onResponse(Call<ProcurementList> call, Response<ProcurementList> response) {
                try {
                    adapter = new AdapterProcurement(response.body(),getContext());
                    ProcurementBundleFull =  response.body().getData();

                    rview.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Tidak Ada Procurement!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProcurementList> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }

        });


        return v;
    }
}
