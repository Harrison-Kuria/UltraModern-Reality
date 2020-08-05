package ultramodern.activity.milkdiary_farmer;

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
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_logout);
        Button button = (Button)findViewById(R.id.button4);
        this.logoutbutton = button;
        button.setOnClickListener(this);
    }
}
