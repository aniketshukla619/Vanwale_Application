package vanwalle.in.vanwale.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import vanwalle.in.vanwale.R;
import vanwalle.in.vanwale.Utils.MyConstants;
import vanwalle.in.vanwale.Utils.MySharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileMenuFragment extends BaseFragment {


    public static final String TAG = "ProfileMenuFragment";
Context mContext;
MySharedPreferences mySharedPreferences;

    public ProfileMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_menu, container, false);

        mContext = getActivity();
        mySharedPreferences = new MySharedPreferences(mContext);
        //user_id = mySharedPreferences.getMyString(MyConstants.USERID);
        mySharedPreferences.insertString(MyConstants.FRAGMENTTAG, "ProfileMenuFragment");
       // getUserprofileWS(user_id);
        return view;
    }






    }
