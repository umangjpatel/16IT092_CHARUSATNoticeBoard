package io.github.umangjpatel.charusatnoticeboard.ui.splash;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import io.github.umangjpatel.charusatnoticeboard.R;
import io.github.umangjpatel.charusatnoticeboard.ui.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);
        startActivity(HomeActivity.newIntent(this));
        finish();
    }
}
