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
import p3l_8980.com.atmaauto.Controller.History;
import p3l_8980.com.atmaauto.Controller.HistoryList;
import p3l_8980.com.atmaauto.Controller.Transaction;
import p3l_8980.com.atmaauto.Controller.TransactionList;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterHistory  extends RecyclerView.Adapter<AdapterHistory.MyViewHolder>  {
    private HistoryList HistoryBundle;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView idTransaction, paid, status;
        public LinearLayout topWraper;
        public LinearLayout bottomWraper;

        public MyViewHolder(View v) {
            super(v);
            idTransaction = v.findViewById(R.id.frgtIdTransaction);
            paid = v.findViewById(R.id.frgtPaidStatus);
            status = v.findViewById(R.id.frgtStatusTransaction);
            bottomWraper = v.findViewById(R.id.bottom_wrapper);
            topWraper = v.findViewById(R.id.top_wrapper);
        }
    }

    public AdapterHistory(HistoryList HistoryBundle , Context context) {
        this.HistoryBundle = HistoryBundle;
        this.context = context;
    }

    public AdapterHistory(HistoryList HistoryBundle) {
        this.HistoryBundle = HistoryBundle;
    }

    @NonNull
    @Override
    public AdapterHistory.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclehistory, viewGroup, false);
        return new AdapterHistory.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterHistory.MyViewHolder vh, final int i) {

        final History data = HistoryBundle.getData().get(i);
        final int ifinal = vh.getAdapterPosition();
        vh.idTransaction.setText(data.getIdTransaction());
        vh.paid.setText(data.getPayment());
        vh.status.setText(data.getStatus());
        if(data.getStatus().equalsIgnoreCase("on Progress")){
            vh.status.setBackgroundColor(Color.parseColor("#f6d82d"));
        }
        else if(data.getStatus().equalsIgnoreCase("unprocessed"))
        {
            vh.status.setBackgroundColor(Color.parseColor("#f62d30"));
        }
        else if(data.getStatus().equalsIgnoreCase("finish"))
        {
            vh.status.setBackgroundColor(Color.parseColor("#45f62d"));
        }
        vh.topWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, AddTransaction.class);
//                intent.putExtra("simpan", i);
//                intent.putExtra("name", data.getCustomerName());
//                intent.putExtra("status", data.getTransactionStatus());
//                intent.putExtra("type", data.getTransactionType());
//                intent.putExtra("id_transaction", data.getIdTransaction());
//                intent.putExtra("id", data.getIdCustomer());
//                context.startActivity(intent);
            }
        });
        vh.bottomWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(data.getTransactionStatus().equalsIgnoreCase("Unprocessed")) {
//                    Retrofit retrofit = new retrofit2.Retrofit.Builder()
//                            .baseUrl("https://p3l.yafetrakan.com/api/")
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .build();
//
//                    ApiClient apiClient = retrofit.create(ApiClient.class);
//
//                    Call<ResponseBody> deleteTransaction = apiClient.deleteTransaction(TransactionBundle.getData().get(i).getIdTransaction());
//                    deleteTransaction.enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            if (response.code() == 200) {
////                          notifyItemRemoved(vh.getAdapterPosition());
////                          notifyItemRangeChanged(vh.getAdapterPosition(), SupplierBundle.getData().size());
//                                Toast.makeText(context.getApplicationContext(), "Berhasil hapus data Supplier", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(context.getApplicationContext(), "Gagal hapus data pengguna", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            Toast.makeText(context.getApplicationContext(), "Gagal hapus data pengguna", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                    TransactionBundle.getData().remove(ifinal);
//                    notifyItemRemoved(ifinal);
//                    notifyItemRangeChanged(ifinal, getItemCount());
//                }
//                else
//                {
//                    Toast.makeText(context.getApplicationContext(), "Tidak dapat membatalkan transaksi", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return HistoryBundle.getData().size();
    }
}
