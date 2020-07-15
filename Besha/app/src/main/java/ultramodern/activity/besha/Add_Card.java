package ultramodern.activity.besha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Add_Card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__card);
        BottomNavigationView bottomnav=findViewById(R.id.bottomnav);
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.wallet:
                        Toast.makeText(getApplicationContext(),"My Wallet",Toast.LENGTH_LONG).show();
                        break;

                    case R.id.piechart:
                        Toast.makeText(Add_Card.this, "Chart", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.groups:
                        Toast.makeText(Add_Card.this, "Groups", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }
}