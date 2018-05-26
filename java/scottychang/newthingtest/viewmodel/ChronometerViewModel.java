package scottychang.newthingtest.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

public class ChronometerViewModel extends ViewModel {

    @Nullable
    private Long startDate;

    @Nullable
    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(final long startDate) {
        this.startDate = startDate;
    }
}
