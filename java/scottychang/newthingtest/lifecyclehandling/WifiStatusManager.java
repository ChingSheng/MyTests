package scottychang.newthingtest.lifecyclehandling;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.util.Log;

public class WifiStatusManager implements LifecycleObserver{

    private static final String TAG = WifiStatusManager.class.getSimpleName();

    private Context context;
    private LifecycleOwner lifecycleOwner;
    private WifiStatusListener listener;

    public WifiStatusManager(@NonNull Context context, @NonNull LifecycleOwner lifecycleOwner, WifiStatusListener listener) {
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.listener = listener;

        // Should add this to observe life cycle (activity), (But how to observe fragment lifecycle ??)
        this.lifecycleOwner.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)   // 先寫的Annotation會先被執行
    public void log() {
        Log.d(TAG, "current state: " + lifecycleOwner.getLifecycle().getCurrentState().name());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME) // Method should public
    public void registerEvent() {
        Log.d(TAG, "registerEvent");
        context.registerReceiver(wifiEventReceiver, new IntentFilter(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)); // Connecting
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE) // Method should public
    public void unRegisterEvent() {
        Log.d(TAG, "unRegisterEvent");
        context.unregisterReceiver(wifiEventReceiver);
    }

    private BroadcastReceiver wifiEventReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION.equals(action)) { // Check for connecting

                SupplicantState state = intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);

                NetworkInfo.DetailedState detailedState = WifiInfo.getDetailedStateOf(state);
                Log.d(TAG, "Supplicant_state_changed_action (DetailedState) : " + detailedState.name());

                if (detailedState == NetworkInfo.DetailedState.DISCONNECTED) {

                    int error_code = intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, 0);
                    if (error_code == WifiManager.ERROR_AUTHENTICATING) {   // Auth fail.

                        if (listener != null) {
                            listener.onStateChange("ErrorAuth");
                        }

                    } else {
                        if (listener != null) {
                            listener.onStateChange("Disconnected");
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.onStateChange(detailedState.name());
                    }
                }
            }
        }
    };


            //================================================================================
    // Listener
    //================================================================================

    interface WifiStatusListener {
        void onStateChange(String stateName);
    }
}
