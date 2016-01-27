package tianqiw.vocalartistdisplayer.activities;

import android.support.v4.app.Fragment;

import tianqiw.vocalartistdisplayer.fragments.MainFragment;
import tianqiw.vocalartistdisplayer.fragments.VideoFragment;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 *
 * activity for play video
 */
public class VideoActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new VideoFragment();
    }
}
