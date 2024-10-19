package com.example.gptlogin;

import static com.example.gptlogin.R.id;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.gptlogin.databinding.ActivityGoogleMapsBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleMapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private ActivityGoogleMapsBinding binding;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastlocation;
    private Marker currentUserLocationMarker;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private boolean isLocationAvailable = false;
    public static final int Request_User_Location_Client = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGoogleMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_parking) {
                    showNearbyParking(); // Show nearby parking locations
                    return true;
                } else if (item.getItemId() == R.id.navigation_parking1) {
                        showNearbyPoliceStations();
                } else {
                    return false;
                }
            return true;
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkUserLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment;
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(id.map);
        mapFragment.getMapAsync(this);
    }



    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    public boolean checkUserLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, Request_User_Location_Client);
            return false;
        } else {
            return true;
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Request_User_Location_Client:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        buildGoogleApiClient();
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest.Builder(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, 1100).build();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                onLocationChanged(locationResult.getLastLocation());
            }
        }, Looper.myLooper());
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        isLocationAvailable = true;
        lastlocation = location;

        if (currentUserLocationMarker != null) {
            currentUserLocationMarker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current user location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        currentUserLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14)); // Set fixed zoom level
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    private void showNearbyParking() {
        if (isLocationAvailable && lastlocation != null) {
            double latitude = lastlocation.getLatitude();
            double longitude = lastlocation.getLongitude();
            String location = latitude + "," + longitude;

            String apiKey =  BuildConfig.MAPS_API_KEY;; // Secure your API key
            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + location + "&radius=1500&type=parking&key=" + apiKey;

            new Thread(() -> {
                try {
                    URL requestUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder jsonResponse = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        jsonResponse.append(line);
                    }

                    reader.close();
                    connection.disconnect();

                    parseParkingJson(jsonResponse.toString());
                } catch (IOException e) {
                    runOnUiThread(() -> Toast.makeText(GoogleMapsActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show());
                }
            }).start();
        } else {
            Toast.makeText(this, "Current location is not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void parseParkingJson(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray results = jsonObject.getJSONArray("results");

            runOnUiThread(() -> {
                mMap.clear(); // Clear existing markers
                if (results.length() == 0) {
                    Toast.makeText(GoogleMapsActivity.this, "No parking areas found", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < results.length(); i++) {
                    JSONObject parking = null;
                    try {
                        parking = results.getJSONObject(i);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    String name = null;
                    try {
                        name = parking.getString("name");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    JSONObject location = null;
                    try {
                        location = parking.getJSONObject("geometry").getJSONObject("location");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    double lat = 0;
                    try {
                        lat = location.getDouble("lat");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    double lng = 0;
                    try {
                        lng = location.getDouble("lng");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    LatLng latLng = new LatLng(lat, lng);
                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(latLng)
                            .title(name)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                    mMap.addMarker(markerOptions);
                }

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastlocation.getLatitude(), lastlocation.getLongitude()), 14));
            });
        } catch (JSONException e) {
            e.printStackTrace();
            runOnUiThread(() -> Toast.makeText(GoogleMapsActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show());
        }
    }private void showNearbyPoliceStations() {
        if (lastlocation != null) {
            double latitude = lastlocation.getLatitude();
            double longitude = lastlocation.getLongitude();
            String location = latitude + "," + longitude;

            String apiKey = BuildConfig.MAPS_API_KEY; // Secure your API key
            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + location + "&radius=1500&type=police&key=" + apiKey;

            new Thread(() -> {
                try {
                    URL requestUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder jsonResponse = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        jsonResponse.append(line);
                    }

                    reader.close();
                    connection.disconnect();

                    parsePoliceJson(jsonResponse.toString());
                } catch (IOException e) {
                    runOnUiThread(() -> Toast.makeText(GoogleMapsActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show());
                }
            }).start();
        } else {
            Toast.makeText(this, "Current location is not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void parsePoliceJson(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray results = jsonObject.getJSONArray("results");

            runOnUiThread(() -> {
                mMap.clear(); // Clear existing markers
                if (results.length() == 0) {
                    Toast.makeText(GoogleMapsActivity.this, "No police stations found", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < results.length(); i++) {
                    JSONObject policeStation = null;
                    try {
                        policeStation = results.getJSONObject(i);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    String name = null;
                    try {
                        name = policeStation.getString("name");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    JSONObject location = null;
                    try {
                        location = policeStation.getJSONObject("geometry").getJSONObject("location");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    double lat = 0;
                    try {
                        lat = location.getDouble("lat");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    double lng = 0;
                    try {
                        lng = location.getDouble("lng");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    LatLng latLng = new LatLng(lat, lng);
                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(latLng)
                            .title(name)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                    mMap.addMarker(markerOptions);
                }

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastlocation.getLatitude(), lastlocation.getLongitude()), 14));
            });
        } catch (JSONException e) {
            e.printStackTrace();
            runOnUiThread(() -> Toast.makeText(GoogleMapsActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show());
        }
    }


}

