/*
HomeWork06
InstructorListAdapter.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dharak029 on 11/5/2017.
 */

public class InstructorListAdapter extends RecyclerView.Adapter<InstructorListAdapter.ViewHolder> {
    ArrayList<Instructor> mData;

    public InstructorListAdapter(ArrayList<Instructor> mData) {
        this.mData = mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView instructorName,email;
        ImageView imageView;
        AlertDialog.Builder builder1;
        DatabaseDataManager dm;
        Instructor instructor;

        public ViewHolder(View itemView) {
            super(itemView);
            instructorName = (TextView)itemView.findViewById(R.id.txtInsName);
            email = (TextView)itemView.findViewById(R.id.txtEmail);
            imageView = (ImageView)itemView.findViewById(R.id.imageInstructorItem);
            builder1 = new AlertDialog.Builder(itemView.getContext());
            dm = new DatabaseDataManager(itemView.getContext());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.instructor_item, parent, false);
        InstructorListAdapter.ViewHolder viewHolder = new InstructorListAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Instructor instructor = mData.get(position);
        holder.instructor = instructor;
        holder.instructorName.setText(instructor.getFname()+" "+instructor.getLname());
        holder.email.setText(instructor.getEmail());
        holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(instructor.getImage(), 0, instructor.getImage().length));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.builder1.setMessage("Are you sure you want to delete?");
                holder.builder1.setCancelable(true);

                holder.builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                boolean result = holder.dm.getInstructorDAO().delete(instructor);
                                mData.remove(position);
                                notifyDataSetChanged();
                            }
                        });

                holder.builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = holder.builder1.create();
                alert11.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
