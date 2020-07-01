package ultramodern.activity.milkdiary_collector;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class UpdateRecord extends AppCompatActivity implements View.OnClickListener {
    private EditText amount;

    private Task<Void> auth;

    DatabaseReference databaseReference;

    private FirebaseDatabase firebaseDatabase;

    private Button updaterecordsbutton;

    String value;

    public void firebaseActivity() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int j = calendar.get(2);
        int k = calendar.get(7);
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(k);
        stringBuilder1.append("-");
        stringBuilder1.append(j);
        stringBuilder1.append("-");
        stringBuilder1.append(i);
        String str1 = stringBuilder1.toString();
        EditText editText = (EditText)findViewById(2131230825);
        this.amount = editText;
        String str2 = editText.getText().toString();
        String str3 = PreferenceManager.getDefaultSharedPreferences(this).getString("Name", "get");
        FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance("https://milkdiary-farmer.firebaseio.com/");
        this.firebaseDatabase = firebaseDatabase1;
        this.databaseReference = firebaseDatabase1.getReference("Collector").child(str3).child("Farmer-list");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("UPDATE RECORD GOT FIREBASE FULL REFERENCE SUCCESSFULLY AS ");
        stringBuilder2.append(this.databaseReference);
        Log.d("TRACK", stringBuilder2.toString());
        DatabaseReference databaseReference1 = this.databaseReference.push();
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str1);
        stringBuilder2.append(":          ");
        stringBuilder2.append(str2);
        this.auth = databaseReference1.setValue(stringBuilder2.toString());
        Log.d("TRACK", "RECORDS UPDATED SUCCESSFULLY ");
    }

    public void onClick(View paramView) {
        if (paramView == this.updaterecordsbutton) {
            firebaseActivity();
            Toast.makeText(this, "RECORDS UPDATED SUCCESSFULLY", 0).show();
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427365);
        this.value = PreferenceManager.getDefaultSharedPreferences(this).getString("Name", "get");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TEXT RECEIVED SUCCESSFULLY AS ");
        stringBuilder.append(this.value);
        Log.d("TRACK", stringBuilder.toString());
        this.amount = (EditText)findViewById(2131230825);
        Button button = (Button)findViewById(2131230797);
        this.updaterecordsbutton = button;
        button.setOnClickListener(this);
    }
}
