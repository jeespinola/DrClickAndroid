package py.org.ideasweb.solumend.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import py.org.ideasweb.solumend.R;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        Icepick.restoreInstanceState(this, savedInstanceState);
        Logger.addLogAdapter(new AndroidLogAdapter());

        if (LeakCanary.isInAnalyzerProcess(this)) return ;
        LeakCanary.install(getApplication());

        setSupportActionBar(mToolbar);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    public abstract int getLayoutId();
}
