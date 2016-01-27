package tianqiw.scorestatistic.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by STuotuo.Wen on 2015/11/12.
 *
 * an activity with a single fragment
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager manager = getSupportFragmentManager();
        Fragment sFragment = manager.findFragmentById(R.id.fragmentContainer);

        if (sFragment == null) {
            sFragment = createFragment();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, sFragment)
                    .commit();
        }
    }
}
