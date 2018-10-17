package py.org.ideasweb.solumend.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import butterknife.BindView;
import butterknife.ButterKnife;
import py.org.ideasweb.solumend.R;


public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void inicializar() {

    }

    // se infla el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_visitante, menu);
//        return true;

        final MenuItem alertMenuItem = menu.findItem(R.id.menu_item_ingresar);
        LinearLayout rootView = (LinearLayout) alertMenuItem.getActionView();

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Logger.d("Click en el view");
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
                finish();
            }
        });


        return super.onPrepareOptionsMenu(menu);
    }


}
