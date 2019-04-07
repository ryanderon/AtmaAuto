package p3l_8980.com.atmaauto.UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.R;

public class AdapterSparepart extends RecyclerView.Adapter<AdapterSparepart.MyViewHolder> {

    private Context mContext;
    private List<Sparepart> mData = new ArrayList<>();

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView sparepart_name, stock, sell_price;
        CardView cardView;

        public LinearLayout topcard;

        public  MyViewHolder(View itemView){
            super(itemView);
            sparepart_name = (TextView) itemView.findViewById(R.id.sparepart_name);
            stock = (TextView) itemView.findViewById(R.id.stock);
            sell_price = (TextView) itemView.findViewById(R.id.sell_price);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            topcard = itemView.findViewById(R.id.topcard);


/*            ImageView sparepart_img;
            sparepart_img = (ImageView) itemView.findViewById(R.id.sparepart_image); */
        }
    }

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
    public void onBindViewHolder(@NonNull AdapterSparepart.MyViewHolder myViewHolder, final int i) {

        myViewHolder.sparepart_name.setText(mData.get(i).getSparepartName());
        myViewHolder.stock.setText("Stok : " + mData.get(i).getStock());
        myViewHolder.sell_price.setText("Harga : " + mData.get(i).getSellPrice());
/*        myViewHolder.sparepart_img.setImageResource(mData.get(i).getImage() ); */

        final Sparepart data = mData.get(i);
        final int ifinal = myViewHolder.getAdapterPosition();

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
                mContext.startActivity(intent);
            }
            });

//        myViewHolder.sparepart_name.setText(data.getSparepartName());
//        myViewHolder.stock.setText(data.getStock());
//        myViewHolder.sell_price.setText(data.getSellPrice());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
