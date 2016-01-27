package tianqiw.vocalartistdisplayer.activities;

import android.support.v4.app.Fragment;

import tianqiw.vocalartistdisplayer.fragments.EmailFragment;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 *
 * activity for mailing list email
 */
public class EmailActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new EmailFragment();
    }
}
