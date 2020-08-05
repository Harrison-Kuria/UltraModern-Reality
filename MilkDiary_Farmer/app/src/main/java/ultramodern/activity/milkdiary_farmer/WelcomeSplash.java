package ultramodern.activity.milkdiary_farmer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeSplash extends AppCompatActivity {
    private TextView textView9;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_welcome_splash);
        String str = getIntent().getStringExtra("username");
        TextView textView = (TextView)findViewById(R.id.textView9);
        this.textView9 = textView;
        textView.setText(str);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                String str = WelcomeSplash.this.getIntent().getStringExtra("username");
                Intent intent = new Intent(WelcomeSplash.this.getApplicationContext(), MainActivity.class);
                intent.putExtra("username", str);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("TEXT FROM WELCOME SPLASH SENT AS ");
                stringBuilder.append(str);
                Log.d("TRACK", stringBuilder.toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                WelcomeSplash.this.startActivity(intent);
                WelcomeSplash.this.finish();
            }
        },2000);
    }

    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance() != null)
            (new Handler()).postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(WelcomeSplash.this.getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    WelcomeSplash.this.startActivity(intent);
                    WelcomeSplash.this.finish();
                }
            },2000);
    }
}
