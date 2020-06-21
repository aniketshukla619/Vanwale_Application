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
import android.widget.Button;

import vanwalle.in.vanwale.Adapter.StudentAdapter;
import vanwalle.in.vanwale.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentFragment extends BaseFragment {

    public static final String TAG = "StudentFragment";
    Context mContext;
    RecyclerView recyclerView;
    Button submit;
    public StudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_student, container, false);
        submit=(Button)view.findViewById(R.id.submit);
        mContext = getActivity();
        recyclerView=(RecyclerView)view.findViewById(R.id.student);
        StudentAdapter studentAdapter=new StudentAdapter(mContext,submit);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(studentAdapter);
        return view;



    }


}
