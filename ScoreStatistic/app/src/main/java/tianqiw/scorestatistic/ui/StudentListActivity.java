package tianqiw.scorestatistic.ui;

import android.support.v4.app.Fragment;

/**
 * activity to show a list of student record
 */
public class StudentListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new StudentListFragment();
    }
}
