package are_we_there_yet.radek.washington.edu.arewethereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by apple on 5/14/15.
 */
public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Toast.makeText(context, intent.getStringExtra("message"), Toast.LENGTH_LONG).show();
        }
    }
}
