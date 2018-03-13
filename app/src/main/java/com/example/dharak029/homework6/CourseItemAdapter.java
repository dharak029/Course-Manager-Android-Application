/*
HomeWork06
CourseItemAdapter.java
Viranchi Deshpande, Dharak Shah
 */

package com.example.dharak029.homework6;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dharak029 on 11/5/2017.
 */

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder>{

    static ArrayList<Instructor>  mData;
    public static Instructor instructorObj;

    public CourseItemAdapter(ArrayList<Instructor> mData) {
        this.mData = mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgInstructor;
        TextView txtName;
        RadioButton radioButton;
        public ViewHolder(View itemView) {
            super(itemView);
            imgInstructor = (ImageView)itemView.findViewById(R.id.imgCourseInstructor);
            txtName = (TextView) itemView.findViewById(R.id.txtCourseInstructorName);
            radioButton = (RadioButton)itemView.findViewById(R.id.rbCourse);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);
        CourseItemAdapter.ViewHolder viewHolder = new CourseItemAdapter.ViewHolder(v);
        return viewHolder;
    }

    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Instructor instructor = mData.get(position);
        holder.imgInstructor.setImageBitmap(BitmapFactory.decodeByteArray(instructor.getImage(), 0, instructor.getImage().length));
        holder.txtName.setText(instructor.getFname()+" "+instructor.getLname());
        holder.radioButton.setTag(position);

        if(position == 0 && holder.radioButton.isChecked())
        {
            lastChecked = holder.radioButton;
            lastCheckedPos = 0;
        }

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton cb = (RadioButton)v;
                int clickedPos = ((Integer)cb.getTag()).intValue();

                if(cb.isChecked())
                {
                    if(lastChecked != null)
                    {
                        lastChecked.setChecked(false);
                    }

                    lastChecked = cb;
                    lastCheckedPos = clickedPos;
                    instructorObj = mData.get(clickedPos);
                }
                else
                    lastChecked = null;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
