package tianqiw.geolocationtracker.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import tianqiw.geolocationtracker.exception.CustomException;
import tianqiw.geolocationtracker.exception.Logger;
import tianqiw.geolocationtracker.ui.R;
import tianqiw.geolocationtracker.util.LocationHelper;
import tianqiw.geolocationtracker.util.SmsHelper;

/**
 * Main activity that enable users to input the destination cell number
 * and send current location to the number
 */
public class MainActivity extends AppCompatActivity {
    private static final int NUMBER_LENGTH = 10;
        private Button sendButton;
        private TextView inputNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = (Button) this.findViewById(R.id.send_button);
        inputNumber = (TextView) this.findViewById(R.id.cell_number);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if the input number if valid
                String cellNumber = null;
                try {
                    cellNumber = validateNumber(inputNumber.getText().toString());
                } catch (CustomException e) {
                    Logger.log(e.toString());
                    cellNumber = e.fix();
                }
                // retrieve current location
                LocationHelper locationHelper = new LocationHelper(MainActivity.this);
                String location = null;
                try {
                    location = locationHelper.printLocation();
                } catch (IOException e) {
                    System.err.println(e);
                }

                if (location == null) {
                    Logger.log("No location found.");
                    Toast.makeText(MainActivity.this, "No Location Found!", Toast.LENGTH_SHORT).show();
                } else {
                    // send SMS
                    Toast.makeText(MainActivity.this, "Location Sent to " + cellNumber + ": " + location,
                            Toast.LENGTH_LONG).show();
                    SmsHelper smsHelper = new SmsHelper(MainActivity.this);
                    smsHelper.sendMessage(location, cellNumber);
                }
            }
        });
    }

    private String validateNumber(String inputNumber) throws CustomException {
        if (inputNumber.length() != NUMBER_LENGTH) {
            throw new CustomException("Number length not match: " + inputNumber.length());
        }
        try {
            Long.parseLong(inputNumber);
        } catch (NumberFormatException e) {
            throw new CustomException("Invalid input format: " + inputNumber);
        }
        return inputNumber;
    }
}
