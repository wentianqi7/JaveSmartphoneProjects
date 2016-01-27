package com.example.skyforlife.projectwithactivity.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

/**
 * Created by STuotuo.Wen on 2015/12/10.
 */
public class LocationHelper implements LocationListener {
    private Context context;
    private Location curLocation;

    public LocationHelper(Context context) {
        this.context = context;
    }

    public Location getCurLocation() {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location tempLocation = null;
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // GPS enabled
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                tempLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            } else {
                // location access not granted by user
                Logger.log("Location access not granted by the user", 1);
            }
        } else {
            Logger.log("No GPS Service Enabled", 1);
        }

        return tempLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        curLocation = location;
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
}
