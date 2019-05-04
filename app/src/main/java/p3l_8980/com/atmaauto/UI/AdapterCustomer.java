package p3l_8980.com.atmaauto.UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import okhttp3.ResponseBody;
import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierList;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class AdapterCustomer extends RecyclerView.Adapter<AdapterCustomer.MyViewHolder> {

    private SupplierList SupplierBundle;
    private Context context;


//    @Override
//    public Filter getFilter() {
//        return supplierFilter;
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Nama, Plat, Nomor;
        public LinearLayout topWraper;
        public LinearLayout bottomWraper;

        public MyViewHolder(View v) {
            super(v);
            Nama = v.findViewById(R.id.frgtNamaCustomer);
            Plat = v.findViewById(R.id.frgtPlatNumber);
            Nomor = v.findViewById(R.id.frgtCustomerPhone);
            bottomWraper = v.findViewById(R.id.bottom_wrapper);
            topWraper = v.findViewById(R.id.top_wrapper);
            topWraper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddTransaction.class);
                }
            });
        }
    }

    @NonNull
    @Override
    public AdapterCustomer.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclesupplier, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  final AdapterCustomer.MyViewHolder vh, int i) {

    }


    @Override
    public int getItemCount() {
        return SupplierBundle.getData().size();
    }

}


