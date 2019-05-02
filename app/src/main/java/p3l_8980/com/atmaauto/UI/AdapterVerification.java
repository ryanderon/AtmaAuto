package p3l_8980.com.atmaauto.UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import p3l_8980.com.atmaauto.Controller.Detail;
import p3l_8980.com.atmaauto.Controller.ProcurementDetail;
import p3l_8980.com.atmaauto.R;

public class AdapterVerification extends RecyclerView.Adapter<AdapterVerification.MyViewHolder>   {
    private Context context;
    private p3l_8980.com.atmaauto.Controller.Detail DetailBundle;
    private List<ProcurementDetail> Detail;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sparepart_name, amount, price,sell_price;
        public LinearLayout topWraper;
        public LinearLayout bottomWraper;

        public MyViewHolder(View v) {
            super(v);
            sparepart_name = v.findViewById(R.id.frgtNamaSparepart);
            amount = v.findViewById(R.id.frgtAmount);
            price = v.findViewById(R.id.frgtPrice);
            sell_price = v.findViewById(R.id.frgtSellPrice);
            bottomWraper = v.findViewById(R.id.bottom_wrapper);
            topWraper = v.findViewById(R.id.top_wrapper);
        }
    }

    public AdapterVerification(List<ProcurementDetail> DetailBundle, Context context) {
        this.Detail = DetailBundle;
        this.context = context;
    }

    public AdapterVerification(List<ProcurementDetail> DetailBundle) {
        this.Detail= DetailBundle;
    }

    @NonNull
    @Override
    public AdapterVerification.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycleverification, viewGroup, false);
        return new AdapterVerification.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterVerification.MyViewHolder vh, final int i) {
        final ProcurementDetail data = Detail.get(i);
        final int ifinal = vh.getAdapterPosition();
        vh.sparepart_name.setText(data.getSparepartName());
        vh.amount.setText("Jumlah : ".concat(String.valueOf(data.getAmount())));
        vh.price.setText("Harga Beli : Rp ".concat(String.valueOf(data.getPrice())));
        vh.sell_price.setText("Harga Jual : Rp ".concat(String.valueOf(data.getSellPrice())));
        vh.topWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("amounts",String.valueOf(data.getAmount()));
                Intent intent = new Intent(context, UpdateVerification.class);
                intent.putExtra("simpan", i);
                intent.putExtra("id_procurement_detail", data.getIdProcurementDetail());
                intent.putExtra("id_sparepart", data.getIdSparepart());
                intent.putExtra("sparepart_name", data.getSparepartName().concat("-").concat(data.getMerk()));
                intent.putExtra("amount", data.getAmount());
                intent.putExtra("sell_price", data.getSellPrice());
                intent.putExtra("purchase_price", data.getPrice());
                intent.putExtra("id_procurement",data.getIdProcurement());
                context.startActivity(intent);
            }
        });
        vh.bottomWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Detail.remove(ifinal);
                notifyItemRemoved(ifinal);
                notifyItemRangeChanged(ifinal, getItemCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return Detail.size();
    }
}
