package scottychang.newthingtest.livedata;

import android.arch.lifecycle.LiveData;

public class SimpleLiveData extends LiveData<String>{



    @Override
    protected void onActive() {
        super.onActive();
        // is active
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        // is not active
    }
}
