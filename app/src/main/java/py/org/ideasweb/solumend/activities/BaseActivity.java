package py.org.ideasweb.solumend.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import py.org.ideasweb.solumend.R;
import py.org.ideasweb.solumend.models.seguridad.CredentialValues;

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
        try {

            LeakCanary.install(getApplication());
        }catch (Exception e){
            e.printStackTrace();
        }

        setSupportActionBar(mToolbar);
        //se oculta el title por defecto
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // habilitar navigacion hacia atras
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView title =(TextView)mToolbar.findViewById(R.id.text_titulo_toolbar);
        if(CredentialValues.getLoginData()!= null)
        title.setText("Hola, " + CredentialValues.getLoginData().getFirebaseUser().getDisplayName());
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    public abstract int getLayoutId();

    // menu de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_perfil:

                break;
            case R.id.menu_item_about:
                Logger.d("Sobre nosotros");
                break;
            case R.id.menu_item_salir:

                finish();
                break;
            case R.id.menu_item_desconectarme:
                FirebaseAuth.getInstance().signOut();
                CredentialValues.setLoginData(null);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.menu_item_ingresar:
                Intent intent2 = new Intent(this, RegistroActivity.class);
                startActivity(intent2);
                finish();
                break;


            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
