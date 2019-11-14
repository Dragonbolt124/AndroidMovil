package com.example.mapas1;

import androidx.fragment.app.FragmentActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, View.OnClickListener, MenuFragment.OnFragmentInteractionListener {

    private GoogleMap mMap;
    private CameraPosition cam;
    private MenuFragment mfragment;
    private Button btn1, btn2, btn3, btn4;

    private boolean satelite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        mfragment = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.xmenu);

        satelite = false;
        btn1 = (Button) mfragment.getView().findViewById(R.id.xbtnsat);
        btn1.setOnClickListener(this);

        btn2 = (Button) mfragment.getView().findViewById(R.id.xbtncenter);
        btn2.setOnClickListener(this);

        btn3 = (Button) mfragment.getView().findViewById(R.id.xbtnanimar);
        btn3.setOnClickListener(this);

        btn4 = (Button) mfragment.getView().findViewById(R.id.xbtnpos);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if(id==R.id.xbtnsat)
        {
            if(satelite)
            {
                btn1.setText("Satelite");
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                satelite = false;
            }
            else
            {
                btn1.setText("Normal");
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                satelite = true;
            }
        }
        else if(id==R.id.xbtncenter)
        {
            LatLng sevilla = new LatLng(37.3828300,-5.9731700);
            mMap.addMarker(new MarkerOptions().position(sevilla).title("Marker in Sevilla"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sevilla,10));
            //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sevilla,10));
        }
        else if(id==R.id.xbtnanimar)
        {
            LatLng cdmx = new LatLng(19.4284706,-99.1276627);
            mMap.addMarker(new MarkerOptions().position(cdmx).title("Marker in CDMX"));
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cdmx,10));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cdmx,10));
        }
        else if(id==R.id.xbtnpos)
        {
            CameraPosition cam = mMap.getCameraPosition();
            LatLng coor = cam.target;
            double latitud = coor.latitude;
            double longitud = coor.longitude;

            Toast.makeText(getApplicationContext(),"lat: "+latitud+"\nlon: "+longitud,Toast.LENGTH_LONG).show();
        }
    }
}
