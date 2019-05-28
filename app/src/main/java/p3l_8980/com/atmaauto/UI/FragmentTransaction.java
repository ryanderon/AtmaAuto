package p3l_8980.com.atmaauto.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Procurement;
import p3l_8980.com.atmaauto.Controller.ProcurementList;
import p3l_8980.com.atmaauto.Controller.SupplierList;
import p3l_8980.com.atmaauto.Controller.Transaction;
import p3l_8980.com.atmaauto.Controller.TransactionList;
import p3l_8980.com.atmaauto.R;
import p3l_8980.com.atmaauto.Session.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentTransaction extends Fragment {

    View v;
    private RecyclerView rview;
    private AdapterTransaction adapter;
    private RecyclerView.LayoutManager layout;
    private List<TransactionList> transactionLists;
    private AdapterTransaction adapterTransaction;
    private List<Transaction> TransactionBundleFull;
    private AdapterTransaction transactionAdapter;
    private TransactionList transactionList1;

    Intent i;

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
                List<Transaction> newList = new ArrayList<>();
                //Sparepart newList = new Sparepart();

                for (Transaction transaction : TransactionBundleFull)
                {
                    String idTransaction = transaction.getIdTransaction().toLowerCase();
                    String customer = transaction.getCustomerName().toLowerCase();
                    String status = transaction.getTransactionStatus().toLowerCase();
                    Log.d("transactionlower",transaction.getIdTransaction().toLowerCase());
                    if(idTransaction.contains(newText) || customer.contains(newText) || status.contains(newText))
                        newList.add(transaction);
                }
                adapter.setFilter(newList);

                return false;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frgtranscation,container,false);
        rview = v.findViewById(R.id.transaction_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getContext());
        rview.setLayoutManager(layout);

        transactionLists = new ArrayList<>();
        transactionAdapter = new AdapterTransaction(transactionList1,this.getContext());

        session = new SessionManager(getContext());
        session.checkLogin();

        Retrofit retrofit= new retrofit2.Retrofit.Builder()
                .baseUrl("http://192.168.19.140/8991/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient apiClient = retrofit.create(ApiClient.class);

        Call<TransactionList> transactionGet = apiClient.getTransaction();

        transactionGet.enqueue(new Callback<TransactionList>() {
            @Override
            public void onResponse(Call<TransactionList> call, Response<TransactionList> response) {
                try {
                    adapter = new AdapterTransaction(response.body(),getContext());
                    TransactionBundleFull =  response.body().getData();

                    rview.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Tidak Ada Transaksi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TransactionList> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }

        });


        return v;
    }



}
