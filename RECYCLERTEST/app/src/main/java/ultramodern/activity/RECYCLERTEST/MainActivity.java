package ultramodern.activity.RECYCLERTEST;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private PlanetAdapter adapter;

    private ArrayList<Planet> planetArrayList;

    private RecyclerView recyclerView;

    private void createListData() {
        Planet planet = new Planet("Earth", 150, 10, 12750);
        this.planetArrayList.add(planet);
        planet = new Planet("Jupiter", 778, 26, 143000);
        this.planetArrayList.add(planet);
        planet = new Planet("Mars", 228, 4, 6800);
        this.planetArrayList.add(planet);
        planet = new Planet("Pluto", 5900, 1, 2320);
        this.planetArrayList.add(planet);
        planet = new Planet("Venus", 108, 9, 12750);
        this.planetArrayList.add(planet);
        planet = new Planet("Saturn", 1429, 11, 120000);
        this.planetArrayList.add(planet);
        planet = new Planet("Mercury", 58, 4, 4900);
        this.planetArrayList.add(planet);
        planet = new Planet("Neptune", 4500, 12, 50500);
        this.planetArrayList.add(planet);
        planet = new Planet("Uranus", 2870, 9, 52400);
        this.planetArrayList.add(planet);
        this.adapter.notifyDataSetChanged();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView1 = (RecyclerView)findViewById(R.id.recycler);
        this.recyclerView = recyclerView1;
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        this.planetArrayList = new ArrayList();
        this.recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        PlanetAdapter planetAdapter = new PlanetAdapter(this, this.planetArrayList);
        this.adapter = planetAdapter;
        this.recyclerView.setAdapter(planetAdapter);
        createListData();
    }
}
