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

import p3l_8980.com.atmaauto.Controller.Detail;
import p3l_8980.com.atmaauto.Controller.DetailService;
import p3l_8980.com.atmaauto.Controller.ProcurementDetail;
import p3l_8980.com.atmaauto.Controller.Service;
import p3l_8980.com.atmaauto.R;

public class AdapterDetailService extends RecyclerView.Adapter<AdapterDetailService.MyViewHolder> {
    private Context context;
    private DetailService DetailBundle;
    private List<Service> Detail;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView service_name, license_number, sub_total;
        public LinearLayout topWraper;
        public LinearLayout bottomWraper;

        public MyViewHolder(View v) {
            super(v);
            service_name = v.findViewById(R.id.frgtServiceName);
            license_number = v.findViewById(R.id.frgtMotorUser);
            sub_total = v.findViewById(R.id.frgtSubTotal);
            bottomWraper = v.findViewById(R.id.bottom_wrapper);
            topWraper = v.findViewById(R.id.top_wrapper);
        }
    }

    public AdapterDetailService(DetailService DetailBundle , Context context) {
        this.DetailBundle = DetailBundle;
        this.context = context;
    }

    public AdapterDetailService(List<Service> DetailBundle) {
        this.Detail= DetailBundle;
    }

    @NonNull
    @Override
    public AdapterDetailService.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycledetailservice, viewGroup, false);
        return new AdapterDetailService.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterDetailService.MyViewHolder vh, final int i) {
        final Service data = Detail.get(i);
        final int ifinal = vh.getAdapterPosition();
        vh.service_name.setText(data.getServiceName());
        vh.license_number.setText("Jumlah : ".concat(String.valueOf(data.getLicenseNumber())));
        vh.sub_total.setText("Rp ".concat(String.valueOf(data.getDetailServiceSubtotal())));
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
