package py.org.ideasweb.solumend.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.orhanobut.logger.Logger;

import java.util.Timer;
import java.util.TimerTask;

import py.org.ideasweb.solumend.BuildConfig;
import py.org.ideasweb.solumend.R;
import py.org.ideasweb.solumend.models.seguridad.CredentialValues;
import py.org.ideasweb.solumend.models.seguridad.LoginData;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DELAY = 2000;
    private static final String VERSION_NAME = BuildConfig.VERSION_NAME;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Intent mainIntent = null;
                if(currentUser != null){
                    LoginData login = new LoginData();
                    login.setFirebaseUser(currentUser);
                    CredentialValues.setLoginData(login);
                    mainIntent = new Intent().setClass(SplashActivity.this, HomeActivity.class);

                }else {
                    mainIntent = new Intent().setClass(SplashActivity.this, MainActivity.class);
                    Logger.d( "No tiene usuario");
                }
                startActivity(mainIntent);
                finish();

            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);



    }


    protected void onStart() {
        super.onStart();
         currentUser = mAuth.getCurrentUser();

    }
}

