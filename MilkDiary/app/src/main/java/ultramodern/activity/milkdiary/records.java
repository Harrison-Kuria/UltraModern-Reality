package ultramodern.activity.milkdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class records extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        final Intent intent = new Intent(getApplicationContext(), Records_now.class);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                records.this.startActivity(intent);
                records.this.finish();
            }
        }, 2000);

    }
}
