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
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import p3l_8980.com.atmaauto.Controller.Sparepart;
import p3l_8980.com.atmaauto.Controller.SparepartList;
import p3l_8980.com.atmaauto.R;

public class AdapterSparepart2 extends RecyclerView.Adapter<AdapterSparepart2.MyViewHolder> {

    private Context context;
    private List<Sparepart> SparepartBundle;
    private List<Sparepart> SparepartFilter;


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView sparepart_name, stock, sell_price;
        CardView cardView;
        ImageView sparepart_image;

        public LinearLayout topcard;

        public  MyViewHolder(View v){
            super(v);
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

    public AdapterSparepart2(List<Sparepart> SparepartBundle, Context context) {
        this.context = context;
        this.SparepartBundle = SparepartBundle;
        this.SparepartFilter = SparepartBundle;
    }

    @NonNull
    @Override
    public AdapterSparepart2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_item_sparepart, viewGroup, false);
        return new MyViewHolder(v);
    }


    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    SparepartFilter = SparepartBundle;
                } else {
                    List<Sparepart> filteredList = new ArrayList<>();
                    for (Sparepart obj : SparepartBundle) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match

                        if (obj.getSparepartName().toLowerCase().contains(charString.toLowerCase())
                               ){
                            // || obj.getCustomerAddress().toLowerCase().contains(charString.toLowerCase())
                            // || obj.getCustomerPhoneNumber().toLowerCase().contains(charString.toLowerCase())
                            filteredList.add(obj);
                        }
                    }
                    SparepartFilter = filteredList;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = SparepartFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                SparepartFilter = (ArrayList<Sparepart>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



    @Override
    public void onBindViewHolder(@NonNull final AdapterSparepart2.MyViewHolder myViewHolder, final int i) {
        final Sparepart data = SparepartFilter.get(i);

        myViewHolder.sparepart_name.setText(data.getSparepartName());
        myViewHolder.stock.setText("Stok : " + data.getStock());
        myViewHolder.sell_price.setText("Harga : " + data.getSellPrice());
        Picasso.get().load("https://p3l.yafetrakan.com/images/"+data.getImage()).memoryPolicy(MemoryPolicy.NO_CACHE) .networkPolicy(NetworkPolicy.NO_CACHE).into(myViewHolder.sparepart_image);


        myViewHolder.topcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddSparepart.class);
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
                context.startActivity(intent);
            }
        });

//        myViewHolder.sparepart_name.setText(data.getSparepartName());
//        myViewHolder.stock.setText(data.getStock());
//        myViewHolder.sell_price.setText(data.getSellPrice());

    }

    @Override
    public int getItemCount() {
        return SparepartFilter.size();
    }
}
