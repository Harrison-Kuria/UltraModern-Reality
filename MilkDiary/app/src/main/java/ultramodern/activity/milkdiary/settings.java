package ultramodern.activity.milkdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.UiModeManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final ConstraintLayout layout = findViewById(R.id.layout);
        final CardView cardView1 = findViewById(R.id.cardView1);
        final TextView textView1 = findViewById(R.id.textView1);
        final Switch switch1 = findViewById(R.id.switch1);
        savedInstanceState.putBoolean("MyInt",true);
        
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cardView1.setCardBackgroundColor(Color.BLACK);
                    textView1.setTextColor(Color.WHITE);

                }
                else {
                    cardView1.setCardBackgroundColor(Color.WHITE);
                    textView1.setTextColor(Color.BLACK);
                }

                //savedInstanceState.putBoolean("MyInt",true);
            }

        });


    }


}
