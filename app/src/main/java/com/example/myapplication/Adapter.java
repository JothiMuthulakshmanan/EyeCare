package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class Adapter extends FirebaseRecyclerAdapter<
        user,Adapter.personsViewholder> {

    Context context;
    ArrayList<user> list;

    public Adapter(Context context, @NonNull FirebaseRecyclerOptions<user> options)
    {
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public personsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.blog_layout, parent, false);
        return new Adapter.personsViewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull personsViewholder holder, int position, @NonNull user model) {
       if(model!=null) {
           holder.tit.setText(model.getText1());
           holder.con.setText(model.getText2());
       }
    }


    class personsViewholder
            extends RecyclerView.ViewHolder {
        TextView tit, con;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);

            tit
                    = itemView.findViewById(R.id.tit);
            con = itemView.findViewById(R.id.con);
        }
    }

}
