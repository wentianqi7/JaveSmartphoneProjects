package tianqiw.mortgageapp.ui;

import android.content.Intent;
import android.view.View;
import android.content.Context;

/**
 * Created by Tianqi Wen (tianqiw) on 2015/11/6.
 *
 * this class helps navigate from on activity to another
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
