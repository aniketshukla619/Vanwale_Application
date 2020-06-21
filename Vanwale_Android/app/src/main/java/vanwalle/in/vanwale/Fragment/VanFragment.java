package vanwalle.in.vanwale.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vanwalle.in.vanwale.Adapter.VanAdapter;
import vanwalle.in.vanwale.Dashboard;
import vanwalle.in.vanwale.Interface.ItemClickListener;
import vanwalle.in.vanwale.R;
import vanwalle.in.vanwale.model.Address;
import vanwalle.in.vanwale.model.Van;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VanFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TAG = "VanFragment";
    Context mContext;
    FirebaseRecyclerAdapter<Van,VanAdapter.ViewHolder> adapter;
    RecyclerView recyclerView;
    public VanFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_van, container, false);
        mContext = getActivity();
        recyclerView=(RecyclerView)view.findViewById(R.id.rv_main_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference categories = database.getReference("city").child("bhopal");
loadvehicle(categories);
        return view;
    }
    private void loadvehicle(DatabaseReference databaseReference) {
        adapter = new FirebaseRecyclerAdapter<Van,VanAdapter.ViewHolder>(Van.class, R.layout.van_details, VanAdapter.ViewHolder.class,databaseReference) {
            @Override
            protected void populateViewHolder(VanAdapter.ViewHolder viewHolder, Van model,final int position) {
                if (model != null) {
                    Log.d("model",model.getDrivername());
                  if (model.getPlace1().equals(Address.locality) || model.getPlace2().equals(Address.locality) || model.getPlace3().equals(Address.locality) || model.getPlace4().equals(Address.locality) || model.getPlace5().equals(Address.locality) || model.getPlace6().equals(Address.locality)) {
                       viewHolder.cardView.setVisibility(View.VISIBLE);
                        viewHolder.seats.setText(model.getSeats()+"Seats Left");
                        viewHolder.drivername.setText("Driver:"+model.getDrivername());
viewHolder.places.setText(model.getPlace1());
                        viewHolder.vanno.setText(model.getVehicleno());

                        viewHolder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean isLongClick) {
                                ((Dashboard) mContext).clearBackStack();
                                ((Dashboard) mContext).changeFragment(new StudentFragment(), StudentFragment.TAG);
                            }
                        });
                    }
                    }
               }

        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


    }
}









