package ultramodern.activity.milkdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class WelcomeSplash extends AppCompatActivity {
TextView textView20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_splash);
        textView20=findViewById(R.id.textView20);
        String name = getIntent().getStringExtra("username");
        textView20.setText(name);

        //sending data
        Intent intent = new Intent(getApplicationContext(),Records_now.class);
        intent.putExtra("username","username");
        startActivity(intent);


        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
