package scottychang.newthingtest.viewmodel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Chronometer;

import scottychang.newthingtest.R;
import scottychang.newthingtest.viewmodel.fragment.ChronometerFragment;

public class ViewModelInFragmentActivity extends AppCompatActivity {

    private static final String TAG = ViewModelInFragmentActivity.class.getSimpleName();

    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model_in_fragment);
        Log.d(TAG, "onCreate");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChronometerFragment()).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
