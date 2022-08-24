package com.exanple.firebaseexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;

public class UserAdapter extends FirebaseRecyclerAdapter<Member, UserAdapter.Holder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UserAdapter(@NonNull FirebaseRecyclerOptions<Member> options) {
        super(options);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data,parent,false);
        return new Holder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull Member model) {
        holder.nameDb.setText(model.getName());
        holder.genderDb.setText(model.getPhone());
    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView nameDb,genderDb;

        public Holder(@NonNull View itemView) {
            super(itemView);
             nameDb = itemView.findViewById(R.id.nameValue);
            genderDb = itemView.findViewById(R.id.genderValue);
        }
    }
}
