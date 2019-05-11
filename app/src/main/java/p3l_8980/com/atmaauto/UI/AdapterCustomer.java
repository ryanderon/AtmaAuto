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
import java.util.Locale;

public class AdapterCustomer extends RecyclerView.Adapter<AdapterCustomer.MyViewHolder> {

    private  List<Customer> CustomerBundle;
    private List<Customer> CustomerFilter;

    private Context context;

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

    public AdapterCustomer(List<Customer> CustomerBundle , Context context) {
        this.CustomerBundle = CustomerBundle;
        this.context = context;
        this.CustomerFilter = CustomerBundle;
    }


    @NonNull
    @Override
    public AdapterCustomer.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclecustomer, viewGroup, false);
        return new MyViewHolder(v);
    }



    public void filter(String charText) {
        Log.d( "filter: ", charText);

        charText = charText.toLowerCase(Locale.getDefault());
        CustomerFilter.clear();
        if (charText.length() == 0) {
            CustomerFilter.addAll(CustomerBundle);
        }
        else
        {
            for (Customer obj : CustomerBundle) {
                if (obj.getCustomerName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    CustomerFilter.add(obj);
                }
            }
        }
        notifyDataSetChanged();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    CustomerFilter = CustomerBundle;
                } else {
                    List<Customer> filteredList = new ArrayList<>();
                    for (Customer obj : CustomerBundle) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (obj.getCustomerName().toLowerCase().contains(charString.toLowerCase())
                                || obj.getCustomerAddress().toLowerCase().contains(charString.toLowerCase())
                                || obj.getCustomerPhoneNumber().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(obj);
                        }
                    }
                    CustomerFilter = filteredList;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = CustomerFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                CustomerFilter = (ArrayList<Customer>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    @Override
    public void onBindViewHolder(@NonNull  final AdapterCustomer.MyViewHolder vh, final int i) {
        final Customer data = CustomerFilter.get(i);
        final int ifinal = vh.getAdapterPosition();
        vh.Nama.setText(data.getCustomerName());
        vh.Address.setText(data.getCustomerAddress());
        vh.Phone.setText(data.getCustomerPhoneNumber());
        vh.topWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddCustomer.class);
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
                        .baseUrl("https://p3l.yafetrakan.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiClient apiClient = retrofit.create(ApiClient.class);

                Call<ResponseBody> deleteCustomer = apiClient.deleteCustomer(CustomerBundle.get(i).getIdCustomer());
                deleteCustomer.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 200){
//                          notifyItemRemoved(vh.getAdapterPosition());
//                          notifyItemRangeChanged(vh.getAdapterPosition(), SupplierBundle.getData().size());
                            Toast.makeText(context.getApplicationContext(), "Berhasil hapus data Customer", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context.getApplicationContext(), "Gagal hapus data Customer", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context.getApplicationContext(), "Gagal hapus data pengguna", Toast.LENGTH_SHORT).show();
                    }
                });

                CustomerBundle.remove(ifinal);
                notifyItemRemoved(ifinal);
                notifyItemRangeChanged(ifinal, getItemCount());
            }
        });
    }


    @Override
    public int getItemCount() {
        return CustomerFilter.size();
    }

}


