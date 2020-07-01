package ultramodern.activity.milkdiary_collector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class PhoneNumberAuthentication extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth auth;

    Button button2;

    EditText editText2;

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        public void onCodeAutoRetrievalTimeOut(String param1String) { super.onCodeAutoRetrievalTimeOut(param1String); }

        public void onCodeSent(String param1String, PhoneAuthProvider.ForceResendingToken param1ForceResendingToken) {
            super.onCodeSent(param1String, param1ForceResendingToken);
            PhoneNumberAuthentication.this.verificationId = param1String;
        }

        public void onVerificationCompleted(PhoneAuthCredential param1PhoneAuthCredential) {}

        public void onVerificationFailed(FirebaseException param1FirebaseException) {}
    };

    String verificationId;

    private void signInWithPhoneAuthCredential(PhoneAuthCredential paramPhoneAuthCredential) { this.auth.signInWithCredential(paramPhoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        public void onComplete(Task<AuthResult> param1Task) {
            if (param1Task.isSuccessful()) {
                Intent intent = new Intent(PhoneNumberAuthentication.this.getApplicationContext(), gettingready.class);
                PhoneNumberAuthentication.this.startActivity(intent);
            }
        }
    }); }

    private void verification(String paramString) { PhoneAuthProvider.getInstance().verifyPhoneNumber(paramString, 60L, TimeUnit.SECONDS, this, this.mCallbacks); }

    private void verifyCode(String paramString) { signInWithPhoneAuthCredential(PhoneAuthProvider.getCredential(this.verificationId, paramString)); }

    public void onClick(View paramView) { verifyCode(this.editText2.getText().toString()); }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_phone_number_authentication);
        EditText editText = (EditText)findViewById(R.id.editText2);
        this.editText2 = editText;
        if (editText.length() == 0)
            this.editText2.setText("Code required");
        Button button = (Button)findViewById(R.id.button2);
        this.button2 = button;
        button.setOnClickListener(this);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        this.auth = firebaseAuth;
        firebaseAuth.setLanguageCode("fr");
        verification(getIntent().getStringExtra("phonenumber"));
    }
}
