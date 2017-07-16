package com.amber.random.datapoliceukv2.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import com.amber.random.datapoliceukv2.BuildConfig;
import com.amber.random.datapoliceukv2.ui.fragments.ForcesListFragment;

//https://data.police.uk/docs/method/crimes-street-dates/
public class MainActivity extends AppCompatActivity  {
    private static final String CRIMES_TAG = "crimes";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        setupStrictMode();
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
