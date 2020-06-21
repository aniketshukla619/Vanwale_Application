package vanwalle.in.vanwale.Fragment;

import android.Manifest;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import vanwalle.in.vanwale.Dashboard;
import vanwalle.in.vanwale.R;
import vanwalle.in.vanwale.Utils.CustomToast;
import vanwalle.in.vanwale.model.BookVehicleDetails;
import vanwalle.in.vanwale.model.Locality;


import static android.content.Context.LOCATION_SERVICE;

public class BookingFragment extends BaseFragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public static BookingFragment categoryFragment;
    public static final String TAG = "BookingFragment";
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
    public static final int LOCATION_UPDATE_MIN_TIME = 5000;
    String s="";
    GoogleMap mGoogleMap;
    private Context mContext;
    MapView mMapView;
    AppCompatSpinner spinner;
    public String loc_address, loc_state, loc_city;
    EditText editText,time1,time2;
    private boolean result;


    private ArrayList<String> arrayList;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                Log.d(TAG, "onLocationChanged: " + String.format("%f, %f", location.getLatitude(), location.getLongitude()));
                drawMarker(location);
                mLocationManager.removeUpdates(mLocationListener);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };



    public BookingFragment() {
        // Required empty public constructor
    }

    public static BookingFragment getInstance() {
        return categoryFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        Button search = (Button) view.findViewById(R.id.search);
        spinner=(AppCompatSpinner)view.findViewById(R.id.cityspinner);
        arrayList=new ArrayList<String>();

        mContext = getActivity();

        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        //Get map fragment
//        FragmentManager fm = getActivity().getSupportFragmentManager();/// getChildFragmentManager();
//        mapFrag = (SupportMapFragment) fm.findFragmentById(R.id.map);
//        if (mapFrag == null) {
//            mapFrag = SupportMapFragment.newInstance();
//            fm.beginTransaction().replace(R.id.map, mapFrag).commit();
//        }
//        mapFrag.getMapAsync(this);

        //Check device location is enabled or not
        final LocationManager manager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
        InputMethodManager imm=(InputMethodManager)getActivity().getSystemService(Service.INPUT_METHOD_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
        editText = (EditText) view.findViewById(R.id.from);
        imm.hideSoftInputFromWindow(editText.getWindowToken(),0);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    openMap();
                } else {
                    checkLocationPermission();
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ((Dashboard) getActivity()).clearBackStack();
                ((Dashboard) getActivity()).changeFragment(new VanFragment(), VanFragment.TAG);
            }
        });

        time1=(EditText) view.findViewById(R.id.start_time);
        imm.showSoftInput(time1,0);

        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");


            }
        });
        time2=(EditText) view.findViewById(R.id.end_time);
        imm.showSoftInput(time2,0);
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragmentDrop();
                newFragment.show(getFragmentManager(), "timePicker");


            }
        });


        return view;

    }

    public void data1()
    {
        FirebaseDatabase.getInstance().getReference("Locality").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {

                    Locality locality=dataSnapshot1.getValue(Locality.class);
                    Log.d("locality",locality.getLocality());
                    if(locality!=null) {
                        arrayList.add(locality.getLocality());
                        Toast.makeText(getActivity().getApplicationContext(),arrayList.toString(),Toast.LENGTH_SHORT).show();

                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,arrayList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void data()
    {
        FirebaseDatabase.getInstance().getReference("Locality").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {

                    Locality locality=dataSnapshot1.getValue(Locality.class);
                    Log.d("locality",locality.getLocality());
                    if(locality!=null) {
                        arrayList.add(locality.getLocality());
                        Toast.makeText(getActivity().getApplicationContext(),arrayList.toString(),Toast.LENGTH_SHORT).show();

                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,arrayList);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("To continue,  Please turn on device location")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(mContext)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                result = true;
                openMap();
            }
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
    private void initMap() {
//        int googlePlayStatus = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext);
//        if (googlePlayStatus != ConnectionResult.SUCCESS) {
//            GooglePlayServicesUtil.getErrorDialog(googlePlayStatus, getActivity(), -1).show();
//            finish();
//        } else {
        if (mGoogleMap != null) {
            if (ContextCompat.checkSelfPermission(mContext,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mGoogleMap.setMyLocationEnabled(true);
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
                mGoogleMap.getUiSettings().setAllGesturesEnabled(true);
            } else {
//                    checkLocationPermission();
            }
        }
//        }
    }

    private void getCurrentLocation() {
        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location location = null;
        if (!(isGPSEnabled || isNetworkEnabled))
            CustomToast.showerror(mContext, "Location not enabled");
        else {
            if (isNetworkEnabled) {
                if (ContextCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                    location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                } else {
//                    checkLocationPermission();
                }
            }

            if (isGPSEnabled) {
                if (ContextCompat.checkSelfPermission(mContext,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                    location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                } else {
//                    checkLocationPermission();
                }
            }
        }
        if (location != null) {
            Log.d(TAG, "getCurrentLocation: " + String.format("getCurrentLocation(%f, %f)", location.getLatitude(),
                    location.getLongitude()));
            drawMarker(location);
        }
    }

    private void setMapListeners(final GoogleMap mGoogleMap)
    {
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (mGoogleMap != null) {
                    mGoogleMap.clear();
                    String address = getAddress(latLng.latitude, latLng.longitude);
                    mGoogleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(address));
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                }
            }
        });
    }

    private void drawMarker(Location location)
    {
        if(mGoogleMap != null)
        {
            mGoogleMap.clear();
            LatLng gps = new LatLng(location.getLatitude(), location.getLongitude());
            String address = getAddress(location.getLatitude(), location.getLongitude());
            Toast.makeText(getActivity(),""+address,Toast.LENGTH_SHORT).show();
            mGoogleMap.addMarker(new MarkerOptions()
                    .position(gps)
                    .title(address));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps, 12));
        }
    }
    private String getAddress(double latitude, double longitude)
    {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
           // Toast.makeText(getActivity(), ""+addresses.toString(), Toast.LENGTH_SHORT).show();
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                Toast.makeText(getActivity(),""+address.getSubLocality(),Toast.LENGTH_SHORT).show();
             vanwalle.in.vanwale.model.Address.locality =address.getSubLocality().toLowerCase();
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    result.append(address.getAddressLine(i)).append("\n");
                }
//                result.append(address.getAddressLine(0));
                loc_address = address.getAddressLine(0);
                loc_city = address.getLocality();
                loc_state = address.getAdminArea();
                String knownName = addresses.get(0).getFeatureName();
//                Toast.makeText(mContext, "name: "+knownName, Toast.LENGTH_SHORT).show();

                Log.d(TAG, "getAddress: city " + loc_city);
                Log.d(TAG, "getAddress: state " + loc_state);
                Log.d(TAG, "getAddress: " + address.getAddressLine(0));
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        return result.toString();
    }

    private void openMap() {
        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setContentView(R.layout.dialog_map_fragment);
        mMapView = dialog.findViewById(R.id.mapview);
        Button btn_ok = dialog.findViewById(R.id.btn_ok);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
        MapsInitializer.initialize(getActivity());
        mMapView.onCreate(dialog.onSaveInstanceState());
        mMapView.onResume();
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;
                setMapListeners(mGoogleMap);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(loc_address);
                dialog.dismiss();
            }
        });
        dialog.show();

        initMap();
        getCurrentLocation();
    }


}
