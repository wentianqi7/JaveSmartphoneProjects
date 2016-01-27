package tianqiw.scorestatistic.model;

import android.app.IntentService;

import java.util.*;
import java.util.zip.DeflaterOutputStream;

/**
 * Created by STuotuo.Wen on 2015/11/12.
 *
 * model class for student record
 */
public class StudentRecord {
    private int sid;
    private List<Double> scores = new ArrayList<Double>();

    public StudentRecord() {
        sid = 0;
        for (int i = 0; i < 5; i++) {
            scores.add(0.0);
        }
    }

    public StudentRecord(List<Double> scores) {
        this.sid = generateID();
        this.scores = scores;
    }

    public StudentRecord(int sid, List<Double> scores) {
        this.sid = sid;
        this.scores = scores;
    }

    @Override
    public String toString() {
        return Integer.toString(sid);
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public double getScore(int i) {
        return scores.get(i);
    }

    public void setScore(int i, double value) {
        scores.set(i, value);
    }

    public int generateID() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }
}
