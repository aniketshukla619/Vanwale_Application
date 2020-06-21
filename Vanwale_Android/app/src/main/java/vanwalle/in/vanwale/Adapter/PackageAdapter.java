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

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.ViewHolder> {
    Context mContext;

    MySharedPreferences mySharedPreferences;

    public PackageAdapter(Context mContext) {
        this.mContext=mContext;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.package_list, viewGroup, false);
        mySharedPreferences=new MySharedPreferences(mContext);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull PackageAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

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

