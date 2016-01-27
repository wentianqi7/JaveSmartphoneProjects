package tianqiw.vocalartistdisplayer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import tianqiw.vocalartistdisplayer.activities.EmailActivity;
import tianqiw.vocalartistdisplayer.activities.R;
import tianqiw.vocalartistdisplayer.activities.SongActivity;
import tianqiw.vocalartistdisplayer.activities.VideoActivity;
import tianqiw.vocalartistdisplayer.activities.WallPaperActivity;
import tianqiw.vocalartistdisplayer.utils.GestureHandler;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 *
 * show the profile of an artist
 */
public class MainFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.setOnTouchListener(new GestureHandler(getActivity(), EmailActivity.class, SongActivity.class));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, parent, false);

        return v;
    }
}
