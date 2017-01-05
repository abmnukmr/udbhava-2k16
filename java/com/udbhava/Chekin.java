package com.udbhava;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class Chekin extends ActionBarActivity implements OnMapReadyCallback {

    GoogleMap googleMap;
    SharedPreferences sharedPreferences;
    int locationCount = 0;
    private  GoogleMap mMap;
    private  ArrayList<MyMarker> mMyMarkersArray = new ArrayList<MyMarker>();
    private  HashMap<Marker, MyMarker> mMarkersHashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chekin);



            // Initialize the HashMap for Markers and MyMarker object
            mMarkersHashMap = new HashMap<Marker, MyMarker>();

            mMyMarkersArray.add(new MyMarker("Brasil", "icon1", Double.parseDouble("-28.5971788"), Double.parseDouble("-52.7309824")));
            mMyMarkersArray.add(new MyMarker("United States", "icon2", Double.parseDouble("33.7266622"), Double.parseDouble("-87.1469829")));
            mMyMarkersArray.add(new MyMarker("Canada", "icon3", Double.parseDouble("51.8917773"), Double.parseDouble("-86.0922954")));
            mMyMarkersArray.add(new MyMarker("England", "icon4", Double.parseDouble("52.4435047"), Double.parseDouble("-3.4199249")));
            mMyMarkersArray.add(new MyMarker("España", "icon5", Double.parseDouble("41.8728262"), Double.parseDouble("-0.2375882")));
            mMyMarkersArray.add(new MyMarker("Portugal", "icon6", Double.parseDouble("40.8316649"), Double.parseDouble("-4.936009")));
            mMyMarkersArray.add(new MyMarker("Deutschland", "icon7", Double.parseDouble("51.1642292"), Double.parseDouble("10.4541194")));
            mMyMarkersArray.add(new MyMarker("Atlantic Ocean", "icondefault", Double.parseDouble("-13.1294607"), Double.parseDouble("-19.9602353")));

            setUpMap();

            plotMarkers(mMyMarkersArray);
        }

    private void plotMarkers(ArrayList<MyMarker> markers)
    {

        if(markers.size() > 0)
        {
            for (MyMarker myMarker : markers)
            {

                // Create user marker with custom icon and other options
                MarkerOptions markerOption = new MarkerOptions().position(new LatLng(myMarker.getmLatitude(), myMarker.getmLongitude()));
                markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.bacfk));

                Marker currentMarker = mMap.addMarker(markerOption);
                mMarkersHashMap.put(currentMarker, myMarker);

                mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
            }
        }
    }

    private int manageMarkerIcon(String markerIcon)
    {
        if (markerIcon.equals("icon1"))
            return R.drawable.bacfk;
        else if(markerIcon.equals("icon2"))
            return R.drawable.bacfk;
        else if(markerIcon.equals("icon3"))
            return R.drawable.bacfk;
        else if(markerIcon.equals("icon4"))
            return R.drawable.bacfk;
        else if(markerIcon.equals("icon5"))
            return R.drawable.bacfk;
        else if(markerIcon.equals("icon6"))
            return R.drawable.bacfk;
        else if(markerIcon.equals("icon7"))
            return R.drawable.bacfk;
        else
            return R.drawable.bacfk;
    }


    private void setUpMap()
    {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            // Try to obtain the map from the SupportMapFragment.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            // Check if we were successful in obtaining the map.

            if (mMap != null)
            {
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
                {
                    @Override
                    public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker)
                    {
                        marker.showInfoWindow();
                        return true;
                    }
                });
            }
            else
                Toast.makeText(getApplicationContext(), "Unable to create Maps", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter
    {
        public MarkerInfoWindowAdapter()
        {
        }

        @Override
        public View getInfoWindow(Marker marker)
        {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker)
        {
            View v  = getLayoutInflater().inflate(R.layout.marker, null);

            MyMarker myMarker = mMarkersHashMap.get(marker);

            ImageView markerIcon = (ImageView) v.findViewById(R.id.imagemap);

            TextView markerLabel = (TextView)v.findViewById(R.id.textviewmap);

            TextView anotherLabel = (TextView)v.findViewById(R.id.textviewmap1);

            markerIcon.setImageResource(manageMarkerIcon(myMarker.getmIcon()));

            markerLabel.setText(myMarker.getmLabel());
            anotherLabel.setText("A custom text");

            return v;
        }
    }
    public class MyMarker
    {
        private String mLabel;
        private String mIcon;
        private Double mLatitude;
        private Double mLongitude;

        public MyMarker(String label, String icon, Double latitude, Double longitude)
        {
            this.mLabel = label;
            this.mLatitude = latitude;
            this.mLongitude = longitude;
            this.mIcon = icon;
        }

        public String getmLabel()
        {
            return mLabel;
        }

        public void setmLabel(String mLabel)
        {
            this.mLabel = mLabel;
        }

        public String getmIcon()
        {
            return mIcon;
        }

        public void setmIcon(String icon)
        {
            this.mIcon = icon;
        }

        public Double getmLatitude()
        {
            return mLatitude;
        }

        public void setmLatitude(Double mLatitude)
        {
            this.mLatitude = mLatitude;
        }

        public Double getmLongitude()
        {
            return mLongitude;
        }

        public void setmLongitude(Double mLongitude)
        {
            this.mLongitude = mLongitude;
        }
    }

}