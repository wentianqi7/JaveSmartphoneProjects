package tianqiw.vocalartistdisplayer.activities;

import android.support.v4.app.Fragment;

import tianqiw.vocalartistdisplayer.fragments.SongFragment;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 *
 * activity for play audio
 */
public class SongActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new SongFragment();
    }
}
