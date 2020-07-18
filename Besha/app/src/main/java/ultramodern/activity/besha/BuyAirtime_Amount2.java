package ultramodern.activity.besha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuyAirtime_Amount2 extends AppCompatActivity implements View.OnClickListener{

    Button nextbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_airtime__amount2);

        nextbtn=findViewById(R.id.button25);
        nextbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==nextbtn){
            Intent intent = new Intent(getApplicationContext(),BuyAirtime_EnterPin.class);
            startActivity(intent);
        }
    }
}