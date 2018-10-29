
package co.twinger.location;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class RNAndroidLocationServiceModule extends ReactContextBaseJavaModule implements LocationListener {

    private final ReactApplicationContext reactContext;
    private Location mLocation;

    private static final long MIN_TIME = 10;
    private static final float MIN_DISTANCE = 10;
    private static final String TAG = "AndroidLocationService";

    public RNAndroidLocationServiceModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;

        if (ActivityCompat.checkSelfPermission(reactContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(reactContext,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "You should request permission before get location");
            return;
        }

        LocationManager locationManager = (LocationManager) reactContext.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager != null) {
            boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (gpsEnabled) {
                Log.d(TAG, "GPS Enable");
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, this,
                        Looper.getMainLooper());
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this,
                    Looper.getMainLooper()); // You can also use LocationManager.GPS_PROVIDER and
                                             // LocationManager.PASSIVE_PROVIDER
            mLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
    }

    @Override
    public String getName() {
        return "RNAndroidLocationService";
    }

    @Override
    public void onLocationChanged(Location location) {
        this.mLocation = location;
        getLocation();
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

    /*
     * Location Provider as called by JS
     */
    @ReactMethod
    public void getLocation() {
        if (mLocation != null) {
            try {
                double Longitude;
                double Latitude;
                // Receive Longitude / Latitude from (updated) Last Location
                Longitude = mLocation.getLongitude();
                Latitude = mLocation.getLatitude();

                Log.i(TAG, "Location . Longitude: " + Longitude + " Latitude: " + Latitude);

                // Create Map with Parameters to send to JS
                WritableMap params = Arguments.createMap();
                params.putDouble("Longitude", Longitude);
                params.putDouble("Latitude", Latitude);

                // Send Event to JS to update Location
                sendEvent(this.reactContext, "updateLocation", params);
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG, "Location services disconnected.");
            }
        }
    }

    /*
     * Internal function for communicating with JS
     */
    private void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
        if (reactContext.hasActiveCatalystInstance()) {
            reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
        } else {
            Log.i(TAG, "Waiting for CatalystInstance...");
        }
    }

}