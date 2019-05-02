package p3l_8980.com.atmaauto.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Procurement;
import p3l_8980.com.atmaauto.Controller.ProcurementList;
import p3l_8980.com.atmaauto.Controller.Supplier;
import p3l_8980.com.atmaauto.Controller.SupplierList;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterProcurement extends RecyclerView.Adapter<AdapterProcurement.MyViewHolder>  {
    private ProcurementList ProcurementBundle;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Tanggal, Sales, Status;
        public LinearLayout topWraper;
        public LinearLayout bottomWraper;

        public MyViewHolder(View v) {
            super(v);
            Tanggal = v.findViewById(R.id.frgtDate);
            Sales = v.findViewById(R.id.frgtSales);
            Status = v.findViewById(R.id.frgtStatus);
            bottomWraper = v.findViewById(R.id.bottom_wrapper);
            topWraper = v.findViewById(R.id.top_wrapper);
        }
    }

    public AdapterProcurement(ProcurementList ProcurementBundle , Context context) {
        this.ProcurementBundle = ProcurementBundle;
        this.context = context;
    }

    public void setFilter(List<Procurement> newList){
        ProcurementBundle = new ProcurementList();
        ProcurementBundle.getData().addAll(newList);
        notifyDataSetChanged();

        int i;
        for (i=0; i<ProcurementBundle.getData().size(); i++)
        {
            Log.d("procurementbundle",ProcurementBundle.getData().get(i).getDate());
        }
    }

    @NonNull
    @Override
    public AdapterProcurement.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycleprocurement, viewGroup, false);
        return new AdapterProcurement.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterProcurement.MyViewHolder vh, final int i) {

        final Procurement data = ProcurementBundle.getData().get(i);
        final int ifinal = vh.getAdapterPosition();
        vh.Tanggal.setText(data.getDate());
        vh.Sales.setText(data.getSales());
        if(data.getProcurementStatus().equalsIgnoreCase("On Progress")){
            vh.Status.setBackgroundColor(Color.parseColor("#f6d82d"));
        }
        else if(data.getProcurementStatus().equalsIgnoreCase("Unprocessed"))
        {
            vh.Status.setBackgroundColor(Color.parseColor("#f62d30"));
        }
        else if(data.getProcurementStatus().equalsIgnoreCase("Finish"))
        {
            vh.Status.setBackgroundColor(Color.parseColor("#45f62d"));
        }
        final String[] parts = data.getDate().split(" ");
        vh.topWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.getProcurementStatus().equalsIgnoreCase("On Progress")){
                    Intent intent = new Intent(context, VerificationProcurement.class);
                    Log.d("zz",String.valueOf(data.getIdSales()));
                    intent.putExtra("simpan", i);
                    intent.putExtra("date", parts[0]);
                    intent.putExtra("id_sales", data.getIdSales());
                    intent.putExtra("sales", data.getSales());
                    intent.putExtra("id_supplier", data.getIdSupplier());
                    intent.putExtra("supplier", data.getSupplier());
                    intent.putExtra("procurement_status", data.getProcurementStatus());
                    intent.putExtra("id_procurement", data.getIdProcurement());
                    context.startActivity(intent);
                }
//                else if(data.getProcurementStatus().equalsIgnoreCase("Finish")){
//                    Toast.makeText(context.getApplicationContext(), "Data Pengadaan tidak dapat diubah", Toast.LENGTH_SHORT).show();
//                }
                else
                {
                    Intent intent = new Intent(context, AddProcurement.class);
                    intent.putExtra("simpan", i);
                    intent.putExtra("date", parts[0]);
                    intent.putExtra("id_sales", data.getIdSales());
                    intent.putExtra("sales", data.getSales());
                    intent.putExtra("id_supplier", data.getIdSupplier());
                    intent.putExtra("supplier", data.getSupplier());
                    intent.putExtra("procurement_status", data.getProcurementStatus());
                    intent.putExtra("id_procurement", data.getIdProcurement());
                    context.startActivity(intent);
                }
            }
        });
        vh.Status.setText(data.getProcurementStatus());
        vh.bottomWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.getProcurementStatus().equalsIgnoreCase("Unprocessed")) {
                    Retrofit retrofit = new retrofit2.Retrofit.Builder()
                            .baseUrl("https://p3l.yafetrakan.com/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiClient apiClient = retrofit.create(ApiClient.class);

                    Call<ResponseBody> deleteProcurement = apiClient.deleteProcurement(ProcurementBundle.getData().get(i).getIdProcurement());
                    deleteProcurement.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.code() == 200) {
//                          notifyItemRemoved(vh.getAdapterPosition());
//                          notifyItemRangeChanged(vh.getAdapterPosition(), SupplierBundle.getData().size());
                                Toast.makeText(context.getApplicationContext(), "Berhasil Membatalkan Pengadaan", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context.getApplicationContext(), "Gagal hapus data pengguna", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(context.getApplicationContext(), "Gagal hapus data", Toast.LENGTH_SHORT).show();
                        }
                    });

                    ProcurementBundle.getData().remove(ifinal);
                    notifyItemRemoved(ifinal);
                    notifyItemRangeChanged(ifinal, getItemCount());
                }
                else{
                    Toast.makeText(context.getApplicationContext(), "Tidak dapat membatalkan transaksi", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return ProcurementBundle.getData().size();
    }
}
