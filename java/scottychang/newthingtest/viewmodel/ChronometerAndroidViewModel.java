package scottychang.newthingtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ChronometerAndroidViewModel extends AndroidViewModel {

    /*
        If you need to use context inside your viewmodel you should use AndroidViewModel,
        because it contains the application context (to retrieve the context call getApplication() ),
        otherwise use regular ViewModel.

        https://stackoverflow.com/questions/44148966/androidviewmodel-vs-viewmodel

        But life cycle is still in activity / fragment, not application

    */

    @Nullable
    private Long startDate;

    public ChronometerAndroidViewModel(@NonNull Application application) {
        super(application);
    }

    @Nullable
    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(final long startDate) {
        this.startDate = startDate;
    }
}
