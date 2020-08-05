package ultramodern.activity.milkdiary_farmer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {
    private FirebaseRecyclerAdapter adapter2;

    private ArrayList<Model> arrayList;

    private Context context;

    private DatabaseReference databaseReference;

    private RecyclerView recyclerView;

    public String sp;

    private Toolbar toolbar;

    private void fetch() {
        this.sp = PreferenceManager.getDefaultSharedPreferences(this).getString("Name", "get");
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance("https://milkdiary-farmer.firebaseio.com").getReference("Farmer").child(this.sp).child("Records");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MAIN ACTIVITY: FIREBASE DATABASE GOT CHILD AS ");
        stringBuilder.append(this.sp);
        Log.d("TRACK", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("MAIN ACTIVITY: FIREBASE DATABASE GOT FULL REFERENCE AS ");
        stringBuilder.append(databaseReference1);
        Log.d("TRACK", stringBuilder.toString());
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>((new FirebaseRecyclerOptions.Builder()).setQuery(databaseReference1, new SnapshotParser<Model>() {
            public Model parseSnapshot(DataSnapshot param1DataSnapshot) { return new Model(param1DataSnapshot.getValue().toString()); }
        }).build()) {
            protected void onBindViewHolder(MainActivity.ViewHolder param1ViewHolder, int param1Int, Model param1Model) { param1ViewHolder.setTxtname(param1Model.getName()); }

            public MainActivity.ViewHolder onCreateViewHolder(ViewGroup param1ViewGroup, int param1Int) {
                View view = LayoutInflater.from(param1ViewGroup.getContext()).inflate(R.layout.recycler_row, param1ViewGroup, false);
                return new MainActivity.ViewHolder(view);
            }
        };
        this.adapter2 = firebaseRecyclerAdapter;
        this.recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);
        this.sp = PreferenceManager.getDefaultSharedPreferences(this).getString("Name", "get");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TEXT IN MAIN ACTIVITY RECEIVED SUCCESSFULLY AS ");
        stringBuilder.append(this.sp);
        Log.d("TRACK", stringBuilder.toString());
        Toolbar toolbar1 = (Toolbar)findViewById(R.id.toolbar);
        this.toolbar = toolbar1;
        toolbar1.setTitle("My Records");
        this.toolbar.setTitleTextColor(-1);
        setSupportActionBar(this.toolbar);
        RecyclerView recyclerView1 = (RecyclerView)findViewById(R.id.recycler);
        this.recyclerView = recyclerView1;
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        this.arrayList = new ArrayList();
        this.recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        fetch();
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.menuwindow, paramMenu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        if (paramMenuItem.getItemId() == R.id.item2)
            startActivity(new Intent(this, Logout.class));
        return super.onOptionsItemSelected(paramMenuItem);
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
