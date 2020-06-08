package ultramodern.activity.milkdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import java.util.concurrent.TimeUnit;

public class PhoneNumberAuthentication extends AppCompatActivity implements View.OnClickListener  {
    Button button6;
    EditText editTextCode;
private String verificationId;
private String phoneNumber;
private FirebaseAuth mAuth;
private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_authentication);
        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);
        phoneNumber=getIntent().getStringExtra("number");
        sendVerificationCode(phoneNumber);
        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        editTextCode=findViewById(R.id.editText2);
    }

    @Override
    public void onClick(View v) {
        if (v==button6){
            Intent intent = new Intent(getApplicationContext(),setting_splash.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            //String smsCode = editTextCode.getText().toString();
            //if (smsCode.length() < 6){
            //    editTextCode.setError("Enter valid code!!");
            //}
            //else if (smsCode.length()==0){
            //    editTextCode.setError("This code is required!!");
            //}
            //else{

                //verifyCode(smsCode);
            //}
        }
    }
    private void sendVerificationCode(String number) {
        //progressBar.setVisibility(View.VISIBLE);
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            PhoneNumber numberProto = phoneNumberUtil.parse(phoneNumber, "KE");
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumberUtil.format(numberProto,PhoneNumberUtil.PhoneNumberFormat.E164),
                    60,
                    TimeUnit.SECONDS,
                    TaskExecutors.MAIN_THREAD,
                    mCallbacks);
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
    }
    //verifying code input by the user
    private void verifyCode(String code){

        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId,code);
        //let user sign in
        signInCredential(phoneAuthCredential);
    }
//method to let user sign in
    private void signInCredential(PhoneAuthCredential phoneAuthCredential) {
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId=s;
        }
//code sent by sms
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String smsCode = phoneAuthCredential.getSmsCode();
            if (smsCode != null){
                //progressBar.setVisibility(View.VISIBLE);
                verifyCode(smsCode);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };
}