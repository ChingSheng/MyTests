package scottychang.newthingtest;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;

import scottychang.newthingtest.viewmodel.ChronometerViewModel;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);

        // ViewModelProviders 会提供一个新的或者之前已经创建过的 ViewModel
        ChronometerViewModel chronometerViewModel = ViewModelProviders.of(this).get(ChronometerViewModel.class);

        if (chronometerViewModel.getStartDate() == null) {
            // 如果开始时间为 null , 那么这个 ViewModel 是刚被创建的
            long startTime = SystemClock.elapsedRealtime();
            chronometerViewModel.setStartDate(startTime);
            chronometer.setBase(startTime);
        } else {
            // 否则这个 ViewModel 是从 ViewModelProviders 中被检索出来的，所以需要设置刚开始的开始时间
            chronometer.setBase(chronometerViewModel.getStartDate());
        }

        chronometer.start();
    }
}
