package scottychang.newthingtest.lifecyclehandling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import scottychang.newthingtest.R;

public class WifiStatusActivity extends AppCompatActivity {

    private static final String TAG = WifiStatusActivity.class.getSimpleName();

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_wifi_status);

        textView = findViewById(R.id.wifi_status);

        WifiStatusManager wm = new WifiStatusManager(this,
                                                     this,
                                                     new WifiStatusManager.WifiStatusListener() {
            @Override
            public void onStateChange(String stateName) {
                textView.setText(stateName);
            }
        });
    }
}
