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
import androidx.annotation.Nullable;
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

public class BudgetFragment extends Fragment {
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    RecyclerView budgetList;
    Button addBudget;
    public BudgetFragment() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_budget,container,false);
        addBudget = view.findViewById(R.id.addbudget);
        addBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),NewBudget.class);
                startActivity(intent);
            }
        });
        budgetList=view.findViewById(R.id.budgetListDisplay);
        budgetList.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetch();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void fetch() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Budget");
        FirebaseRecyclerOptions<BudgetModel> options = new FirebaseRecyclerOptions.Builder<BudgetModel>().setQuery(reference, new SnapshotParser<BudgetModel>() {
            @NonNull
            @Override
            public BudgetModel parseSnapshot(@NonNull DataSnapshot snapshot) {
                Log.d("TRACK","layout inflated");

                return new BudgetModel(snapshot.getValue().toString());
            }
        }).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<BudgetModel, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull BudgetModel budgetModel) {
                viewHolder.budgetName.setText(budgetModel.getName());
                viewHolder.budgetCategory.setText(budgetModel.getCategory());
                viewHolder.budgetitemAmount.setText(budgetModel.getAmount());
                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }


            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.budgetitem,parent,false);
                return new ViewHolder(view);
            }
        };
        budgetList.setAdapter(firebaseRecyclerAdapter);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView budgetName,budgetitemAmount,budgetCategory;
        private ImageView budgetItempic;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.budgetcard);
            budgetName = itemView.findViewById(R.id.budgetName);
            budgetitemAmount = itemView.findViewById(R.id.budgetitemamount);
            budgetCategory = itemView.findViewById(R.id.budgetCategory);
            budgetItempic = itemView.findViewById(R.id.budgetitempic);

        }
        public ViewHolder(LayoutInflater inflater,ViewGroup container){
            super(inflater.inflate(R.layout.budgetitem,container));

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }

}
