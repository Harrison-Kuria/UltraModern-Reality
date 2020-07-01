package ultramodern.activity.milkdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hbb20.CountryCodePicker;

public class PhoneNumber extends AppCompatActivity implements View.OnClickListener{
Button button5;
EditText phoneNumber;
CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        phoneNumber = findViewById(R.id.phoneNumberInput);
        ccp=findViewById(R.id.ccp);
        button5=findViewById(R.id.button5);
        button5.setOnClickListener(this);


    }
    //String number

    @Override
    public void onClick(View v) {
        if (v==button5){
            String number= phoneNumber.getText().toString();
            Intent in = new Intent(getApplicationContext(), PhoneNumberAuthentication.class);
            in.putExtra("number",number);
            startActivity(in);
        }
    }
    public void onCountryPickerClick(View view) {
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                //Alert.showMessage(RegistrationActivity.this, ccp.getSelectedCountryCodeWithPlus());
                //Alert.showPhoneNumber.this,ccp.getSelectedCountryCodeWithPlus();
                //selected_country_code = ccp.getSelectedCountryCodeWithPlus();
            }
        });
    }
}
