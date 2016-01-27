package tianqiw.scorestatistic.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tianqiw.scorestatistic.model.StudentRecord;
import tianqiw.scorestatistic.util.DBAdapter;
import tianqiw.scorestatistic.util.NavigateHelper;

/**
 * Created by STuotuo.Wen on 2015/11/13.
 *
 * fragment with a form to record input
 */
public class InsertFragment extends Fragment {
    private DBAdapter adapter;
    private Button insertButton;
    private Button skipButton;
    private TextView q1Text;
    private TextView q2Text;
    private TextView q3Text;
    private TextView q4Text;
    private TextView q5Text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DBAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_insert, parent, false);

        q1Text = (TextView) v.findViewById(R.id.q1_input);
        q2Text = (TextView) v.findViewById(R.id.q2_input);
        q3Text = (TextView) v.findViewById(R.id.q3_input);
        q4Text = (TextView) v.findViewById(R.id.q4_input);
        q5Text = (TextView) v.findViewById(R.id.q5_input);

        insertButton = (Button) v.findViewById(R.id.insert_button);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHandler(view);
            }
        });
        skipButton = (Button) v.findViewById(R.id.skip_button);
        skipButton.setOnClickListener(new NavigateHelper(getActivity(), StudentListActivity.class));

        return v;
    }

    private void onClickHandler(View v) {
        String[] qs = new String[5];
        qs[0] = q1Text.getText().toString();
        qs[1] = q2Text.getText().toString();
        qs[2] = q3Text.getText().toString();
        qs[3] = q4Text.getText().toString();
        qs[4] = q5Text.getText().toString();

        List<Double> scores = new ArrayList<Double>();
        for (int i = 0; i < 5; i++) {
            try {
                double score = Double.parseDouble(qs[i]);
                if (score < 0) {
                    score = 0;
                } else if (score > 100) {
                    score = 100;
                }
                scores.add(score);
            } catch (NumberFormatException e) {
                scores.add(0.0);
            }
        }

        if (StudentLab.get(getActivity()).isFull()) {
            Toast.makeText(getActivity(), "DB is full", Toast.LENGTH_LONG).show();
            return;
        }
        StudentRecord temp = null;
        StudentRecord exist = null;
        do {
            temp = new StudentRecord(scores);
            exist = StudentLab.get(getActivity()).getStudentRecord(temp.getSid());
        } while (exist != null);
        StudentLab.get(getActivity()).insertRecord(temp);
        Toast.makeText(getActivity(), "Insert Successful: sid = " + temp.getSid(), Toast.LENGTH_LONG).show();
    }
}
