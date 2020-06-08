package ultramodern.activity.milkdiary_farmer;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class BlogViewHolder extends RecyclerView.ViewHolder {
    View view;
    public BlogViewHolder(@NonNull View itemView) {
        super(itemView);
        view=itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
