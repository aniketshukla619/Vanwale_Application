package vanwalle.in.vanwale.Viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import vanwalle.in.vanwale.Interface.ItemClickListener;


/**
 * Created by AAKASH on 09-10-2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
   public TextView textMenuName,start_date,end_date;
    public LinearLayout cancel;
   public LinearLayout accept;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);
       // accept=(LinearLayout)itemView.findViewById(R.id.ll_call) ;
       // cancel=(LinearLayout)itemView.findViewById(R.id.ll_cancel);
       // textMenuName=(TextView)itemView.findViewById(R.id.tv_name);
        //start_date=(TextView)itemView.findViewById(R.id.tv_from_date);
        //end_date=(TextView)itemView.findViewById(R.id.tv_to_date);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

  /*  @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select the action");
        menu.add(0,0,getAdapterPosition(), Common.UPDATE);
        menu.add(0,1,getAdapterPosition(),Common.DELETE);

    }*/
}
