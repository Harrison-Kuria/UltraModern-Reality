package ultramodern.activity.milkdiary_collector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.Calendar;
import java.util.Date;

public class UsernameRequest extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "LOG";
    Button button3;
    EditText editText3;
    FirebaseDatabase database;
    Task<Void> auth;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_request);
        button3=findViewById(R.id.button3);
        button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==button3){


            editText3=findViewById(R.id.editText3);
            String name = editText3.getText().toString();
            Log.d(TAG,"TEXT RECIEVED SUCCESSFULLY AS "+name);
            database=FirebaseDatabase.getInstance("https://milkdiary-farmer.firebaseio.com/");
            auth=database.getReference("Collector").child(name).setValue(ServerValue.TIMESTAMP);

            Intent intent = new Intent(getApplicationContext(),WelcomeSplash.class);
            intent.putExtra("username",name);
            startActivity(intent);
        }
    }
}
