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

import p3l_8980.com.atmaauto.Controller.Customer;
import p3l_8980.com.atmaauto.Controller.CustomerList;
import p3l_8980.com.atmaauto.Controller.Motorcycle;
import p3l_8980.com.atmaauto.Controller.MotorcycleList;
import p3l_8980.com.atmaauto.R;

public class AdapterMotor extends RecyclerView.Adapter<AdapterMotor.MyViewHolder> {
    private MotorcycleList MotorBundle;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView licenseNumber, Type, Brand;
        public LinearLayout topWraper;
        public LinearLayout bottomWraper;

        public MyViewHolder(View v) {
            super(v);
            licenseNumber = v.findViewById(R.id.frgtLicenseNumber);
            Type = v.findViewById(R.id.frgtTypeMotor);
            Brand = v.findViewById(R.id.frgtBrandMotor);
            bottomWraper = v.findViewById(R.id.bottom_wrapper);
            topWraper = v.findViewById(R.id.top_wrapper);
        }
    }

    public AdapterMotor(MotorcycleList MotorBundle , Context context) {
        this.MotorBundle = MotorBundle;
        this.context = context;
    }

    public void setFilter(List<Motorcycle> newList){
        MotorBundle = new MotorcycleList();
        MotorBundle.getData().addAll(newList);
        notifyDataSetChanged();

        int i;
        for (i=0; i<MotorBundle.getData().size(); i++)
        {
//            Log.d("customerbundle",MotorBundle.getData().get(i).getCustomerName());
        }
    }

    @NonNull
    @Override
    public AdapterMotor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclemotor, viewGroup, false);
        return new AdapterMotor.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterMotor.MyViewHolder vh, final int i) {
        final Motorcycle data = MotorBundle.getData().get(i);
        final int ifinal = vh.getAdapterPosition();
        vh.licenseNumber.setText(data.getLicenseNumber());
        vh.Brand.setText(data.getMotorcycleBrand());
        vh.Type.setText(data.getMotorcycleType());
//        vh.topWraper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, AddCustomer.class);
//                intent.putExtra("simpan", i);
//                intent.putExtra("name", data.getCustomerName());
//                intent.putExtra("address", data.getCustomerAddress());
//                intent.putExtra("number", data.getCustomerPhoneNumber());
//                intent.putExtra("id", data.getIdCustomer());
//                context.startActivity(intent);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return MotorBundle.getData().size();
    }

}
