package ultramodern.activity.besha;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;
import com.google.firebase.storage.StorageReference;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class HomeFragment extends Fragment implements View.OnClickListener{
    StorageReference storageReference;

    Button fabhome, recievepaymentbtn, buyairtimebtn, paybtn, sendmoneybtn, buyairtimeicon, recievepaymenticon, payicon, sendmoneyicon;
    CardView popup;
    RecyclerView cardlist;
    public HomeFragment() {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view= inflater.inflate(R.layout.fragment_home,container,false);
        TextView datetoday = view.findViewById(R.id.textView7);
        fabhome = view.findViewById(R.id.fabhome);
        recievepaymentbtn = view.findViewById(R.id.buttonrecievepayment);
        buyairtimebtn = view.findViewById(R.id.buttonbuyairtime);
        paybtn = view.findViewById(R.id.buttonpayhome);
        sendmoneybtn = view.findViewById(R.id.sendmoneybutton);
        buyairtimeicon = view.findViewById(R.id.iconbuyairtime);
        recievepaymenticon = view.findViewById(R.id.iconrecievepayment);
        payicon = view.findViewById(R.id.iconpay);
        sendmoneyicon = view.findViewById(R.id.sendmoneyicon);
        cardlist = view.findViewById(R.id.cardlist);
        popup = view.findViewById(R.id.imagepopup);
        cardlist.setLayoutManager(new LinearLayoutManager(getActivity()));
        popupContent();

        //setting button listeners
        fabhome.setOnClickListener(this);
        recievepaymentbtn.setOnClickListener(this);
        buyairtimebtn.setOnClickListener(this);
        paybtn.setOnClickListener(this);
        sendmoneybtn.setOnClickListener(this);
        buyairtimeicon.setOnClickListener(this);
        recievepaymenticon.setOnClickListener(this);
        payicon.setOnClickListener(this);
        sendmoneyicon.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        String date=day+"/"+month+"/"+year;
        datetoday.setText("Today "+date);

        return view;
    }

    public void visible(){
        buyairtimebtn.setVisibility(View.VISIBLE);
        recievepaymentbtn.setVisibility(View.VISIBLE);
        paybtn.setVisibility(View.VISIBLE);
        sendmoneybtn.setVisibility(View.VISIBLE);
        buyairtimeicon.setVisibility(View.VISIBLE);
        recievepaymenticon.setVisibility(View.VISIBLE);
        payicon.setVisibility(View.VISIBLE);
        sendmoneyicon.setVisibility(View.VISIBLE);
    }
    public void invisible(){
        buyairtimebtn.setVisibility(View.GONE);
        recievepaymentbtn.setVisibility(View.GONE);
        paybtn.setVisibility(View.GONE);
        sendmoneybtn.setVisibility(View.GONE);
        buyairtimeicon.setVisibility(View.GONE);
        recievepaymenticon.setVisibility(View.GONE);
        payicon.setVisibility(View.GONE);
        sendmoneyicon.setVisibility(View.GONE);
    }
    @Override
    public void onClick(View view) {
        if (view==fabhome){
            visible();
        }
        if (view==sendmoneybtn){
            invisible();
            Intent intent = new Intent(getActivity(),Send_Money.class);
            startActivity(intent);
        }
        if (view==buyairtimebtn){
            Intent intent = new Intent(getActivity(),BuyAirtimeAction.class);
            startActivity(intent);
        }
    }

    private void popupContent(){
        DatabaseReference imageReference = FirebaseDatabase.getInstance().getReference("CardImages");
        FirebaseRecyclerOptions<ImageRetrieval> options = new FirebaseRecyclerOptions.Builder<ImageRetrieval>().setQuery(imageReference, new SnapshotParser<ImageRetrieval>() {
            @NonNull
            @Override
            public ImageRetrieval parseSnapshot(@NonNull DataSnapshot snapshot) {
                return new ImageRetrieval(snapshot.getValue().toString());
            }
        }).build();
        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ImageRetrieval, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ImageRetrieval model) {
                //Glide.with(requireActivity()).load(model.getImageURL()).into(holder.cardImage);
                Picasso.with(requireActivity()).setLoggingEnabled(true);
                Picasso.with(requireActivity()).load(model.getImageURL()).into(holder.cardImage);

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.popup_layout,parent,false);

                return new ViewHolder(view);
            }
        };
        cardlist.setAdapter(firebaseRecyclerAdapter);
        Log.d("TRACK","inflate success");
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.cardImage);
        }
    }
}
