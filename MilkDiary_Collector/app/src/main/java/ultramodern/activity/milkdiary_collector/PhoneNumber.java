package ultramodern.activity.milkdiary_collector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class PhoneNumber extends AppCompatActivity implements View.OnClickListener {
    Button button;

    EditText editText;

    public void onClick(View paramView) {
        if (paramView == this.button) {
            Intent intent = new Intent(getApplicationContext(), PhoneNumberAuthentication.class);
            EditText editText1 = (EditText)findViewById(R.id.editText);
            this.editText = editText1;
            intent.putExtra("phonenumber", editText1.getText().toString());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_phone_number);
        Button button1 = (Button)findViewById(R.id.button);
        this.button = button1;
        button1.setOnClickListener(this);
    }
}
