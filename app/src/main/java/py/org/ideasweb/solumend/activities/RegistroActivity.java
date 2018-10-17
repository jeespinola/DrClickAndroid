package py.org.ideasweb.solumend.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import py.org.ideasweb.solumend.R;
import py.org.ideasweb.solumend.models.seguridad.CredentialValues;
import py.org.ideasweb.solumend.models.seguridad.LoginData;

public class RegistroActivity extends BaseActivity {


    private static final String TAG = "GMAIL";
    @BindView(R.id.card_regitro_facebook)
    MaterialCardView cardRegitroFacebook;

    @BindView(R.id.card_registro_gmail)
    MaterialCardView cardRegistroGmail;

    @BindView(R.id.card_regitro_app)
    MaterialCardView cardRegitroApp;

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;

    private static final int RC_SIGN_IN = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setListeners();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();



    }

    private void signInGmail() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);


    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            LoginData login = new LoginData();
                            login.setFirebaseUser( mAuth.getCurrentUser());
                            CredentialValues.setLoginData(login);
                            Intent intent = new Intent(RegistroActivity.this, HomeActivity.class);
                            startActivity(intent);

                        } else {

                            Snackbar.make(getCurrentFocus(), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                          //  enviar todos
                        }
                    }
                });
    }



    private void setListeners() {
        cardRegitroFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform Facebook login

            }
        });

        cardRegistroGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               signInGmail();
            }
        });

        cardRegitroApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* mAuth.createUserWithEmailAndPassword("jaimeferreira11@gmail.com", "123456")
                        .addOnCompleteListener(RegistroActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Logger.d(task.isComplete());
                                Logger.d(task.isSuccessful());
                                Toast.makeText(RegistroActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegistroActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(RegistroActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });*/

            }
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_registro;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Toast.makeText(RegistroActivity.this, "gmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                if (!task.isSuccessful()) {
                    Toast.makeText(RegistroActivity.this, "Authentication failed." + task.getException(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuthWithGoogle(account);
                }
            } catch (ApiException e) {
                Logger.d( "Google sign in failed", e);

            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
