package p3l_8980.com.atmaauto.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.Controller.SparepartList;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierList;
import p3l_8980.com.atmaauto.R;
import p3l_8980.com.atmaauto.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentSparepart extends Fragment {

    View v;
    private RecyclerView rview;
    private AdapterSparepart adapter;
    private RecyclerView.LayoutManager layout;
    private List<SparepartList> sparepartList;
    private AdapterSparepart adapterSparepart;
    private List<Sparepart> SparepartBundleFull;
    private AdapterSparepart sparepartAdapter;
    private SparepartList sparepartList1;


    SessionManager session;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchSparepart = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchSparepart.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                List<Sparepart> newList = new ArrayList<>();

                for (Sparepart sparepart : SparepartBundleFull)
                {
                    String name = sparepart.getSparepartName().toLowerCase();
                    Log.d("supplierlower",sparepart.getSparepartName().toLowerCase());
                    if(name.contains(newText))
                        newList.add(sparepart);
                }
                adapter.setFilter(newList);

                return false;
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgsparepart,container,false);
        rview = view.findViewById(R.id.sparepart_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getContext());
        rview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rview.setAdapter(adapter);

        sparepartList = new ArrayList<>();
        sparepartAdapter = new AdapterSparepart(sparepartList1,this.getContext());

        session = new SessionManager(getContext());
        session.checkLogin();

        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("https://p3l.yafetrakan.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Call<SparepartList> sparepartGet = apiClient.getSpareparts();

        sparepartGet.enqueue(new Callback<SparepartList>() {
            @Override
            public void onResponse(Call<SparepartList> call, Response<SparepartList> response) {
                try {
                    adapter = new AdapterSparepart(response.body(),getContext());
                    SparepartBundleFull = response.body().getData();
                    adapter.notifyDataSetChanged();
//                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//                    rview.setLayoutManager(mLayoutManager);
//                    rview.setItemAnimator(new DefaultItemAnimator());
                    rview.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Tidak Ada Sparepart!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SparepartList> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }

        });

        return view;
    }

}
