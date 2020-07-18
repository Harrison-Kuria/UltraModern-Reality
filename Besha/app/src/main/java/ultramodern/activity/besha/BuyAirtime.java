package ultramodern.activity.besha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuyAirtime extends AppCompatActivity implements View.OnClickListener{

    Button safaricomprovider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_airtime);

        safaricomprovider=findViewById(R.id.button17);
        safaricomprovider.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==safaricomprovider){
            Intent intent = new Intent(getApplicationContext(),BuyAirtime_Amount.class);
            startActivity(intent);
        }
    }
}