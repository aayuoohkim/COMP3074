package gbc.comp3074.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calcButton = findViewById(R.id.calc_button);

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hide the Keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                EditText workedHourInput = findViewById(R.id.worked_hour);
                EditText hourlyRateInput = findViewById(R.id.hourly_rate);

                String workedHourStr = workedHourInput.getText().toString();
                String hourlyRateStr = hourlyRateInput.getText().toString();

                TextView totalPayView = findViewById(R.id.total_payment);
                TextView taxView = findViewById(R.id.tax);

                double workedHour, hourlyRate;
                double pay, totalPay, tax;

                if (hourlyRateStr.isEmpty() || hourlyRateStr.isEmpty()) {
                    totalPayView.setText("Please fill out all the input.");
                    taxView.setText("");
                    return;
                }

                workedHour = Double.parseDouble(workedHourStr);
                hourlyRate = Double.parseDouble(hourlyRateStr);

                if (workedHour <= 40) {
                    pay = workedHour * hourlyRate;
                } else {
                    pay = (workedHour - 40) * hourlyRate * 1.5 + (40 * hourlyRate);
                }

                tax = Math.round(pay * 0.18);
                totalPay = Math.round(pay - tax);

                totalPayView.setText(String.valueOf(totalPay));
                taxView.setText(String.valueOf(tax));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.about_menu) {
            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}