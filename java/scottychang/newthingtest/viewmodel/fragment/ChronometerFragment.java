package scottychang.newthingtest.viewmodel.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import scottychang.newthingtest.R;
import scottychang.newthingtest.viewmodel.ChronometerViewModel;

public class ChronometerFragment extends Fragment {

    private static final String TAG = ChronometerFragment.class.getSimpleName();

    private Chronometer chronometer;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chronometer, container, false);

        chronometer = view.findViewById(R.id.chronometer);

        UseViewModel();

        chronometer.start();

        button = view.findViewById(R.id.switch_btn);
        button.setOnClickListener(v -> PushTextFragment());
        return view;
    }

    private void PushTextFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_container, new TextFragment()).addToBackStack(null).commit();

        // Will not store ChronometerFragment
        // fm.beginTransaction().replace(R.id.fragment_container, new TextFragment()).commit();
    }

    private void UseViewModel() {
        // Retain if fragment not to onDetached (When activity replace a new one will cause detach)
        // ChronometerViewModel chronometerViewModel = ViewModelProviders.of(this).get(ChronometerViewModel.class);

        // Retain if activity not to finished
        ChronometerViewModel chronometerViewModel = ViewModelProviders.of(getActivity()).get(ChronometerViewModel.class);

        if (chronometerViewModel.getStartDate() == null) {
            long startTime = SystemClock.elapsedRealtime();
            Log.d(TAG, "onCreate: " + startTime);
            chronometerViewModel.setStartDate(startTime);
            chronometer.setBase(startTime);
        } else {
            chronometer.setBase(chronometerViewModel.getStartDate());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

}
