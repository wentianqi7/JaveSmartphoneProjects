package com.example.skyforlife.projectwithactivity.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skyforlife.projectwithactivity.R;
import com.example.skyforlife.projectwithactivity.entities.ProfileLab;
import com.example.skyforlife.projectwithactivity.exception.CustomException;
import com.example.skyforlife.projectwithactivity.model.ProfileRecord;

/**
 * Created by STuotuo.Wen on 2015/12/11.
 */
public class ProfileContentFragment extends Fragment {
    private ProfileLab profileLab;
    private ProfileRecord profile;
    private TextView nameText;
    private TextView locText;
    private TextView emailText;
    private ImageView imageView;
    private Button editButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileLab = ProfileLab.get(getActivity());
        profile = profileLab.readProfile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ui_profile, parent, false);
        nameText = (TextView) v.findViewById(R.id.input_name);
        locText = (TextView) v.findViewById(R.id.input_loc);
        emailText = (TextView) v.findViewById(R.id.input_email);
        imageView = (ImageView) v.findViewById(R.id.profile_image);
        editButton = (Button) v.findViewById(R.id.edit_button);

        nameText.setText(profile.getUsername());
        locText.setText(profile.getLocation());
        emailText.setText(profile.getEmail());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write current profile info into db
                profileLab.deleteProfile(profile.getUsername());

                profile = new ProfileRecord(getProfileText(nameText),
                        getProfileText(locText), getProfileText(emailText), "");
                profileLab.createProfile(profile);
            }
        });

        return v;
    }

    private String getProfileText(TextView v) {
        String text = v.getText().toString();
        try {
            if (text.indexOf(" ") >= 0) {
                throw new CustomException("No space allowed in this filed!",
                        CustomException.ExceptionType.SPACE_IN_REQUEST);
            }
        } catch (CustomException e) {
            text = e.fix(text);
        }
        return text;
    }
}
