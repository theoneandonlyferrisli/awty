package are_we_there_yet.radek.washington.edu.arewethereyet;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the activity to its controls.
        final EditText phoneNumberBox = (EditText) findViewById(R.id.phone_number);
        final EditText messageBox = (EditText) findViewById(R.id.message);
        final EditText durationBox = (EditText) findViewById(R.id.duration);
        final Button start = (Button) findViewById(R.id.start_and_stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pattern phoneNumberValidator = Pattern.compile("^[0-9]{10}$");
                Pattern durationValidator = Pattern.compile(("^[1-9][0-9]*$"));
                if (!phoneNumberBox.getText().toString().equals("") &&
                    !messageBox.getText().toString().equals("") &&
                    !durationBox.getText().toString().equals("") &&
                    phoneNumberValidator.matcher(phoneNumberBox.getText().toString()).matches() &&
                    durationValidator.matcher(durationBox.getText().toString()).matches()) {
                    start.setText("Stop");
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
