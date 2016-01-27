package tianqiw.vocalartistdisplayer.fragments;

import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

import tianqiw.vocalartistdisplayer.activities.R;
import tianqiw.vocalartistdisplayer.activities.SongActivity;
import tianqiw.vocalartistdisplayer.activities.VideoActivity;
import tianqiw.vocalartistdisplayer.activities.WallPaperActivity;
import tianqiw.vocalartistdisplayer.utils.GestureHandler;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 *
 * can play two videos
 */
public class VideoFragment extends Fragment implements SurfaceHolder.Callback {
    private VideoView videoView;
    private MediaRecorder recorder = null;
    private Button startBtn = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.setOnTouchListener(new GestureHandler(getActivity(), SongActivity.class, WallPaperActivity.class));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.video_fragment, parent, false);
        startBtn = (Button) v.findViewById(R.id.bgnBtn);
        Button playRecordingBtn = (Button) v.findViewById(R.id.playRecordingBtn);
        Button stpPlayingRecordingBtn = (Button) v.findViewById(R.id.stpPlayingRecordingBtn);

        videoView = (VideoView) v.findViewById(R.id.videoView);
        final SurfaceHolder holder = videoView.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    playRecording(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        playRecordingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    playRecording(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        stpPlayingRecordingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    stopPlayingRecording();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startBtn.setEnabled(true);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    private void playRecording(int i) {
        MediaController mc = new MediaController(getActivity());
        videoView.setMediaController(mc);
        switch (i) {
            case 0:
                videoView.setVideoURI(Uri.parse("android.resource://tianqiw.vocalartistdisplayer/" + R.raw.video1));
                break;
            case 1:
                videoView.setVideoURI(Uri.parse("android.resource://tianqiw.vocalartistdisplayer/" + R.raw.video2));
            default:
                videoView.setVideoURI(Uri.parse("android.resource://tianqiw.vocalartistdisplayer/" + R.raw.video2));
        }

        videoView.start();
    }

    private void stopPlayingRecording() {
        videoView.stopPlayback();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (recorder != null) {
            recorder.release();
        }
    }
}
