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
import p3l_8980.com.atmaauto.Controller.Customer;
import p3l_8980.com.atmaauto.Controller.CustomerList;
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

public class FragmentCustomer extends Fragment {

    View v;
    private RecyclerView rview;
    private AdapterCustomer adapter;
    private RecyclerView.LayoutManager layout;
    private List<CustomerList> customerLists;
    private AdapterCustomer adapterCustomer;
    private List<Customer> CustomerBundleFull;
    private AdapterCustomer customerAdapter;
    private CustomerList customerList1;

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

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frgcustomer,container,false);
        rview = v.findViewById(R.id.customer_list);
        rview.setHasFixedSize(true);
        layout = new LinearLayoutManager(getContext());
        rview.setLayoutManager(layout);

        customerLists = new ArrayList<>();
        customerAdapter = new AdapterCustomer(customerList1.getData(),this.getContext());

        session = new SessionManager(getContext());
        session.checkLogin();

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
                    adapter = new AdapterCustomer(response.body().getData(),getContext());
                    CustomerBundleFull =  response.body().getData();

                    rview.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Tidak Ada Customer!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerList> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }

        });


        return v;
    }
}
