package ultramodern.activity.milkdiary_collector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsernameRequest extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TRACK";

    Task<Void> auth;

    Button button3;

    FirebaseDatabase database;

    DatabaseReference databaseReference;

    EditText editText3;

    SharedPreferences sp;

    SharedPreferences sp1;

    private void goToNextActivity() {
        String str = this.editText3.getText().toString();
        Intent intent = new Intent(getApplicationContext(), WelcomeSplash.class);
        intent.putExtra("username", str);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void onClick(View paramView) {
        String str = this.editText3.getText().toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TEXT RECIEVED SUCCESSFULLY AS ");
        stringBuilder.append(str);
        Log.d("TRACK", stringBuilder.toString());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://milkdiary-farmer.firebaseio.com/");
        this.database = firebaseDatabase;
        DatabaseReference databaseReference1 = firebaseDatabase.getReference("Collector").child(str).child("Farmer-list").push();
        this.databaseReference = databaseReference1;
        this.auth = databaseReference1.setValue("donotclick");
        goToNextActivity();
        this.sp.edit().putBoolean("logged", true).apply();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_username_request);
        this.editText3 = (EditText)findViewById(R.id.editText3);
        Button button = (Button)findViewById(R.id.button3);
        this.button3 = button;
        button.setOnClickListener(this);
        String str = this.editText3.getText().toString();
        this.sp = getSharedPreferences("login", 0);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.sp1 = sharedPreferences;
        this.editText3.setText(sharedPreferences.getString("Name", str));
        this.editText3.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) { UsernameRequest.this.sp1.edit().putString("Name", param1Editable.toString()).commit(); }

            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}

            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
        });
        if (this.sp.getBoolean("logged", false))
            goToNextActivity();
    }
}
