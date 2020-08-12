package ultramodern.activity.besha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ultramodern.activity.besha.Adapter.MessageAdapter;
import ultramodern.activity.besha.Model.Chat;

public class Complete_new_Group extends AppCompatActivity implements View.OnClickListener{

    Button fab,paybtn, depobtn, borrowbtn, borrowicon, depoicon, payicon;
    ImageButton sendtextbutton;
    EditText text_send;
    RecyclerView chatrecyclerView;
    MessageAdapter messageAdapter;
    List<Chat> mChat;
    FirebaseRecyclerAdapter<Chat,ViewHolder> firebaseRecyclerAdapter;

    public static final int MESSAGE_RIGHT=1;
    public static final int MESSAGE_LEFT=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) { ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_new__group);

        fab = findViewById(R.id.fab_complete_group);
        paybtn = findViewById(R.id.button6);
        depobtn = findViewById(R.id.button5);
        borrowbtn = findViewById(R.id.button3);
        borrowicon = findViewById(R.id.button8);
        depoicon = findViewById(R.id.button9);
        payicon = findViewById(R.id.button10);
        sendtextbutton = findViewById(R.id.sendtextButton);
        text_send = findViewById(R.id.text_send);
        chatrecyclerView = findViewById(R.id.chatrecycler);

        chatrecyclerView.setLayoutManager(new LinearLayoutManager(this));


        fab.setOnClickListener(this);
        paybtn.setOnClickListener(this);
        depobtn.setOnClickListener(this);
        borrowbtn.setOnClickListener(this);
        borrowicon.setOnClickListener(this);
        depoicon.setOnClickListener(this);
        payicon.setOnClickListener(this);
        sendtextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    @Override
    public void onClick(View view) {
        if (view==fab){
            paybtn.setVisibility(View.VISIBLE);
            depobtn.setVisibility(View.VISIBLE);
            borrowbtn.setVisibility(View.VISIBLE);
            borrowicon.setVisibility(View.VISIBLE);
            depoicon.setVisibility(View.VISIBLE);
            payicon.setVisibility(View.VISIBLE);
        }
        if (view==paybtn){
            Intent intent = new Intent(getApplicationContext(),Group_pay.class);
            startActivity(intent);
        }
        if (view==payicon){
            Intent intent = new Intent(getApplicationContext(),Group_pay_icon_activity.class);
            startActivity(intent);
        }
        if (view==depobtn){
            Intent intent = new Intent(getApplicationContext(),Group_deposit_button_activity.class);
            startActivity(intent);
        }
        if (view==depoicon){
            Intent intent = new Intent(getApplicationContext(),Group_depo_icon_activity.class);
            startActivity(intent);
        }
        if (view==borrowbtn){
            Intent intent = new Intent(getApplicationContext(),Group_deposit_button_activity.class);
            startActivity(intent);
        }
        if (view==borrowicon){
            Intent intent = new Intent(getApplicationContext(),Group_borrow_icon_activity.class);
            startActivity(intent);
        }
        if (view==sendtextbutton){
            String message = text_send.getText().toString();
            if (!message.equals("")){
                sendmessage(message);
            }
            else {
                Toast.makeText(this, "You can't send empty message", Toast.LENGTH_LONG).show();
            }
            text_send.setText("");
            readMessage();
        }
    }
    private void sendmessage(String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> hashMap = new HashMap<>();
        //hashMap.put("sender",sender);
        //hashMap.put("reciever",reciever);
        hashMap.put("message",message);

        reference.child("Chats").push().setValue(hashMap);
    }

    private void readMessage(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
        FirebaseRecyclerOptions<Chat> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Chat>().setQuery(databaseReference, new SnapshotParser<Chat>() {
            @NonNull
            @Override
            public Chat parseSnapshot(@NonNull DataSnapshot snapshot) {
                return new Chat(snapshot.getValue().toString());
            }
        }).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Chat,ViewHolder>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Chat chat) {
            viewHolder.setText(chat.getMessage());
            }


            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == MESSAGE_RIGHT){
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right,parent,false);
                    return new ViewHolder(view);
                }
                else {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left,parent,false);
                    return new ViewHolder(view);
                }

            }
        };
        chatrecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView showMessage;
        public ImageView profilepic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            showMessage=itemView.findViewById(R.id.showMessage);
            profilepic=itemView.findViewById(R.id.profilepic);
        }
        public void setText(String paramString){
            this.showMessage.setText(paramString);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }
}