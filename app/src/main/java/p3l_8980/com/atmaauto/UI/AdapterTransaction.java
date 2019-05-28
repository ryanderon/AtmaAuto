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
import p3l_8980.com.atmaauto.Controller.Transaction;
import p3l_8980.com.atmaauto.Controller.TransactionList;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterTransaction extends RecyclerView.Adapter<AdapterTransaction.MyViewHolder> {
    private TransactionList TransactionBundle;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView idTransaction, customerName, transactionStatus;
        public LinearLayout topWraper;
        public LinearLayout bottomWraper;

        public MyViewHolder(View v) {
            super(v);
            idTransaction = v.findViewById(R.id.frgtIdTransaction);
            customerName = v.findViewById(R.id.frgtCustomer);
            transactionStatus = v.findViewById(R.id.frgtStatusTransaction);
            bottomWraper = v.findViewById(R.id.bottom_wrapper);
            topWraper = v.findViewById(R.id.top_wrapper);
        }
    }

    public AdapterTransaction(TransactionList TransactionBundle , Context context) {
        this.TransactionBundle = TransactionBundle;
        this.context = context;
    }

    public void setFilter(List<Transaction> newList){
        TransactionBundle = new TransactionList();
        TransactionBundle.getData().addAll(newList);
        notifyDataSetChanged();

        int i;
        for (i=0; i<TransactionBundle.getData().size(); i++)
        {
            Log.d("procurementbundle",TransactionBundle.getData().get(i).getIdTransaction());
        }
    }

    @NonNull
    @Override
    public AdapterTransaction.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycletransaction, viewGroup, false);
        return new AdapterTransaction.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterTransaction.MyViewHolder vh, final int i) {

        final Transaction data = TransactionBundle.getData().get(i);
        final int ifinal = vh.getAdapterPosition();
        vh.idTransaction.setText(data.getIdTransaction());
        vh.customerName.setText(data.getCustomerName());
        vh.transactionStatus.setText(data.getTransactionStatus());
        if(data.getTransactionStatus().equalsIgnoreCase("on Progress")){
            vh.transactionStatus.setBackgroundColor(Color.parseColor("#f6d82d"));
        }
        else if(data.getTransactionStatus().equalsIgnoreCase("unprocessed"))
        {
            vh.transactionStatus.setBackgroundColor(Color.parseColor("#f62d30"));
        }
        else if(data.getTransactionStatus().equalsIgnoreCase("finish"))
        {
            vh.transactionStatus.setBackgroundColor(Color.parseColor("#45f62d"));
        }
        vh.topWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddTransaction.class);
                intent.putExtra("simpan", i);
                intent.putExtra("name", data.getCustomerName());
                intent.putExtra("status", data.getTransactionStatus());
                intent.putExtra("type", data.getTransactionType());
                intent.putExtra("id_transaction", data.getIdTransaction());
                intent.putExtra("id", data.getIdCustomer());
                context.startActivity(intent);
            }
        });
        vh.bottomWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.getTransactionStatus().equalsIgnoreCase("Unprocessed")) {
                    Retrofit retrofit = new retrofit2.Retrofit.Builder()
                            .baseUrl("http://192.168.19.140/8991/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiClient apiClient = retrofit.create(ApiClient.class);

                    Call<ResponseBody> deleteTransaction = apiClient.deleteTransaction(TransactionBundle.getData().get(i).getIdTransaction());
                    deleteTransaction.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.code() == 200) {
//                          notifyItemRemoved(vh.getAdapterPosition());
//                          notifyItemRangeChanged(vh.getAdapterPosition(), SupplierBundle.getData().size());
                                Toast.makeText(context.getApplicationContext(), "Berhasil hapus data Supplier", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context.getApplicationContext(), "Gagal hapus data pengguna", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(context.getApplicationContext(), "Gagal hapus data pengguna", Toast.LENGTH_SHORT).show();
                        }
                    });

                    TransactionBundle.getData().remove(ifinal);
                    notifyItemRemoved(ifinal);
                    notifyItemRangeChanged(ifinal, getItemCount());
                }
                else
                {
                    Toast.makeText(context.getApplicationContext(), "Tidak dapat membatalkan transaksi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return TransactionBundle.getData().size();
    }

}
