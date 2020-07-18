package ultramodern.activity.besha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Complete_new_Group extends AppCompatActivity implements View.OnClickListener{

    Button fab,paybtn, depobtn, borrowbtn, borrowicon, depoicon, payicon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_new__group);

        fab = findViewById(R.id.fab_complete_group);
        paybtn = findViewById(R.id.button6);
        depobtn = findViewById(R.id.button5);
        borrowbtn = findViewById(R.id.button3);
        borrowicon = findViewById(R.id.button8);
        depoicon = findViewById(R.id.button9);
        payicon = findViewById(R.id.button10);

        fab.setOnClickListener(this);
        paybtn.setOnClickListener(this);
        depobtn.setOnClickListener(this);
        borrowbtn.setOnClickListener(this);
        borrowicon.setOnClickListener(this);
        depoicon.setOnClickListener(this);
        payicon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==fab){
            paybtn.setVisibility(View.VISIBLE);
            depobtn.setVisibility(View.VISIBLE);
            borrowbtn.setVisibility(View.VISIBLE);
            borrowicon.setVisibility(View.VISIBLE);
            depoicon.setVisibility(View.VISIBLE);
            payicon.setVisibility(View.VISIBLE);
        }
        if (view==paybtn){
            Intent intent = new Intent(getApplicationContext(),Group_pay.class);
            startActivity(intent);
        }
        if (view==payicon){
            Intent intent = new Intent(getApplicationContext(),Group_pay_icon_activity.class);
            startActivity(intent);
        }
        if (view==depobtn){
            Intent intent = new Intent(getApplicationContext(),Group_deposit_button_activity.class);
            startActivity(intent);
        }
        if (view==depoicon){
            Intent intent = new Intent(getApplicationContext(),Group_depo_icon_activity.class);
            startActivity(intent);
        }
        if (view==borrowbtn){
            Intent intent = new Intent(getApplicationContext(),Group_deposit_button_activity.class);
            startActivity(intent);
        }
        if (view==borrowicon){
            Intent intent = new Intent(getApplicationContext(),Group_borrow_icon_activity.class);
            startActivity(intent);
        }
    }
}