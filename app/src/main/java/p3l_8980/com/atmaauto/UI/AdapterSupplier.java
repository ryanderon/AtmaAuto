package p3l_8980.com.atmaauto.UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import okhttp3.ResponseBody;
import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdapterSupplier extends RecyclerView.Adapter<AdapterSupplier.MyViewHolder> {

    private List<Supplier> SupplierBundle = new ArrayList<>();
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
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

    public AdapterSupplier(List<Supplier> SupplierBundle , Context context) {
        this.SupplierBundle = SupplierBundle;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterSupplier.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = (View) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclesupplier, viewGroup, false);
        return new AdapterSupplier.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSupplier.MyViewHolder vh, final int i) {

        final Supplier data = SupplierBundle.get(i);
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

              Call<ResponseBody> deleteSupplier = apiClient.deleteSupplier(data.getIdSupplier());
              deleteSupplier.enqueue(new Callback<ResponseBody>() {
                  @Override
                  public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                      if (response.code() == 200){
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

                  SupplierBundle.remove(ifinal);
                  notifyItemRemoved(ifinal);
                  notifyItemRangeChanged(ifinal, getItemCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return SupplierBundle.size();
    }


}
