package tianqiw.vocalartistdisplayer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import tianqiw.vocalartistdisplayer.activities.EmailActivity;
import tianqiw.vocalartistdisplayer.activities.R;
import tianqiw.vocalartistdisplayer.activities.VideoActivity;
import tianqiw.vocalartistdisplayer.activities.WallPaperActivity;
import tianqiw.vocalartistdisplayer.utils.GestureHandler;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 *
 * use next button to show wall paper gallery
 */
public class WallPaperFragment extends Fragment {
    private int index;
    private Button nextButton;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.setOnTouchListener(new GestureHandler(getActivity(), VideoActivity.class, EmailActivity.class));
        index = 0;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wallpaper_fragment, parent, false);

        imageView = (ImageView) v.findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.wall1);

        nextButton = (Button) v.findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index + 1) % 3;
                switch (index) {
                    case 0:
                        imageView.setImageResource(R.drawable.wall1);
                        break;
                    case 1:
                        imageView.setImageResource(R.drawable.wall2);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.wall3);
                }
            }
        });
        return v;
    }
}
