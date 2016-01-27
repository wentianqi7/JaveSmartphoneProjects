package tianqiw.scorestatistic.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

import org.w3c.dom.Text;

import java.util.ArrayList;

import tianqiw.scorestatistic.model.StudentRecord;
import tianqiw.scorestatistic.util.DBAdapter;

/**
 * Created by STuotuo.Wen on 2015/11/12.
 *
 * activity to insert student records
 */
public class InsertActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new InsertFragment();
    }
}
