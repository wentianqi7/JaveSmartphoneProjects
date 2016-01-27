package tianqiw.scorestatistic.model;

import java.util.*;

/**
 * Created by STuotuo.Wen on 2015/11/12.
 *
 * doing calculations on the data
 */
public class ScoreAnalyzer {
    private List<StudentRecord> srList = new ArrayList<StudentRecord>();

    public ScoreAnalyzer() {

    }

    public void updateList(List<StudentRecord> srList) {
        this.srList = srList;
    }

    public double getHighScore(int i) {
        double high = 0;
        for (StudentRecord sr : srList) {
            high = Math.max(sr.getScore(i), high);
        }
        return high;
    }

    public double getLowScore(int i) {
        double low = 100;
        for (StudentRecord sr : srList) {
            low = Math.min(sr.getScore(i), low);
        }
        return low;
    }

    public double getAverage(int i) {
        double sum = 0;
        for (StudentRecord sr : srList) {
            sum += sr.getScore(i);
        }
        return sum / srList.size();
    }
}
