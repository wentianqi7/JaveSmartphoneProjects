package tianqiw.vocalartistdisplayer.fragments;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tianqiw.vocalartistdisplayer.activities.MainActivity;
import tianqiw.vocalartistdisplayer.activities.R;
import tianqiw.vocalartistdisplayer.activities.VideoActivity;
import tianqiw.vocalartistdisplayer.activities.WallPaperActivity;
import tianqiw.vocalartistdisplayer.utils.GestureHandler;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 *
 * can play two songs
 */
public class SongFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private int playbackPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.setOnTouchListener(new GestureHandler(getActivity(), MainActivity.class, VideoActivity.class));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.audio_fragment, parent, false);

        Button startPlayerBtn = (Button) v.findViewById(R.id.startPlayerBtn);
        Button startPlayerBtn2 = (Button) v.findViewById(R.id.startPlayerBtn2);
        Button pausePlayerBtn = (Button) v.findViewById(R.id.pausePlayerBtn);
        Button restartPlayerBtn = (Button) v.findViewById(R.id.restartPlayerBtn);

        startPlayerBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    playLocalAudio(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        startPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //playLocalAudio_UsingDescriptor();
                    playLocalAudio(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pausePlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    playbackPosition = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                }
            }
        });

        restartPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(playbackPosition);
                    mediaPlayer.start();
                }
            }
        });
        return v;
    }

    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void playLocalAudio(int i) throws Exception {
        killMediaPlayer();
        switch (i) {
            case 1:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.music1);
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.music2);
                break;
            default:
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.music1);
        }
        mediaPlayer.start();
    }
}
