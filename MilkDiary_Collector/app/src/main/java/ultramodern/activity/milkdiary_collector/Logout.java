package ultramodern.activity.milkdiary_collector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Logout extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth;

    private Button logoutbutton;

    public void onClick(View paramView) {
        if (paramView == this.logoutbutton) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), PhoneNumber.class);
            intent.setFlags(268468224);
            startActivity(intent);
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131427359);
        Button button = (Button)findViewById(2131230796);
        this.logoutbutton = button;
        button.setOnClickListener(this);
    }
}
