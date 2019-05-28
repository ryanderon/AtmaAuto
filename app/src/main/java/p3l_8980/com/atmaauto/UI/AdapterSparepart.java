package p3l_8980.com.atmaauto.UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.Controller.SparepartList;
import p3l_8980.com.atmaauto.R;

public class AdapterSparepart extends RecyclerView.Adapter<AdapterSparepart.MyViewHolder> {

    private Context mContext;
    private SparepartList SparepartBundle;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView sparepart_name, stock, sell_price;
        CardView cardView;
        ImageView sparepart_image;

        public LinearLayout topcard;

        public  MyViewHolder(View itemView){
            super(itemView);
            sparepart_name = (TextView) itemView.findViewById(R.id.sparepart_name);
            stock = (TextView) itemView.findViewById(R.id.stock);
            sell_price = (TextView) itemView.findViewById(R.id.sell_price);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            sparepart_image = (ImageView) itemView.findViewById(R.id.sparepart_image);
            topcard = itemView.findViewById(R.id.topcard);


/*            ImageView sparepart_img;
            sparepart_img = (ImageView) itemView.findViewById(R.id.sparepart_image); */
        }
    }

    public AdapterSparepart(SparepartList SparepartBundle, Context mContext) {
        this.mContext = mContext;
        this.SparepartBundle = SparepartBundle;
    }

    public void setFilter(List<Sparepart> newList){
        SparepartBundle = new SparepartList();
        SparepartBundle.getData().addAll(newList);
        notifyDataSetChanged();
        int i;
        for (i=0; i<SparepartBundle.getData().size(); i++)
        {
            Log.d("sparepartbundle",SparepartBundle.getData().get(i).getSparepartName());
        }

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
    public void onBindViewHolder(@NonNull AdapterSparepart.MyViewHolder myViewHolder, final int i) {

        final Sparepart data = SparepartBundle.getData().get(i);
        final int ifinal = myViewHolder.getAdapterPosition();


        myViewHolder.sparepart_name.setText(data.getSparepartName());
        myViewHolder.stock.setText("Stok : " + data.getStock());
        myViewHolder.sell_price.setText("Harga : " + data.getSellPrice());
        Picasso.get().load("http://192.168.19.140/8991/images/"+data.getImage()).memoryPolicy(MemoryPolicy.NO_CACHE) .networkPolicy(NetworkPolicy.NO_CACHE).into(myViewHolder.sparepart_image);

        myViewHolder.topcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddSparepart.class);
                intent.putExtra("simpan", i);
                intent.putExtra("id", data.getIdSparepart());
                intent.putExtra("name", data.getSparepartName());
                intent.putExtra("merk", data.getMerk());
                intent.putExtra("stock", data.getStock());
                intent.putExtra("minstock", data.getMinStock());
                intent.putExtra("purchaseprice", data.getPurchasePrice());
                intent.putExtra("sellprice", data.getSellPrice());
                intent.putExtra("placement", data.getPlacement());
                intent.putExtra("position", data.getPosition());
                intent.putExtra("place", data.getPlace()) ;
                intent.putExtra("number", data.getNumber());
                intent.putExtra("type", data.getSparepartTypeName());
                intent.putExtra("idtype", data.getIdSparepartType());
                intent.putExtra("image", data.getImage());
                mContext.startActivity(intent);
            }
        });

//        myViewHolder.sparepart_name.setText(data.getSparepartName());
//        myViewHolder.stock.setText(data.getStock());
//        myViewHolder.sell_price.setText(data.getSellPrice());

    }

    @Override
    public int getItemCount() {
        return SparepartBundle.getData().size();
    }


}