package ultramodern.activity.milkdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhoneNumber extends AppCompatActivity implements View.OnClickListener{
Button button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        button5=findViewById(R.id.button5);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==button5){
            Intent in = new Intent(getApplicationContext(), PhoneNumberAuthentication.class);
            startActivity(in);
        }
    }
}
