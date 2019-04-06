package p3l_8980.com.atmaauto.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.R;

public class AdapterSparepart extends RecyclerView.Adapter<AdapterSparepart.MyViewHolder> {

    private Context mContext;
    private List<Sparepart> mData;

    public AdapterSparepart(List<Sparepart> mData, Context mContext) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_sparepart, viewGroup, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.sparepart_name.setText(mData.get(i).getPosition());
/*        myViewHolder.sparepart_img.setImageResource(mData.get(i).getImage() ); */
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView sparepart_name;
        public  MyViewHolder(View itemView){
            super(itemView);
            sparepart_name = (TextView) itemView.findViewById(R.id.sparepart_name);
/*            ImageView sparepart_img;
            sparepart_img = (ImageView) itemView.findViewById(R.id.sparepart_image); */
        }
    }
}
