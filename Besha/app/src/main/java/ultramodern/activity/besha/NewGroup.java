package ultramodern.activity.besha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewGroup extends AppCompatActivity implements View.OnClickListener {

    Button fabfinishbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        fabfinishbutton=findViewById(R.id.finishButton);
        fabfinishbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==fabfinishbutton){
            Intent intent = new Intent(getApplicationContext(),FinishNewGroup.class);
            startActivity(intent);
        }
    }
}