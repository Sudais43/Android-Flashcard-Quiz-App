package com.example.flashcardapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class
FlashAdapter extends RecyclerView.Adapter<FlashAdapter.ViewHolder> {

    List<DataEntry> dataEntries;
    Context context;

    public FlashAdapter(Context context, List<DataEntry> dataEntries ) {
        this.dataEntries = dataEntries;
        this.context = context;
    }

    @NonNull
    @Override
    public FlashAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View Flashview = inflater.inflate(R.layout.row,parent,false);
        return new ViewHolder(Flashview);
    }


    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.qs.setText(dataEntries.get(position).getQuestion());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(context);
                builder.setMessage("Do you want to delete");
                builder.setTitle("Alert");
                builder.setCancelable(false);

                builder.setPositiveButton("YES", (DialogInterface.OnClickListener) (dialog,which)->{
                    Appdatabase appdatabase =Appdatabase.getInstance(context);
                    appdatabase.taskdao().deleteTask(dataEntries.get(position));
                    dataEntries.remove(position);
                    notifyDataSetChanged();
                });

                builder.setNegativeButton("NO", (DialogInterface.OnClickListener) (dialog,which)->{
                    dialog.cancel();
                });

                AlertDialog alertDialog =builder.create();
                alertDialog.show();


                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataEntries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView qs;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            qs = itemView.findViewById(R.id.qs);
        }
    }
}
