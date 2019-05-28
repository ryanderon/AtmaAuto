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
import p3l_8980.com.atmaauto.Controller.Customer;
import p3l_8980.com.atmaauto.Controller.CustomerList;
import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierList;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdapterBeranda extends RecyclerView.Adapter<AdapterBeranda.MyViewHolder> {

    private CustomerList CustomerBundle;
    private Context context;

//    @Override
//    public Filter getFilter() {
//        return supplierFilter;
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Nama, Address, Phone;
        public LinearLayout topWraper;
        public LinearLayout bottomWraper;

        public MyViewHolder(View v) {
            super(v);
            Nama = v.findViewById(R.id.frgtNamaCustomer);
            Address = v.findViewById(R.id.frgtAddressCustomer);
            Phone = v.findViewById(R.id.frgtPhoneCustomer);
            bottomWraper = v.findViewById(R.id.bottom_wrapper);
            topWraper = v.findViewById(R.id.top_wrapper);
        }
    }

    public AdapterBeranda(CustomerList CustomerBundle , Context context) {
        this.CustomerBundle = CustomerBundle;
        this.context = context;
    }

    public void setFilter(List<Customer> newList){
        CustomerBundle = new CustomerList();
        CustomerBundle.getData().addAll(newList);
        notifyDataSetChanged();

        int i;
        for (i=0; i<CustomerBundle.getData().size(); i++)
        {
            Log.d("customerbundle",CustomerBundle.getData().get(i).getCustomerName());
        }
    }


    @NonNull
    @Override
    public AdapterBeranda.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclecustomer, viewGroup, false);
        return new AdapterBeranda.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterBeranda.MyViewHolder vh, final int i) {

        final Customer data = CustomerBundle.getData().get(i);
        final int ifinal = vh.getAdapterPosition();
        vh.Nama.setText(data.getCustomerName());
        vh.Address.setText(data.getCustomerAddress());
        vh.Phone.setText(data.getCustomerPhoneNumber());
        vh.topWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddSupplier.class);
                intent.putExtra("simpan", i);
                intent.putExtra("name", data.getCustomerName());
                intent.putExtra("address", data.getCustomerAddress());
                intent.putExtra("number", data.getCustomerPhoneNumber());
                intent.putExtra("id", data.getIdCustomer());
                context.startActivity(intent);
            }
        });
        vh.bottomWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("http://192.168.19.140/8991/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                Call<ResponseBody> deleteCustomer = apiClient.deleteCustomer(CustomerBundle.getData().get(i).getIdCustomer());
                deleteCustomer.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 200){
//                          notifyItemRemoved(vh.getAdapterPosition());
//                          notifyItemRangeChanged(vh.getAdapterPosition(), SupplierBundle.getData().size());
                            Toast.makeText(context.getApplicationContext(), "Berhasil hapus data Customer", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context.getApplicationContext(), "Gagal hapus data pengguna", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context.getApplicationContext(), "Gagal hapus data pengguna", Toast.LENGTH_SHORT).show();
                    }
                });



                CustomerBundle.getData().remove(ifinal);
                notifyItemRemoved(ifinal);
                notifyItemRangeChanged(ifinal, getItemCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return CustomerBundle.getData().size();
    }

}


