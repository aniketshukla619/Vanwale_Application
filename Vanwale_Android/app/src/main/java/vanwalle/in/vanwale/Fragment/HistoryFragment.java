package vanwalle.in.vanwale.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import vanwalle.in.vanwale.Adapter.HistoryAdapter;
import vanwalle.in.vanwale.R;

public class HistoryFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    public static final String TAG = "HistoryFragment";
    Context mContext;
  RecyclerView recyclerView;

  public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_history, container, false);
      recyclerView=(RecyclerView)view.findViewById(R.id.rv_main_list);
      mContext=getActivity();
        HistoryAdapter Adapter=new HistoryAdapter(mContext);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(Adapter);

      return view;
    }





}
