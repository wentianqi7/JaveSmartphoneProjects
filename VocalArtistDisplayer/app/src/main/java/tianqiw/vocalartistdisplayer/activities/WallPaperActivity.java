package tianqiw.vocalartistdisplayer.activities;

import android.support.v4.app.Fragment;

import tianqiw.vocalartistdisplayer.fragments.WallPaperFragment;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 *
 * activity for display wall papers
 */
public class WallPaperActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new WallPaperFragment();
    }
}
