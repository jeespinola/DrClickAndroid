package py.org.ideasweb.solumend.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

    // se infla el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_visitante, menu);
        return true;
    }

    // menu de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_ingresar:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);

                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
