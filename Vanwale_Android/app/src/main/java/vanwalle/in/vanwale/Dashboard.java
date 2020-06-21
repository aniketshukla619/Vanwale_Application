package vanwalle.in.vanwale;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;


import vanwalle.in.vanwale.Fragment.BookingFragment;
import vanwalle.in.vanwale.Fragment.HistoryFragment;
import vanwalle.in.vanwale.Fragment.ProfileMenuFragment;
import vanwalle.in.vanwale.Utils.BottomNavigationViewHelper;
import vanwalle.in.vanwale.Utils.MyConstants;
import vanwalle.in.vanwale.Utils.MySharedPreferences;


public class Dashboard extends AppCompatActivity {

    BottomNavigationView navigation;

    FrameLayout container;
    Toolbar toolbar;

    MySharedPreferences mySharedPreferences;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mySharedPreferences=new MySharedPreferences(this);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        navigation=(BottomNavigationView)findViewById(R.id.navigation);

      getSupportActionBar().setTitle("Dashboard");
   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            mySharedPreferences.insertString(MyConstants.FRAGMENTTAG, "BookingFragment");
           // Phone.setPhone(mySharedPreferences.getMyString(MyConstants.USERPHONE));
           // clearBackStack();
            changeFragment(new BookingFragment(), BookingFragment.TAG);
        }
      BottomNavigationViewHelper.removeShiftMode(navigation);

        BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mn_explore:
                        mySharedPreferences.insertString(MyConstants.FRAGMENTTAG, "BookingFragment");
                        clearBackStack();
                        changeFragment(new BookingFragment(), BookingFragment.TAG);
                        return true;
                    case R.id.mn_following:
                        mySharedPreferences.insertString(MyConstants.FRAGMENTTAG, "HistoryFragment");
                        clearBackStack();
                        changeFragment(new HistoryFragment(), HistoryFragment.TAG);
                        return true;
                    case R.id.mn_cart:
                        mySharedPreferences.insertString(MyConstants.FRAGMENTTAG, "MyCartFragment");
                        clearBackStack();
                        changeFragment(new BookingFragment(), BookingFragment.TAG);
                        return true;
                    case R.id.mn_profile:
                        mySharedPreferences.insertString(MyConstants.FRAGMENTTAG, "ProfileMenuFragment");
                        clearBackStack();
                        changeFragment(new ProfileMenuFragment(), ProfileMenuFragment.TAG);
                        return true;
                }
                return false;
            }
        };
   navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);


    }
    public void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            for (int i = 0; i < manager.getBackStackEntryCount(); ++i) {
                manager.popBackStack();
            }
        }
    }

    // TODO: 4/9/18 Change fragment code
    public void changeFragment(Fragment targetFragment, String tag) {
        currentFragment = targetFragment;
        if (tag != null && !tag.isEmpty()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, currentFragment)
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(tag)
                    .commitAllowingStateLoss();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, currentFragment)
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commitAllowingStateLoss();
        }
    }
}
