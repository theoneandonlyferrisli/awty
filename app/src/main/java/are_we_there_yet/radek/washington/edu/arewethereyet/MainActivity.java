package are_we_there_yet.radek.washington.edu.arewethereyet;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {
    private PendingIntent pendingIntent;

    BroadcastReceiver alarmReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {

                String phoneNumber = intent.getStringExtra("phoneNumber");
                String message = intent.getStringExtra("message");

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                    Log.i("onReceive", "fired");
                    Toast.makeText(context, "SMS sent!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(context, "SMS failed!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the activity to its controls.
        final EditText phoneNumberBox = (EditText) findViewById(R.id.phone_number);
        final EditText messageBox = (EditText) findViewById(R.id.message);
        final EditText durationBox = (EditText) findViewById(R.id.duration);
        final Button start = (Button) findViewById(R.id.start_and_stop);

        // Set up alarm manager.
        final AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        registerReceiver(alarmReceiver, new IntentFilter("start_the_alarm"));

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pattern phoneNumberValidator = Pattern.compile("^[0-9]{10}$");
                Pattern durationValidator = Pattern.compile(("^[1-9][0-9]*$"));

                // Get corresponding texts.
                String phoneNumber = phoneNumberBox.getText().toString();
                String message = messageBox.getText().toString();
                String duration = durationBox.getText().toString();

                if (start.getText().toString().equalsIgnoreCase("stop")) {
                    manager.cancel(pendingIntent);
                    Toast.makeText(MainActivity.this, "Thanks for you mercy!", Toast.LENGTH_LONG).show();
                    start.setText("Start");
                } else if (!phoneNumber.isEmpty() && !message.isEmpty()
                        /*!phoneNumber.equals("") && !message.equals("") &&
                        !duration.equals("") &&
                        phoneNumberValidator.matcher(phoneNumber).matches() &&
                        durationValidator.matcher(duration).matches()*/) {
                    start.setText("Stop");

                    // Get the time interval from user input.
                    long interval = TimeUnit.MINUTES.
                            toMillis(Integer.parseInt(durationBox.getText().toString()));

                    // Put the message to be displayed in the intent.
                    Intent alarmStart = new Intent();
                    alarmStart.setAction("start_the_alarm");

                    // Start the alarm.
                    alarmStart.putExtra("phoneNumber", /*"(" + phoneNumber.substring(0, 3) + ")"
                            + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6)*/
                            phoneNumber);
                    alarmStart.putExtra("message", message);
                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,
                            alarmStart, 0);
                    manager.setRepeating(AlarmManager.RTC_WAKEUP,
                            System.currentTimeMillis(), interval, pendingIntent);

                    //Display a toast message that signifies the onset of an alarm.
                    Toast.makeText(MainActivity.this,
                            "Message spam stream started. Sit and watch!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please put in correct phone numbers and minutes!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
