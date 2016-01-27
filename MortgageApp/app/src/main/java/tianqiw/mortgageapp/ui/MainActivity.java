package tianqiw.mortgageapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tianqiw.mortgageapp.model.*;
import tianqiw.mortgageapp.util.*;

/**
 * Created by Tianqi Wen (tianqiw) on 2015/11/6.
 *
 * this class is used to enter all the information for calculation
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // controls
    private TextView priceText;
    private TextView downPayText;
    private TextView termText;
    private TextView rateText;
    private Spinner payYearSpinner;
    private Spinner payMonthSpinner;
    private Button calButton;
    private Button showButton;

    private Model model = new Model();
    private DBHelper db = new DBHelper(this);
    private Arithmetic calculator = new Arithmetic();
    private CustomException handler = new CustomException();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceText = (TextView) findViewById(R.id.price);
        downPayText = (TextView) findViewById(R.id.down);
        termText = (TextView) findViewById(R.id.term);
        rateText = (TextView) findViewById(R.id.interest);
        payYearSpinner = (Spinner) findViewById(R.id.month);
        payMonthSpinner = (Spinner) findViewById(R.id.year);

        List<String> month_list = new ArrayList<String>();
        for (int i = 1; i < 13; i++) {
            month_list.add("" + i);
        }

        List<String> year_list = new ArrayList<String>();
        for (int i = 2015; i < 2050; i++) {
            year_list.add("" + i);
        }

        ArrayAdapter<String> month_adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, month_list);
        payMonthSpinner.setAdapter(month_adapter);

        ArrayAdapter<String> year_adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, year_list);
        payYearSpinner.setAdapter(year_adapter);

        calButton = (Button) findViewById(R.id.calculate_button);
        calButton.setOnClickListener(this);
        showButton = (Button) findViewById(R.id.show_all_button);
        showButton.setOnClickListener(new NavigateHelper(MainActivity.this, ShowRecordActivity.class));
    }

    @Override
    public void onClick(View v) {
        String price = priceText.getText().toString();
        String down = downPayText.getText().toString();
        String term = termText.getText().toString();
        String rate = rateText.getText().toString();

        try {
            int tempPrice = Integer.parseInt(price);
            model.setPurchasePrice(tempPrice);
        } catch (NumberFormatException e) {
            model.setPurchasePrice(handler.handle(1));
        }

        try {
            int tempDown = Integer.parseInt(down);
            model.setDownPayment(tempDown);
        } catch (NumberFormatException e) {
            model.setDownPayment(handler.handle(2));
        }

        try {
            int tempTerm = Integer.parseInt(term);
            model.setMortgageTerm(tempTerm);
        } catch (NumberFormatException e) {
            model.setMortgageTerm(handler.handle(3));
        }

        try {
            Double tempRate = Double.parseDouble(rate);
            model.setInterestRate(tempRate);
        } catch (NumberFormatException e) {
            model.setInterestRate((double) handler.handle(4));
        }

        int tempMonth = Integer.parseInt(payMonthSpinner.getSelectedItem().toString());
        int tempYear = Integer.parseInt(payYearSpinner.getSelectedItem().toString());

        model.setFirstPaymentMonth(tempMonth);
        model.setFirstPaymentYear(tempYear);

        model = calculator.calculateMortgage(model);

        StringBuilder sb = new StringBuilder();
        sb.append("Monthly Payment: ").append(model.getTotalMonthPay()).append('\n');
        sb.append("Payment Amount: ").append(model.getTotalPayment()).append('\n');
        sb.append("Pay Off Data: month: ").append(model.getPayOffMonth()).append(" year: ")
                .append(model.getPayOffYear());
        Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();


        model.setTime(new Date().toString());
        db.insert(model);
    }
}
