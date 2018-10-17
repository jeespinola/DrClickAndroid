package py.org.ideasweb.solumend.models.seguridad;

import com.google.firebase.auth.FirebaseUser;

import java.sql.Date;

public class LoginData {

    private FirebaseUser firebaseUser;
    private Date today;


    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }
}
