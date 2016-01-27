package tianqiw.scorestatistic.ui;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.*;

import tianqiw.scorestatistic.model.ScoreAnalyzer;
import tianqiw.scorestatistic.ui.R;
import tianqiw.scorestatistic.model.StudentRecord;

/**
 * Created by STuotuo.Wen on 2015/11/12.
 *
 * use fragment to add a list of student records
 */
public class StudentListFragment extends ListFragment {
    private List<StudentRecord> students;
    private ScoreAnalyzer sa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Student Scores");

        students = StudentLab.get(getActivity()).getAllRecords();
        sa = new ScoreAnalyzer();
        sa.updateList(students);

        List<StudentRecord> fullList = new ArrayList<StudentRecord>();
        fullList.add(new StudentRecord());
        fullList.addAll(students);
        for (int i = 0; i < 3; i++) {
            fullList.add(new StudentRecord());
        }
        StudentAdapter adapter = new StudentAdapter(fullList);
        setListAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((StudentAdapter) getListAdapter()).notifyDataSetChanged();
    }

    private class StudentAdapter extends ArrayAdapter<StudentRecord> {
        public StudentAdapter(List<StudentRecord> students) {
            super(getActivity(), android.R.layout.simple_list_item_1, students);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_student, null);
            }
            // configure the view for this Student
            StudentRecord c = getItem(position);

            if (position == 0) {
                TextView titleTextView =
                        (TextView) convertView.findViewById(R.id.item_id);
                titleTextView.setText("Stud");

                TextView quiz1View =
                        (TextView) convertView.findViewById(R.id.quiz1_score);
                quiz1View.setText("Q1");

                TextView quiz2View =
                        (TextView) convertView.findViewById(R.id.quiz2_score);
                quiz2View.setText("Q2");

                TextView quiz3View =
                        (TextView) convertView.findViewById(R.id.quiz3_score);
                quiz3View.setText("Q3");

                TextView quiz4View =
                        (TextView) convertView.findViewById(R.id.quiz4_score);
                quiz4View.setText("Q4");

                TextView quiz5View =
                        (TextView) convertView.findViewById(R.id.quiz5_score);
                quiz5View.setText("Q5");
            } else if (position < students.size() + 1) {
                String sid = Integer.toString(c.getSid());
                TextView titleTextView =
                        (TextView) convertView.findViewById(R.id.item_id);
                titleTextView.setText(sid);

                String score1 = Double.toString(c.getScore(0));
                TextView quiz1View =
                        (TextView) convertView.findViewById(R.id.quiz1_score);
                quiz1View.setText(score1);

                String score2 = Double.toString(c.getScore(1));
                TextView quiz2View =
                        (TextView) convertView.findViewById(R.id.quiz2_score);
                quiz2View.setText(score2);

                String score3 = Double.toString(c.getScore(2));
                TextView quiz3View =
                        (TextView) convertView.findViewById(R.id.quiz3_score);
                quiz3View.setText(score3);

                String score4 = Double.toString(c.getScore(3));
                TextView quiz4View =
                        (TextView) convertView.findViewById(R.id.quiz4_score);
                quiz4View.setText(score4);

                String score5 = Double.toString(c.getScore(4));
                TextView quiz5View =
                        (TextView) convertView.findViewById(R.id.quiz5_score);
                quiz5View.setText(score5);
            } else if (position == students.size() + 1) {
                TextView titleTextView =
                        (TextView) convertView.findViewById(R.id.item_id);
                titleTextView.setText("High Score");

                String score1 = Double.toString(sa.getHighScore(0));
                TextView quiz1View =
                        (TextView) convertView.findViewById(R.id.quiz1_score);
                quiz1View.setText(score1);

                String score2 = Double.toString(sa.getHighScore(1));
                TextView quiz2View =
                        (TextView) convertView.findViewById(R.id.quiz2_score);
                quiz2View.setText(score2);

                String score3 = Double.toString(sa.getHighScore(2));
                TextView quiz3View =
                        (TextView) convertView.findViewById(R.id.quiz3_score);
                quiz3View.setText(score3);

                String score4 = Double.toString(sa.getHighScore(3));
                TextView quiz4View =
                        (TextView) convertView.findViewById(R.id.quiz4_score);
                quiz4View.setText(score4);

                String score5 = Double.toString(sa.getHighScore(4));
                TextView quiz5View =
                        (TextView) convertView.findViewById(R.id.quiz5_score);
                quiz5View.setText(score5);
            } else if (position == students.size() + 2) {
                TextView titleTextView =
                        (TextView) convertView.findViewById(R.id.item_id);
                titleTextView.setText("Low Score");

                String score1 = Double.toString(sa.getLowScore(0));
                TextView quiz1View =
                        (TextView) convertView.findViewById(R.id.quiz1_score);
                quiz1View.setText(score1);

                String score2 = Double.toString(sa.getLowScore(1));
                TextView quiz2View =
                        (TextView) convertView.findViewById(R.id.quiz2_score);
                quiz2View.setText(score2);

                String score3 = Double.toString(sa.getLowScore(2));
                TextView quiz3View =
                        (TextView) convertView.findViewById(R.id.quiz3_score);
                quiz3View.setText(score3);

                String score4 = Double.toString(sa.getLowScore(3));
                TextView quiz4View =
                        (TextView) convertView.findViewById(R.id.quiz4_score);
                quiz4View.setText(score4);

                String score5 = Double.toString(sa.getLowScore(4));
                TextView quiz5View =
                        (TextView) convertView.findViewById(R.id.quiz5_score);
                quiz5View.setText(score5);
            } else {
                TextView titleTextView =
                        (TextView) convertView.findViewById(R.id.item_id);
                titleTextView.setText("Average Score");

                String score1 = new DecimalFormat("#.##").format(sa.getAverage(0));
                TextView quiz1View =
                        (TextView) convertView.findViewById(R.id.quiz1_score);
                quiz1View.setText(score1);

                String score2 = new DecimalFormat("#.##").format(sa.getAverage(1));
                TextView quiz2View =
                        (TextView) convertView.findViewById(R.id.quiz2_score);
                quiz2View.setText(score2);

                String score3 = new DecimalFormat("#.##").format(sa.getAverage(2));
                TextView quiz3View =
                        (TextView) convertView.findViewById(R.id.quiz3_score);
                quiz3View.setText(score3);

                String score4 = new DecimalFormat("#.##").format(sa.getAverage(3));
                TextView quiz4View =
                        (TextView) convertView.findViewById(R.id.quiz4_score);
                quiz4View.setText(score4);

                String score5 = new DecimalFormat("#.#").format(sa.getAverage(4));
                TextView quiz5View =
                        (TextView) convertView.findViewById(R.id.quiz5_score);
                quiz5View.setText(score5);
            }

            return convertView;
        }
    }
}
