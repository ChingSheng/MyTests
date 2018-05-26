package scottychang.newthingtest;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Chronometer;

import scottychang.newthingtest.viewmodel.ChronometerViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);

        UseViewModel();

        chronometer.start();
    }

    private void UseViewModel() {
        ChronometerViewModel chronometerViewModel = ViewModelProviders.of(this).get(ChronometerViewModel.class);

        if (chronometerViewModel.getStartDate() == null) {
            long startTime = SystemClock.elapsedRealtime();
            Log.d(TAG, "onCreate: " + startTime);
            chronometerViewModel.setStartDate(startTime);
            chronometer.setBase(startTime);
        } else {
            chronometer.setBase(chronometerViewModel.getStartDate());
        }
    }
}
