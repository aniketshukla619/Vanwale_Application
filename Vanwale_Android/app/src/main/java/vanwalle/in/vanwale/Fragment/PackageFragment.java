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

import vanwalle.in.vanwale.Adapter.PackageAdapter;
import vanwalle.in.vanwale.Adapter.StudentAdapter;
import vanwalle.in.vanwale.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PackageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PackageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PackageFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TAG = "PackageFragment";
    Context mContext;
    RecyclerView recyclerView;

    public PackageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_package, container, false);
        mContext = getActivity();
        recyclerView=(RecyclerView)view.findViewById(R.id.packagei);
        PackageAdapter studentAdapter=new PackageAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(studentAdapter);
       return view;
    }



}
