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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import p3l_8980.com.atmaauto.Controller.ApiClient;
import p3l_8980.com.atmaauto.Controller.Customer;
import p3l_8980.com.atmaauto.Controller.CustomerList;
import p3l_8980.com.atmaauto.Controller.Motorcycle;
import p3l_8980.com.atmaauto.Controller.MotorcycleList;
import p3l_8980.com.atmaauto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterMotor extends RecyclerView.Adapter<AdapterMotor.MyViewHolder> {
    private List<Motorcycle> MotorcycleBundle;
    private List<Motorcycle> MotorcycleFilter;

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

    public AdapterMotor(List<Motorcycle> MotorcycleBundle , Context context) {
        this.MotorcycleBundle = MotorcycleBundle;
        this.MotorcycleFilter = MotorcycleBundle;
        this.context = context;
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    MotorcycleFilter = MotorcycleBundle;
                } else {
                    List<Motorcycle> filteredList = new ArrayList<>();
                    for (Motorcycle obj : MotorcycleBundle) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (obj.getLicenseNumber().toLowerCase().contains(charString.toLowerCase())
                                || obj.getMotorcycleBrand().toLowerCase().contains(charString.toLowerCase())
                                || obj.getMotorcycleType().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(obj);
                        }
                    }
                    MotorcycleFilter = filteredList;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = MotorcycleFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                MotorcycleFilter = (ArrayList<Motorcycle>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

//    public void setFilter(List<Motorcycle> newList){
//        MotorBundle = new MotorcycleList();
//        MotorBundle.getData().addAll(newList);
//        notifyDataSetChanged();
//
//        int i;
//        for (i=0; i<MotorBundle.getData().size(); i++)
//        {
////            Log.d("customerbundle",MotorBundle.getData().get(i).getCustomerName());
//        }
//    }

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
        final Motorcycle data = MotorcycleFilter.get(i);
        final int ifinal = vh.getAdapterPosition();
        vh.licenseNumber.setText(data.getLicenseNumber());
        vh.Brand.setText(data.getMotorcycleBrand());
        vh.Type.setText(data.getMotorcycleType());
        vh.topWraper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddMotorcycle.class);
                intent.putExtra("simpan", i);
                intent.putExtra("license_number", data.getLicenseNumber());
                intent.putExtra("brand", data.getMotorcycleBrand());
                intent.putExtra("type", data.getMotorcycleType());
                intent.putExtra("idMotor", data.getIdMotorcycle());
                intent.putExtra("id", data.getIdCustomer());
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

                Call<ResponseBody> deleteMotor = apiClient.deleteMotor(MotorcycleBundle.get(i).getIdMotorcycle());
                deleteMotor.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 200){
//                          notifyItemRemoved(vh.getAdapterPosition());
//                          notifyItemRangeChanged(vh.getAdapterPosition(), SupplierBundle.getData().size());
                            Toast.makeText(context.getApplicationContext(), "Berhasil hapus data Motor", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context.getApplicationContext(), "Gagal hapus data Motor", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context.getApplicationContext(), "Gagal hapus data Motor", Toast.LENGTH_SHORT).show();
                    }
                });

                MotorcycleBundle.remove(ifinal);
                notifyItemRemoved(ifinal);
                notifyItemRangeChanged(ifinal, getItemCount());
            }
        });
    }


    @Override
    public int getItemCount() {
        return MotorcycleFilter.size();
    }

}
