package io.github.umangjpatel.charusatnoticeboard.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import io.github.umangjpatel.charusatnoticeboard.R;
import io.github.umangjpatel.charusatnoticeboard.models.Notice;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_NOTICE = "extra_notice_detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Notice notice = (Notice) getIntent().getSerializableExtra(EXTRA_NOTICE);
        setTitle(notice.getCategory());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DetailFragment.newInstance(notice))
                    .commitNow();
        }
    }

    public static Intent newIntent(Context packageContext, Notice notice) {
        Intent intent = new Intent(packageContext, DetailActivity.class);
        intent.putExtra(EXTRA_NOTICE, notice);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
