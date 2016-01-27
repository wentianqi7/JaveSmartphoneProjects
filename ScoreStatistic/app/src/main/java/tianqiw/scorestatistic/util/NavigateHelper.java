package tianqiw.scorestatistic.util;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 *
 * helps navigate between activities
 */
public class NavigateHelper implements View.OnClickListener {
    private Context from;
    private Class to;

    public NavigateHelper(Context from, Class to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(from, to);
        from.startActivity(intent);
    }
}
