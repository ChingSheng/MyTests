package scottychang.newthingtest.livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import scottychang.newthingtest.R;
import scottychang.newthingtest.livedata.viewmodel.LiveDataTimeDataViewModel;

public class LiveDataActivity extends AppCompatActivity {

    LiveDataTimeDataViewModel timeDataViewModel;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_live_data);

        TextView tv = findViewById(R.id.time_text);

        // Create view model
        timeDataViewModel = ViewModelProviders.of(this).get(LiveDataTimeDataViewModel.class);

        // Get LiveData from model -> use observer pattern (When changed -> UI update)
        timeDataViewModel.getElapsedTime().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long aLong) {
                tv.setText("Time elapsed:" + aLong  + " (second)");
            }
        });
    }
}
