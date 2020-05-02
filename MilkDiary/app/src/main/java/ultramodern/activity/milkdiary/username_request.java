package ultramodern.activity.milkdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class username_request extends AppCompatActivity implements View.OnClickListener {
Button button7;
EditText editText;
Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_request);
        editText = findViewById(R.id.editText);
        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==button7){
            String username = editText.getText().toString();
            addToDatabase();
            Intent intent = new Intent(getApplicationContext(),WelcomeSplash.class);
            intent.putExtra("username",username);
            startActivity(intent);
        }
    }
    private void addToDatabase(){
        calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date = day + "/" + month + "/" + year;
        String username = editText.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String,Object> user = new HashMap<>();
        user.put("Date",date);

        //adding new Document
        db.collection(username).document(username +"Database").set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Registration successful", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
