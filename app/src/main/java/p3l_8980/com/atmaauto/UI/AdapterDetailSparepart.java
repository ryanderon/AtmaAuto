package p3l_8980.com.atmaauto.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import p3l_8980.com.atmaauto.Controller.DetailService;
import p3l_8980.com.atmaauto.Controller.DetailSparepart;
import p3l_8980.com.atmaauto.Controller.Service;
import p3l_8980.com.atmaauto.Controller.SparepartTransaction;
import p3l_8980.com.atmaauto.R;

public class AdapterDetailSparepart extends RecyclerView.Adapter<AdapterDetailSparepart.MyViewHolder> {
    private Context context;
    private DetailSparepart DetailBundle;
    private List<SparepartTransaction> Detail;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sparepart_name, amount, sub_total;
        public LinearLayout topWraper;
        public LinearLayout bottomWraper;

        public MyViewHolder(View v) {
            super(v);
            sparepart_name= v.findViewById(R.id.frgtSparepartName);
            amount = v.findViewById(R.id.frgtSparepartAmount);
            sub_total = v.findViewById(R.id.frgtSubTotal2);
            bottomWraper = v.findViewById(R.id.bottom_wrapper);
            topWraper = v.findViewById(R.id.top_wrapper);
        }
    }

    public AdapterDetailSparepart(DetailSparepart DetailBundle , Context context) {
        this.DetailBundle = DetailBundle;
        this.context = context;
    }

    public AdapterDetailSparepart(List<SparepartTransaction> DetailBundle) {
        this.Detail= DetailBundle;
    }

    @NonNull
    @Override
    public AdapterDetailSparepart.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycledetailsparepart, viewGroup, false);
        return new AdapterDetailSparepart.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterDetailSparepart.MyViewHolder vh, final int i) {
        final SparepartTransaction data = Detail.get(i);
        final int ifinal = vh.getAdapterPosition();
        vh.sparepart_name.setText(data.getSparepartName());
        vh.amount.setText("Jumlah : ".concat(String.valueOf(data.getDetailSparepartAmount())));
        vh.sub_total.setText("Rp ".concat(String.valueOf(data.getDetailSparepartSubtotal())));
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
