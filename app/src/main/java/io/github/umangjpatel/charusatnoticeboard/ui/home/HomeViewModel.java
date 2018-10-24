package io.github.umangjpatel.charusatnoticeboard.ui.home;

import android.app.Application;

import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.github.umangjpatel.charusatnoticeboard.api.NoticeClient;
import io.github.umangjpatel.charusatnoticeboard.api.NoticeService;
import io.github.umangjpatel.charusatnoticeboard.models.Notice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel implements InternetConnectivityListener {

    private final InternetAvailabilityChecker mInternetAvailabilityChecker;

    private NoticeService mNoticeService;

    private MutableLiveData<Boolean> mStatusLiveData;
    private MutableLiveData<List<Notice>> mNoticesLiveData;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        InternetAvailabilityChecker.init(application.getApplicationContext());
        mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance();
        mInternetAvailabilityChecker.addInternetConnectivityListener(this);
        mNoticeService = NoticeClient.getNoticeService();
        mStatusLiveData = new MutableLiveData<>();
        mNoticesLiveData = new MutableLiveData<>();
        fetchNotices();
    }

    public void fetchNotices() {
        //TODO : Fetch data from API
        Call<List<Notice>> call = mNoticeService.getNotices();
        call.enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(@NonNull Call<List<Notice>> call, @NonNull Response<List<Notice>> response) {
                mNoticesLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Notice>> call, @NonNull Throwable t) {
                mNoticesLiveData.setValue(null);
            }
        });
    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {
        mStatusLiveData.setValue(isConnected);
    }

    public MutableLiveData<Boolean> getStatusLiveData() {
        return mStatusLiveData;
    }

    public MutableLiveData<List<Notice>> getNoticesLiveData() {
        return mNoticesLiveData;
    }


    public void getNoticesByTitle(String searchQuery) {
        if (!searchQuery.equals("")) {
            List<Notice> titleNotices = new ArrayList<>();
            for (Notice notice : Objects.requireNonNull(mNoticesLiveData.getValue())) {
                if (notice.getTitle().toLowerCase().contains(searchQuery))
                    titleNotices.add(notice);
            }
            mNoticesLiveData.setValue(titleNotices);
        }

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mInternetAvailabilityChecker.removeAllInternetConnectivityChangeListeners();
    }
}
