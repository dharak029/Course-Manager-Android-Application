/*
HomeWork06
DisplayCourse.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisplayCourse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayCourse extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Course course;


    public DisplayCourse() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayCourse.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayCourse newInstance(String param1, String param2) {
        DisplayCourse fragment = new DisplayCourse();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            course = (Course) getArguments().getSerializable("COURSE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_course, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView txtTitleVal = (TextView) getView().findViewById(R.id.txtTitleVal);
        TextView txtInstructorVal = (TextView) getView().findViewById(R.id.txtInstructorVal);
        TextView txttimeVal = (TextView) getView().findViewById(R.id.txtTimeVal);
        TextView txtCreditVal = (TextView) getView().findViewById(R.id.txtCreditVal);
        TextView txtSem = (TextView) getView().findViewById(R.id.txtSem);

        txtTitleVal.setText(course.getTitle());
        txtInstructorVal.setText(course.getInstructor());
        txtSem.setText(course.getSemester());
        txttimeVal.setText(course.getTime());
        txtCreditVal.setText(course.getCredit()+"");
    }
}
