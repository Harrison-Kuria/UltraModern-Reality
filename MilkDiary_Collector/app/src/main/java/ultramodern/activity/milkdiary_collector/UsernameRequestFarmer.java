package ultramodern.activity.milkdiary_collector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsernameRequestFarmer extends AppCompatActivity {

    private static final String TAG = "TRACK";
    Task<Void> auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    SharedPreferences sp;
    SharedPreferences sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_request_farmer);

        EditText edittext3farmer = findViewById(R.id.editText3Farmer);
        String str = edittext3farmer.getText().toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TEXT RECIEVED SUCCESSFULLY AS ");
        stringBuilder.append(str);
        Log.d("TRACK", stringBuilder.toString());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://milkdiary-farmer.firebaseio.com/");
        this.database = firebaseDatabase;
        this.auth = firebaseDatabase.getReference("Farmer").child(str).setValue(str);
        this.auth = this.database.getReference("Farmer-list").child(str).setValue(str);
        DatabaseReference databaseReference1 = this.database.getReference("Farmer").child(str).child("Records").push();
        this.databaseReference = databaseReference1;
        this.auth = databaseReference1.setValue("Welcome");
        goToNextActivity();
        this.sp.edit().putBoolean("logged", true).apply();
    }
    private void goToNextActivity() {
        EditText edittext3farmer = findViewById(R.id.editText3Farmer);
        String str = edittext3farmer.getText().toString();
        Intent intent = new Intent(getApplicationContext(), WelcomeSplashFarmer.class);
        intent.putExtra("username", str);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}