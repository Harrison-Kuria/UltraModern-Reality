package ultramodern.activity.besha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class BanksFragment extends Fragment {

    public BanksFragment() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_banks,container,false);
        Button fab = view.findViewById(R.id.fab);
        fab.bringToFront();
        return view;
    }
}
