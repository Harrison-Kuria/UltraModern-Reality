package ultramodern.activity.milkdiary_collector;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private MyAdapter adapter;

    private FirebaseRecyclerAdapter adapter2;

    private ArrayList<Model> arrayList;

    private Context context;

    private DatabaseReference databaseReference;

    private String name;

    private RecyclerView recyclerView;

    private Toolbar toolbar;

    private void fetch() {
        String str = PreferenceManager.getDefaultSharedPreferences(this).getString("Name", "get");
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance("https://milkdiary-farmer.firebaseio.com").getReference("Collector").child(str).child("Farmer-list");
        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(databaseReference1, new SnapshotParser<Model>() {
            public Model parseSnapshot(DataSnapshot param1DataSnapshot) { return new Model(param1DataSnapshot.getValue().toString()); }
        }).build(); {
            adapter2 = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {
                    holder.setTxtname(model.getName());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View param2View) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                            builder.setTitle("OPTIONS");
                            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface param3DialogInterface, int param3Int) {
                                    if (param3Int != 0) {
                                        if (param3Int != 1) {
                                            if (param3Int == 2) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                                                builder.setMessage("Please contact the Administrator for this support. Use this contact: 0765911279 or Email Address: harrykuria23@gmail.com");
                                                builder.show();
                                            }
                                        } else {
                                            Intent intent = new Intent(Main2Activity.this, UpdateRecord.class);
                                            intent.putExtra("username", name);
                                            Main2Activity.this.startActivity(intent);
                                        }
                                    } else {
                                        //Intent intent = new Intent(Main2Activity.this.this$0, MpesaActivity.class);
                                        //Main2Activity.null.this.this$0.startActivity(intent);
                                    }
                                }
                            };
                            builder.setItems(new String[] { "Pay this farmer", "Update records", "Remove from list" }, onClickListener);
                            builder.show();
                        }
                    });
                }


                @NonNull
                @Override
                public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerrow, parent, false);
                    return new Main2Activity.ViewHolder(view);

                }
            };

            recyclerView.setAdapter(adapter2);

        };

    }

    public void onClick(View paramView) {}

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar3);

        setSupportActionBar(toolbar);
        toolbar.setTitle("Records");
        toolbar.setTitleTextColor(-1);
        getIntent().getStringExtra("username");
        RecyclerView recyclerView1 = (RecyclerView)findViewById(R.id.recycler);
        this.recyclerView = recyclerView1;
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        this.arrayList = new ArrayList();
        this.adapter = new MyAdapter(this, this.arrayList);
        this.recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        this.recyclerView.setAdapter(this.adapter);
        fetch();
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.item, paramMenu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        int i = paramMenuItem.getItemId();
        if (i != 2131230781) {
            if (i == 2131230861)
                startActivity(new Intent(this, Logout.class));
        } else {
            startActivity(new Intent(this, FarmerList.class));
        }
        return true;
    }

    protected void onStart() {
        super.onStart();
        this.adapter2.startListening();
    }

    protected void onStop() {
        super.onStop();
        this.adapter2.stopListening();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View param1View) {
            super(param1View);
            this.name = (TextView)param1View.findViewById(R.id.textView11);
        }

        public void setTxtname(String param1String) { this.name.setText(param1String); }
    }
}
