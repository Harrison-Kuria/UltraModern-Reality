package ultramodern.activity.milkdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class BillingSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_splash);

        final Intent intent = new Intent(getApplicationContext(), Billing.class);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                BillingSplash.this.startActivity(intent);
                BillingSplash.this.finish();
            }
        }, 2000);
    }
}
