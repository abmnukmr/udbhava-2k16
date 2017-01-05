package com.udbhava;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ABHIMANYU SINGH KASH on 8/5/2016.
 */
public class Map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    Boolean locationServiceBoolean = false;
    int providerType = 0;
    static AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chekin);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        LatLng gateone = new LatLng(31.7017529, 76.5228523);
        LatLng nbh = new LatLng(31.710620, 76.523674);
        LatLng pgh = new LatLng(31.703761, 76.526158);
        LatLng himgiri = new LatLng(31.709055, 76.523834);
        LatLng himadri = new LatLng(31.708444, 76.523624);
        LatLng Hamirpur = new LatLng(31.7080692, 76.5280236);
        LatLng agh = new LatLng(31.703962, 76.525514);
        LatLng juicebar = new LatLng(31.7055820,76.5279485);
        LatLng verka = new LatLng(31.7065906,76.5290053);
        LatLng studentspark = new LatLng(31.7072660,76.5282597);
        LatLng new_cc = new LatLng(31.7061479,76.5274174);
        LatLng guesthouse = new LatLng(31.7059982,76.5262426);
        LatLng oat = new LatLng(31.705537, 76.525079);
        LatLng ground = new LatLng(31.7061114,76.5247674);
        LatLng estateoffice = new LatLng(31.7084161,76.5262641);
        LatLng mechdept = new LatLng(31.7088451,76.5267469);
        LatLng ced = new LatLng(31.7090276,76.5272404);
        LatLng csed = new LatLng(31.7085302,76.5270366);
        LatLng archi = new LatLng(31.7089865,76.5263231);
        LatLng workshop = new LatLng(31.7097532,76.5269615);
        LatLng sbi= new LatLng(31.7096118,76.5273209);
        LatLng atmprintgenralhiteshi = new LatLng(31.7095981,76.5275837);
        LatLng kbh = new LatLng(31.7103921,76.5269507);
        LatLng vbh = new LatLng(31.7104560,76.5274550);
        LatLng foodt = new LatLng(31.7108120,76.5268327);
        LatLng mmh = new LatLng(31.7121080,76.5257062);
        LatLng arawli = new LatLng(31.7130207,76.5256847);
        LatLng satpura = new LatLng(31.7134588,76.5256740);
        LatLng shivalik = new LatLng(31.7128564,76.5265967);
        LatLng dbh = new LatLng(31.7115878,76.5247299);
        LatLng audi= new LatLng(31.7070150,76.5272565);
        LatLng centralblok  = new LatLng(31.7076996,76.5279412);
        LatLng admin = new LatLng(31.7080692,76.5280236);
        LatLng eee = new LatLng(31.7080601,76.5274228);
        LatLng library= new LatLng(31.7072706,76.5268220);
        LatLng ichamul= new LatLng(31.7074851,76.5268488);
        LatLng lecturehall = new LatLng(31.7074486,76.5264304);
        LatLng ece = new LatLng(31.7081012,76.5265269);
        LatLng chem = new LatLng(31.7081194,76.5259369);






        mMap.addMarker(new MarkerOptions().position(juicebar).icon(BitmapDescriptorFactory.fromResource(R.mipmap.cooker)).title("Juice Bar")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(verka).icon(BitmapDescriptorFactory.fromResource(R.mipmap.cooker)).title("Verka")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(studentspark).icon(BitmapDescriptorFactory.fromResource(R.mipmap.cooker)).title("Student's Park/Nescafe")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(ichamul).icon(BitmapDescriptorFactory.fromResource(R.mipmap.cooker)).title("Indian Cofee House(ICH) and Amul")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(foodt).icon(BitmapDescriptorFactory.fromResource(R.mipmap.cooker)).title("4-H Food Court")).showInfoWindow();


        mMap.addMarker(new MarkerOptions().position(new_cc).title("New Computer Center")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(guesthouse).title("Guest House")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(oat).title("Open Air Theater")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ground)).position(ground).title("Ground")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(estateoffice).title("Estate Office")).showInfoWindow();
        ///department

        mMap.addMarker(new MarkerOptions().position(mechdept).icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin)).title("Mechanical Department")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(ced).icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin)).title("Civil Engineering Department")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(csed).icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin)).title("Computer Science and Engineering/Computer Centre")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(archi).icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin)).title("Architecture  and Energy Department")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(eee).icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin)).title("Electriacal and Electronics Engineering Department")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(chem).icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin)).title("Chemical Engineering Department/Chemistry Department")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(ece).icon(BitmapDescriptorFactory.fromResource(R.mipmap.pin)).title("Electronics and Communication Engineering Department/Physics Department/Material Science Department")).showInfoWindow();



        mMap.addMarker(new MarkerOptions().position(workshop).title("Central Workshop")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(sbi).title("SBI bank/Post office")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(atmprintgenralhiteshi).icon(BitmapDescriptorFactory.fromResource(R.mipmap.atm12)).title("SBI ATM,  Hiteshi stationary, Genral Store,Printing and Recharge Shop")).showInfoWindow();
         /// hostel

        mMap.addMarker(new MarkerOptions().position(kbh).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("Kailsh Boys Hostel")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(vbh).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("Vindhyachal Boys Hostel")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(mmh).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("ManiMahesh Boys Hostel")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(dbh).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("Dhauladhar Boys Hostel")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(arawli).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("Aravali Hostel")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(satpura).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("Satpura Boys Hostel")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(shivalik).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("Shivalik Boys Hostel")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(kbh).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("Kailsh Boys Hostel")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(himadri).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("Himadri Boys Hostel")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(himgiri).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("Himgiri Boys Hostel")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(nbh).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("Neelkanth Boys Hostel")).showInfoWindow();
        ///.
        //Girl Hostel
        mMap.addMarker(new MarkerOptions().position(agh).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).snippet("Ambika Girls Hostel").title("Ambika Girls Hostel")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(pgh).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("Parvati Girls Hostel")).showInfoWindow();

        mMap.addMarker(new MarkerOptions().position(nbh).icon(BitmapDescriptorFactory.fromResource(R.mipmap.hotel1)).title("NIT Hamirpur")).showInfoWindow();



        //

        mMap.addMarker(new MarkerOptions().position(audi).icon(BitmapDescriptorFactory.fromResource(R.mipmap.audk)).title("Auditorium")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(centralblok).title("Central Block")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(admin).title("Administrative Block")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(library).title("Central Library")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(lecturehall).title("Vivekanand Lecture Hall complex")).showInfoWindow();


        mMap.addMarker(new MarkerOptions().position(gateone).title("NIT-H Main Gate")).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(Hamirpur).icon(BitmapDescriptorFactory.fromResource(R.mipmap.nit)).title("NIT Hamirpur")).showInfoWindow();


        mMap.animateCamera(CameraUpdateFactory.zoomTo(400));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(40), 2000, null);


        // googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        //googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Hamirpur));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;

        }
        mMap.setMyLocationEnabled(true);
        LocationManager mlocManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(!enabled) {
            showDialogGPS();
        }

    }
    private void showDialogGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Enable GPS");
        builder.setMessage("Please enable GPS");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivity(
                        new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        builder.setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
