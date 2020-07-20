package ultramodern.activity.sqlitedatabasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText name;
    public Button queryButton;
    public TextView resultAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        queryButton = findViewById(R.id.query_button);
        resultAddress = findViewById(R.id.result);

        queryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==queryButton){
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
            databaseAccess.open();

            String n = name.getText().toString();
            String address = databaseAccess.getAddress(n);

            resultAddress.setText(address);
            databaseAccess.close();
        }
    }
}