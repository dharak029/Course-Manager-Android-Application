/*
HomeWork06
CourseListAdapter.java
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

/**
 * Created by Viranchi on 11/3/2017.
 */

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder> {

    ArrayList<Course> mData;

    public CourseListAdapter(ArrayList<Course> mData) {
        this.mData = mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCourse;
        TextView txtCourseTitle,txtInstructor,txtTime;
        Course course;
        AlertDialog.Builder builder1;
        DatabaseDataManager dm;

        public ViewHolder(final View itemView) {
            super(itemView);
            imgCourse = (ImageView)itemView.findViewById(R.id.imageCourse);
            txtCourseTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtInstructor = (TextView) itemView.findViewById(R.id.txtInstructor);
            txtTime = (TextView) itemView.findViewById(R.id.txtTime);
            builder1 = new AlertDialog.Builder(itemView.getContext());
            dm = new DatabaseDataManager(itemView.getContext());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("here", course.getUname());
                    ((MainActivity)itemView.getContext()).goToDisplayCourse(course);
                }
            });

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Course course = mData.get(position);
        holder.course = course;
        holder.txtCourseTitle.setText(course.getTitle());
        holder.txtInstructor.setText(course.getInstructor());
        holder.txtTime.setText(course.getTime());
        holder.imgCourse.setImageBitmap(BitmapFactory.decodeByteArray(course.getImage(), 0, course.getImage().length));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.builder1.setMessage("Are you sure you want to delete?");
                holder.builder1.setCancelable(true);

                holder.builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                holder.dm.deleteCourse(course);
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
