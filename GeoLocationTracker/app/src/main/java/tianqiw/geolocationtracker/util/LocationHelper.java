package tianqiw.geolocationtracker.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import java.io.IOException;
import java.util.Locale;
import java.util.*;

import tianqiw.geolocationtracker.exception.Logger;

/**
 * Created by STuotuo.Wen on 2015/11/19.
 * <p/>
 * get current location of the device using location manager and geocoder
 */
public class LocationHelper implements LocationListener {
    private static final String NOT_FOUND = "Unable to get current location!!!";
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
                Logger.log("Location access not granted by the user!!!");
            }
        } else {
            Logger.log("No GPS Service Enabled!!!");
        }

        return tempLocation;
    }

    public String printLocation() throws IOException {
        curLocation = getCurLocation();
        StringBuilder sb = new StringBuilder();

        if (curLocation == null) {
            sb.append(NOT_FOUND);
        } else {
            String locName = "Not found";
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addressList = geocoder.getFromLocation(curLocation.getLatitude(),
                    curLocation.getLongitude(), 1);
            if (addressList.size() > 0) {
                locName = addressList.get(0).getLocality();
            }
            sb.append("Current location is: ").append(curLocation.getLongitude()).append(", ")
                    .append(curLocation.getLatitude()).append("\nLocation Name: ").append(locName);
        }
        return sb.toString();
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
