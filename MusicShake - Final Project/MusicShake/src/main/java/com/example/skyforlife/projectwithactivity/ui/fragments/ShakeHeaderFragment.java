package com.example.skyforlife.projectwithactivity.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skyforlife.projectwithactivity.R;
import com.example.skyforlife.projectwithactivity.entities.ProfileCache;
import com.example.skyforlife.projectwithactivity.entities.ProfileLab;
import com.example.skyforlife.projectwithactivity.entities.SongCache;
import com.example.skyforlife.projectwithactivity.entities.SongLab;
import com.example.skyforlife.projectwithactivity.model.ProfileRecord;
import com.example.skyforlife.projectwithactivity.service.remote.RequestResponse;
import com.example.skyforlife.projectwithactivity.service.remote.RequestSender;
import com.example.skyforlife.projectwithactivity.utils.LocationHelper;
import com.example.skyforlife.projectwithactivity.utils.Logger;
import com.example.skyforlife.projectwithactivity.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by STuotuo.Wen on 2015/12/7.
 */
public class ShakeHeaderFragment extends HeaderFragment implements RequestResponse {
    private static final float THRESHOLD = 12.f;
    private static final float ACCEL_COEFF = 0.9f;
    private static final long SHAKE_INTERVAL = 5000;
    private TextView titleText;
    private String title = TITLE;
    private SensorManager sensorManager;
    private float acceleration;
    private float accelWithGravity;
    private float lastAccel;
    private long prevTimeMills;
    private SongCache songCache;
    private ProfileCache profileCache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(shakeListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        acceleration = 0.f;
        accelWithGravity = SensorManager.GRAVITY_EARTH;
        lastAccel = SensorManager.GRAVITY_EARTH;
        prevTimeMills = 0;

        songCache = new SongCache();
        profileCache = new ProfileCache();

        if (getArguments().getString("username") != null) {
            title = getArguments().getString("username");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.header_item, parent, false);
        titleText = (TextView) v.findViewById(R.id.view_title);
        titleText.setText(title);
        final ProfileRecord tempProfile = profileCache.get(title);
        if (tempProfile != null) {
            titleText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(title);
                    StringBuilder sb = new StringBuilder(tempProfile.getLocation());
                    sb.append("\n").append(tempProfile.getEmail());
                    builder.setMessage(sb.toString()).setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return false;
                }
            });
        }
        return v;
    }

    private void handleShakeEvent() {

        Logger.log("Shaking...", 0);
        LocationHelper locationHelper = new LocationHelper(getActivity());
        Location curLocation = locationHelper.getCurLocation();

        JSONObject songJson = new JSONObject();
        JSONArray songArray;
        try {
            songArray = Utils.sidToJson(SongLab.get(getActivity()).getAllSongs());
            songJson.put("songs", songArray);
        } catch (Exception e) {
            Logger.log(e.toString(), 1);
        }

        JSONObject profJson;
        try {
            profJson = Utils.profileToJson(ProfileLab.get(getActivity()).readProfile());
        } catch (Exception e) {
            profJson = new JSONObject();
            Logger.log(e.toString(), 1);
        }

        String songs = songJson.toString();
        String profile = profJson.toString();
        String query = String.format(SHAKE_QUERY, DNS, curLocation.getLongitude(),
                curLocation.getLatitude(), profile, songs);
        Logger.log(query, 0);

        sendRequest(query);
    }

    private void sendRequest(String query) {
        try {
            RequestSender sender = new RequestSender(getActivity());
            sender.delegate = this;
            sender.execute(query);
        } catch (Exception e) {
            Logger.log(e.toString(), 1);
        }
    }

    @Override
    public void processFinish(String result) {
        if (getActivity() == null) {
            return;
        }

        if (result == null) {
            Toast.makeText(getActivity(), "Request failed.", Toast.LENGTH_SHORT).show();
            return;
        }
        Logger.log(result, 0);

        try {
            // update search result
            Intent intent = new Intent(getActivity(), getActivity().getClass());
            intent.putExtra("username", Utils.cacheProfile(profileCache, result));
            intent.putExtra("search_result", Utils.cacheSongRecord(songCache, result));
            getActivity().startActivity(intent);
        } catch (JSONException e) {
            Logger.log(e.toString(), 1);
            Toast.makeText(getActivity(), "Not matched. Please try again later",
                    Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private final SensorEventListener shakeListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            lastAccel = accelWithGravity;
            accelWithGravity = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = accelWithGravity - lastAccel;
            acceleration = acceleration * ACCEL_COEFF + delta;
            if (acceleration > THRESHOLD && System.currentTimeMillis() - prevTimeMills > SHAKE_INTERVAL) {
                prevTimeMills = System.currentTimeMillis();
                handleShakeEvent();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(shakeListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(shakeListener);
    }
}
