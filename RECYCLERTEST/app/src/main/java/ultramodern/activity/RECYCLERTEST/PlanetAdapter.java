package ultramodern.activity.RECYCLERTEST;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Locale;

public class PlanetAdapter extends RecyclerView.Adapter<MyAdapterHolder> {
    private Context context;

    private ArrayList<Planet> planets;

    public PlanetAdapter(Context paramContext, ArrayList<Planet> paramArrayList) {
        this.context = paramContext;
        this.planets = paramArrayList;
    }

    public int getItemCount() { return this.planets.size(); }

    public void onBindViewHolder(MyAdapterHolder MyAdapterHolder, int Int) { MyAdapterHolder.setDetails((Planet)this.planets.get(Int)); }

    public MyAdapterHolder onCreateViewHolder
            (ViewGroup paramViewGroup, int Int)
    { return new MyAdapterHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_row, paramViewGroup, false)); }
}

class MyAdapterHolder extends RecyclerView.ViewHolder {
    private TextView diameter;

    private TextView distance;

    private TextView gravity;

    private TextView name;

    public MyAdapterHolder(View paramView) {
        super(paramView);
        this.name = (TextView)paramView.findViewById(R.id.name);
        this.distance = (TextView)paramView.findViewById(R.id.distance);
        this.gravity = (TextView)paramView.findViewById(R.id.gravity);
        this.diameter = (TextView)paramView.findViewById(R.id.diameter);
    }

    public void setDetails(Planet paramPlanet) {
        this.name.setText(paramPlanet.getPlanetname());
        this.distance.setText(String.format(Locale.US, "Distance from Sun : %d Million KM", new Object[] { Integer.valueOf(paramPlanet.getDistancefromsun()) }));
        this.gravity.setText(String.format(Locale.US, "Gravity : %d N/kg", new Object[] { Integer.valueOf(paramPlanet.getGravity()) }));
        this.diameter.setText(String.format(Locale.US, "Diameter : %d KM", new Object[] { Integer.valueOf(paramPlanet.getDiameter()) }));
    }
}

