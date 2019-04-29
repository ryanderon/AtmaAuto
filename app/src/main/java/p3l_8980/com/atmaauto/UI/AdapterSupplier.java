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

public class AdapterSupplier extends RecyclerView.Adapter<AdapterSupplier.MyViewHolder> {

    private SupplierList SupplierBundle;
    private Context context;

//    @Override
//    public Filter getFilter() {
//        return supplierFilter;
//    }

    public void clear() {
        SupplierBundle = new SupplierList();
        SupplierBundle.getData().clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Supplier> list) {
        SupplierBundle = new SupplierList();
        SupplierBundle.getData().addAll(list);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Nama, Alamat, Nomor;
        public LinearLayout topWraper;
        public LinearLayout bottomWraper;

        public MyViewHolder(View v) {
            super(v);
            Nama = v.findViewById(R.id.frgtNamaSupplier);
            Alamat = v.findViewById(R.id.frgtAddressSupplier);
            Nomor = v.findViewById(R.id.frgtNumber);
            bottomWraper = v.findViewById(R.id.bottom_wrapper);
            topWraper = v.findViewById(R.id.top_wrapper);
        }
    }

    public AdapterSupplier(SupplierList SupplierBundle , Context context) {
        this.SupplierBundle = SupplierBundle;
        this.context = context;
    }

    public void setFilter(List<Supplier> newList){
        SupplierBundle = new SupplierList();
        SupplierBundle.getData().addAll(newList);
        notifyDataSetChanged();

        int i;
        for (i=0; i<SupplierBundle.getData().size(); i++)
        {
        Log.d("supplierbundle",SupplierBundle.getData().get(i).getSupplierName());
        }
    }


    @NonNull
    @Override
    public AdapterSupplier.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclesupplier, viewGroup, false);
        return new AdapterSupplier.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterSupplier.MyViewHolder vh, final int i) {

        final Supplier data = SupplierBundle.getData().get(i);
        final int ifinal = vh.getAdapterPosition();
        vh.Nama.setText(data.getSupplierName());
        vh.Alamat.setText(data.getSupplierAddress());
        vh.Nomor.setText(data.getSupplierPhoneNumber());
        vh.topWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddSupplier.class);
                intent.putExtra("simpan", i);
                intent.putExtra("name", data.getSupplierName());
                intent.putExtra("address", data.getSupplierAddress());
                intent.putExtra("number", data.getSupplierPhoneNumber());
                intent.putExtra("id", data.getIdSupplier());
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

              Call<ResponseBody> deleteSupplier = apiClient.deleteSupplier(SupplierBundle.getData().get(i).getIdSupplier());
              deleteSupplier.enqueue(new Callback<ResponseBody>() {
                  @Override
                  public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                      if (response.code() == 200){
//                          notifyItemRemoved(vh.getAdapterPosition());
//                          notifyItemRangeChanged(vh.getAdapterPosition(), SupplierBundle.getData().size());
                          Toast.makeText(context.getApplicationContext(), "Berhasil hapus data Supplier", Toast.LENGTH_SHORT).show();
                      }else{
                          Toast.makeText(context.getApplicationContext(), "Gagal hapus data pengguna", Toast.LENGTH_SHORT).show();
                      }
                  }

                  @Override
                  public void onFailure(Call<ResponseBody> call, Throwable t) {
                      Toast.makeText(context.getApplicationContext(), "Gagal hapus data pengguna", Toast.LENGTH_SHORT).show();
                  }
              });

                  SupplierBundle.getData().remove(ifinal);
                  notifyItemRemoved(ifinal);
                  notifyItemRangeChanged(ifinal, getItemCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return SupplierBundle.getData().size();
    }

}


