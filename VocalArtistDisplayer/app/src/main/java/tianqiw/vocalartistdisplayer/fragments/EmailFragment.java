package tianqiw.vocalartistdisplayer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import tianqiw.vocalartistdisplayer.activities.MainActivity;
import tianqiw.vocalartistdisplayer.activities.R;
import tianqiw.vocalartistdisplayer.activities.VideoActivity;
import tianqiw.vocalartistdisplayer.activities.WallPaperActivity;
import tianqiw.vocalartistdisplayer.utils.GestureHandler;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 *
 * submit an email to mailing list
 */
public class EmailFragment extends Fragment{
    private Button emailButton;
    private TextView nameText;
    private TextView contentText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.setOnTouchListener(new GestureHandler(getActivity(), WallPaperActivity.class, MainActivity.class));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.email_fragment, parent, false);
        emailButton = (Button) v.findViewById(R.id.email_button);
        nameText = (TextView) v.findViewById(R.id.name_input);
        contentText = (TextView) v.findViewById(R.id.content_input);

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String content = contentText.getText().toString();
                Toast.makeText(getActivity(),
                        "Send successful\nName: " + name + "\nContent: " + content,
                        Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}
