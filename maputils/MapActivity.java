package com.example.georg.odessaguide.maputils;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.georg.odessaguide.R;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPolyline;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.MapPolyline;
import com.here.android.mpa.mapping.SupportMapFragment;
import com.here.android.mpa.search.DiscoveryResult;
import com.here.android.mpa.search.DiscoveryResultPage;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.GeocodeRequest;
import com.here.android.mpa.search.GeocodeResult;
import com.here.android.mpa.search.PlaceLink;
import com.here.android.mpa.search.ResultListener;
import com.here.android.mpa.search.SearchRequest;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements PositioningManager.OnPositionChangedListener {

    public static List<DiscoveryResult> resultList;
    private List<MapObject> objectList = new ArrayList<>();
    private List<GeoCoordinate> points = new ArrayList<>();
    private Map map = null;
    private SupportMapFragment mapFragment = null;
    private String address;
    private ArrayList<String> coordinates;
    private static final GeoCoordinate odessa = new GeoCoordinate(46.47747, 30.73262);
    private GeoCoordinate lat;
    private int key = 0;
    MapMarker place;
    GeoCoordinate my_location;
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private static final String[] RUNTIME_PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE
    };
    private PositioningManager positioningManager;
    private boolean released;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (hasPermissions(this, RUNTIME_PERMISSIONS)) {
            Toast.makeText(getApplicationContext() , "PERMISSION GRANTED" , Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat
                    .requestPermissions(this, RUNTIME_PERMISSIONS, REQUEST_CODE_ASK_PERMISSIONS);
        }
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        coordinates = intent.getStringArrayListExtra("places");
        address = intent.getStringExtra("loc");
        key = intent.getIntExtra("reply", 0);
        final SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(Error error) {
                try{
                if (error == Error.NONE) {
                    map = mapFragment.getMap();
                    map.setCenter(odessa , Map.Animation.NONE);

                    double maxZoom = map.getMaxZoomLevel();
                    double minZoom = map.getMinZoomLevel();
                    map.setZoomLevel((maxZoom + minZoom)*0.5);
                    positioningManager = PositioningManager.getInstance();
                    positioningManager.addListener( new WeakReference<PositioningManager.OnPositionChangedListener>(MapActivity.this));
                    if (positioningManager.start(PositioningManager.LocationMethod.GPS_NETWORK)){
                        map.getPositionIndicator().setVisible(true);
                    } else {
                        Toast.makeText(MapActivity.this, "unable to access location", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    switch (key){
                        case 0:
                            setMapObj(address , odessa);
                            break;
                        case 1:
                            searchMapObj(address);
                            break;
                        case 2:
                            if (!released & coordinates!=null) {
                                for (String i : coordinates) {
                                    searchMapObj(i);
                                }
                            }
                           addPolyLine(coordinates);
                            break;
                    }
                } else {
                    Toast.makeText(getApplicationContext() , " map loading error" , Toast.LENGTH_SHORT).show();
                }
                }catch (NullPointerException ex){
                    Toast.makeText(getApplicationContext() , "we get NULL.." , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addPolyLine(ArrayList<String> arrayList) {
        if (!released) {
            for (String i : arrayList) {
                searchMapObj(i);
            }
        }
          if(released) {
              GeoPolyline geoPolyline = new GeoPolyline(points);
              MapPolyline mapPolyline = new MapPolyline(geoPolyline);
              mapPolyline.setLineColor(Color.GREEN);
              mapPolyline.setLineWidth(10);
              map.addMapObject(mapPolyline);
              released = false;
          }
    }

    private boolean hasPermissions(MapActivity mapActivity, String[] runtimePermissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && runtimePermissions != null) {
            for (String permission : runtimePermissions) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setMapObj(String address, GeoCoordinate coordinate){
        place = new MapMarker();
        GeocodeRequest geocodeRequest = new GeocodeRequest(address);
        geocodeRequest.setSearchArea(coordinate , 5000);
        geocodeRequest.execute(new ResultListener<List<GeocodeResult>>() {
            @Override
            public void onCompleted(List<GeocodeResult> geocodeResults, ErrorCode errorCode) {
                if (errorCode == ErrorCode.NONE){
                    for (GeocodeResult result : geocodeResults) {
                        lat = result.getLocation().getCoordinate();
                        place.setCoordinate(lat);
                        map.addMapObject(place);
                        objectList.add(place);
                    }
                } else {
                    Log.e("ERR GEOCODER" , ""+errorCode);
                }
            }
        });
    }

    private void searchMapObj(String query) {
        SearchRequest searchRequest = new SearchRequest(query);
        searchRequest.setSearchCenter(map.getCenter());
        searchRequest.execute(discoveryResultPageListener);
    }

    private ResultListener<DiscoveryResultPage> discoveryResultPageListener = new ResultListener<DiscoveryResultPage>() {
        @Override
        public void onCompleted(DiscoveryResultPage discoveryResultPage, ErrorCode errorCode) {
            if(errorCode == ErrorCode.NONE) {
                resultList = discoveryResultPage.getItems();
                for (DiscoveryResult item : resultList) {
                    if (item.getResultType() == DiscoveryResult.ResultType.PLACE) {
                        PlaceLink placeLink = (PlaceLink) item;
                        place = new MapMarker();
                        place.setCoordinate(new GeoCoordinate(placeLink.getPosition()));
                        map.addMapObject(place);
                        objectList.add(place);
                        points.add(new GeoCoordinate(placeLink.getPosition()));
                    }
                    released = false;
                }
                released = true;
            } else {
                Toast.makeText(getApplicationContext() , "NULL REQUEST" , Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void clearView(){
        if (!objectList.isEmpty()) {
            map.removeMapObjects(objectList);
            objectList.clear();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearView();
    }

    @Override
    public void onPositionUpdated(PositioningManager.LocationMethod locationMethod, GeoPosition geoPosition, boolean b) {
        my_location = geoPosition.getCoordinate();
    }

    @Override
    public void onPositionFixChanged(PositioningManager.LocationMethod locationMethod, PositioningManager.LocationStatus locationStatus) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
