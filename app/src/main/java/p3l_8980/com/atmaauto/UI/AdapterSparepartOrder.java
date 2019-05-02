package p3l_8980.com.atmaauto.UI;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import p3l_8980.com.atmaauto.Controller.Detail;
import p3l_8980.com.atmaauto.Controller.Procurement;
import p3l_8980.com.atmaauto.Controller.ProcurementDetail;
import p3l_8980.com.atmaauto.Controller.ProcurementList;
import p3l_8980.com.atmaauto.Controller.SparepartList;
import p3l_8980.com.atmaauto.R;

public class AdapterSparepartOrder extends RecyclerView.Adapter<AdapterSparepartOrder.MyViewHolder>  {
    private Context context;
    private Detail DetailBundle;
    private List<ProcurementDetail> Detail;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sparepart_name, amount, price;
        public LinearLayout topWraper;
        public LinearLayout bottomWraper;

        public MyViewHolder(View v) {
            super(v);
            sparepart_name = v.findViewById(R.id.frgtNamaSparepart);
            amount = v.findViewById(R.id.frgtAmount);
            price = v.findViewById(R.id.frgtPrice);
            bottomWraper = v.findViewById(R.id.bottom_wrapper);
            topWraper = v.findViewById(R.id.top_wrapper);
        }
    }

    public AdapterSparepartOrder(Detail DetailBundle , Context context) {
        this.DetailBundle = DetailBundle;
        this.context = context;
    }

    public AdapterSparepartOrder(List<ProcurementDetail> DetailBundle) {
        this.Detail= DetailBundle;
    }

    @NonNull
    @Override
    public AdapterSparepartOrder.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclesparepart, viewGroup, false);
        return new AdapterSparepartOrder.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterSparepartOrder.MyViewHolder vh, final int i) {
        final ProcurementDetail data = Detail.get(i);
        final int ifinal = vh.getAdapterPosition();
        vh.sparepart_name.setText(data.getSparepartName());
        vh.amount.setText("Jumlah : ".concat(String.valueOf(data.getAmount())));
        vh.price.setText("Rp ".concat(String.valueOf(data.getSubtotal())));
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
