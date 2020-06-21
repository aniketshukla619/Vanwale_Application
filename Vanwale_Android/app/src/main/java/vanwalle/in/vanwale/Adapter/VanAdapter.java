package vanwalle.in.vanwale.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import vanwalle.in.vanwale.Interface.ItemClickListener;
import vanwalle.in.vanwale.R;
import vanwalle.in.vanwale.Utils.MySharedPreferences;

public class VanAdapter extends RecyclerView.Adapter<VanAdapter.ViewHolder> {
    Context mContext;

    MySharedPreferences mySharedPreferences;

    public VanAdapter(Context mContext) {
        this.mContext=mContext;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.van_details, viewGroup, false);
            mySharedPreferences=new MySharedPreferences(mContext);
            return new ViewHolder(view);
        }



    @Override
    public void onBindViewHolder(@NonNull VanAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView drivername,seats,places;
        public Button price,vanno;
public CardView cardView;
        private ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.card);
           imageView=(ImageView)itemView.findViewById(R.id.van_image);
           drivername=(Button) itemView.findViewById(R.id.name);
           seats=(TextView)itemView.findViewById(R.id.seats);
           price=(Button)itemView.findViewById(R.id.price);
           places=(TextView)itemView.findViewById(R.id.places);
           vanno=(Button)itemView.findViewById(R.id.vanno);
            itemView.setOnClickListener(this);
//            rvCatList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.HORIZONTAL,false));
//            categoryBikeListAdapter=new CategoryBikeListAdapter(mContext);
//            rvCatList.setAdapter(categoryBikeListAdapter);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {

            itemClickListener.onClick(v,getAdapterPosition(),false);
        }
    }
}

