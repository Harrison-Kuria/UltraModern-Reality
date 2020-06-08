package ultramodern.activity.milkdiary_collector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    FirebaseDatabase db;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         listView = findViewById(R.id.list);


         arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
         listView.setAdapter(arrayAdapter);

         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 //dialog to appear as popup
                 String content[]={"Pay this farmer","Remove from list","Update record"};
                 AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                 //TITLE OF DIALOG
                 builder.setTitle("OPTIONS");
                 builder.setItems(content, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         if (which == 0){

                         }
                         else if (which == 1){
                             FirebaseDatabase secondary = FirebaseDatabase.getInstance("https://milkdiary-farmer.firebaseio.com");
                             databaseReference=secondary.getReference("Users");
                             databaseReference.addValueEventListener(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                     dataSnapshot.getRef().removeValue();
                                 }

                                 @Override
                                 public void onCancelled(@NonNull DatabaseError databaseError) {

                                 }
                             });

                         }
                         else{
                             Intent intent = new Intent(MainActivity.this,UpdateRecord.class);
                             startActivity(intent);
                         }
                     }
                 });
                 builder.show();


             }
         });



         FirebaseOptions options = new FirebaseOptions.Builder()
                .setProjectId("milkdiary-farmer")
                 .setApplicationId("1:874069115320:android:e9492a6c95631aaac54f4d")
                .setApiKey("AIzaSyBQ1265BoFGFpzRoLtInUNfFpC5OBUXpB4")
                //.setDatabaseUrl("https://milkdiary-collector.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(this,options,"secondary");

        FirebaseApp firebaseApp = FirebaseApp.getInstance("secondary");
        FirebaseDatabase secondary = FirebaseDatabase.getInstance("https://milkdiary-farmer.firebaseio.com");
        databaseReference=secondary.getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

}
