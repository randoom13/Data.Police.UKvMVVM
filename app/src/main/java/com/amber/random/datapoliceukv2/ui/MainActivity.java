package com.amber.random.datapoliceukv2.ui;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import com.amber.random.datapoliceukv2.BuildConfig;
import com.amber.random.datapoliceukv2.ui.fragments.ForcesListFragment;

//https://data.police.uk/docs/method/crimes-street-dates/
public class MainActivity extends AppCompatActivity {
    public static final String CRIMES_TAG = "crimes";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupStrictMode();
        if (null == getSupportFragmentManager().findFragmentByTag(CRIMES_TAG))
            getSupportFragmentManager().beginTransaction()
                    .add(new ForcesListFragment(), CRIMES_TAG).commit();
    }

    private void setupStrictMode() {
        StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder()
                .detectAll().penaltyLog();
        if (BuildConfig.DEBUG) {
            builder.penaltyFlashScreen();
        }
        StrictMode.setThreadPolicy(builder.build());
    }
}
