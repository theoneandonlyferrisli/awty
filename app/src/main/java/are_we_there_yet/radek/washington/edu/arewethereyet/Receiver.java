package are_we_there_yet.radek.washington.edu.arewethereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by apple on 5/14/15.
 */
public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Didn't end up using this class.
        /*if (intent != null) {

            String phoneNumber = intent.getStringExtra("phoneNumber");
            String message = intent.getStringExtra("message");

            try {
                //SmsManager smsManager = SmsManager.getDefault();
                //smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                Log.i("onReceive", "fired");
                Toast.makeText(context, "SMS sent!", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(context, "SMS failed!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }*/
    }
}
