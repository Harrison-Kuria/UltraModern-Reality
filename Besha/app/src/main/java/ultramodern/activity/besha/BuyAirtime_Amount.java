package ultramodern.activity.besha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class BuyAirtime_Amount extends AppCompatActivity implements View.OnClickListener {

    Button nextbtn;
    RadioButton self,group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_airtime__amount);

        nextbtn=findViewById(R.id.button18);
        self=findViewById(R.id.selfradiobtn);
        group=findViewById(R.id.groupradiobtn);
        nextbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==nextbtn){
            if (self.isChecked()){
                Intent intent = new Intent(getApplicationContext(),BuyAirtime_EnterPin.class);
                startActivity(intent);
            }
            if (group.isChecked()){
                Intent intent = new Intent(getApplication(),BuyAirtime_select_contacts.class);
                startActivity(intent);
            }
        }
    }
}