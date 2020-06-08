package ultramodern.activity.milkdiary_collector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Launcher extends AppCompatActivity implements View.OnClickListener {
    Button getstartedbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        getstartedbutton=findViewById(R.id.getstartedbutton);
        getstartedbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==getstartedbutton){
            Intent intent = new Intent(getApplicationContext(), PhoneNumber.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
