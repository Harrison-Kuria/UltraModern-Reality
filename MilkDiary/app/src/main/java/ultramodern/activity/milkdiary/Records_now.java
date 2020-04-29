package ultramodern.activity.milkdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;

public class Records_now extends AppCompatActivity implements View.OnClickListener {
    public static String message;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_now);


        final Database mydatabase = new Database(this);
        final ModelClass[] mymodel = new ModelClass[1];
        Button button2 = findViewById(R.id.button2);
        button = findViewById(R.id.button);
        final EditText editText1 = findViewById(R.id.editText1);
        final TextView textView17 = findViewById(R.id.textView17);

        message = editText1.getText().toString();

        button.setOnClickListener(this);






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent I = new Intent(Intent.ACTION_VIEW);

                I.setData(Uri.parse("smsto:"));
                I.setType("vnd.android-dir/mms-sms");
                I.putExtra("sms_body", "CONFIRMED. Your Milk Sales today was...");
                startActivity(I);
            }
        });


}

    @Override
    public void onClick(View v) {
        if (v == button){
            new Thread(new SendMessage()).start();
        }
    }



    //}
        //}
    private class SendMessage implements Runnable {
        private String mMsg;

        public SendMessage() {
            //mMsg = msg;
        }

        public void run() {
            try {

                Socket client = new Socket("192.168.8.108", 7493);  //connect to server
                DataInputStream dataInputStream = new DataInputStream(System.in);
                DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

                //PrintWriter printwriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())),true);
                ///printwriter.write(message);  //write the message to output stream
                //printwriter.flush();
                //printwriter.close();
                message = dataInputStream.readUTF();
                dataOutputStream.writeUTF(message);
                client.close();   //closing the connection

            } catch (UnknownHostException e) {
                System.out.println(e);
            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }
}
