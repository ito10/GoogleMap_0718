package jp.android.myapp.googlemap_0718;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        Intent intent = getIntent();
        String add = intent.getStringExtra("address");

        double latitude = 43.0675;
        double longitude = 141.350784;

        try {
            Address address = getLatLongFromLocationName(add);
            latitude = address.getLatitude();
            longitude = address.getLongitude();
        } catch (IOException e){
            e.printStackTrace();
        }

        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 18);
        mMap.moveCamera(cu);

        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title("HERE"));
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);




    }

    private Address getLatLongFromLocationName(String locationName) throws IOException {

        Geocoder gCoder = new Geocoder(this, Locale.getDefault());

        List<Address> addressList = gCoder.getFromLocationName(locationName, 1);
        Address address = addressList.get(0);

        return address;
    }

}
