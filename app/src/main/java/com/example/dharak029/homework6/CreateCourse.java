/*
HomeWork06
CreateCourse.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateCourse.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateCourse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateCourse extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String UNAME = "UNAME";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String uname;

    private OnFragmentInteractionListener mListener;

    public CreateCourse() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateCourse.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateCourse newInstance(String param1, String param2) {
        CreateCourse fragment = new CreateCourse();
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
            uname = getArguments().getString(UNAME);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final EditText title = (EditText)getActivity().findViewById(R.id.editCourseManagerTitle);
        final Spinner day = (Spinner)getActivity().findViewById(R.id.spinnerDay);
        final Spinner ampm = (Spinner)getActivity().findViewById(R.id.spinnerAMPM);
        final Spinner semester = (Spinner)getActivity().findViewById(R.id.spinnerSem);
        final EditText hour = (EditText)getActivity().findViewById(R.id.editHour);
        final EditText minute = (EditText)getActivity().findViewById(R.id.editMinute);
        final RadioGroup radioGroup = (RadioGroup)getActivity().findViewById(R.id.radioGroup);
        final TextView txtNoInstructorMsg = (TextView) getActivity().findViewById(R.id.txtDisplayForAddInstructor);

        final Button btnCreate = (Button) getActivity().findViewById(R.id.btnCreate);

        getActivity().findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.reloadCreateCourse();
            }
        });

        getActivity().findViewById(R.id.btnCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(hour.getText().toString()) > 12 || Integer.parseInt(hour.getText().toString()) < 1) || (Integer.parseInt(minute.getText().toString()) > 59 || Integer.parseInt(minute.getText().toString()) < 0))
                {
                    Toast.makeText(getActivity(), "Hours should be between 1-12 and minutes should be 0-59", Toast.LENGTH_SHORT).show();
                }
                else {
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) getActivity().findViewById(selectedId);
                    String time = day.getSelectedItem().toString() + " " + hour.getText() + ":" + minute.getText() + " " + ampm.getSelectedItem().toString();
                    mListener.saveCourse(title.getText().toString(), CourseItemAdapter.instructorObj.getFname() + " " + CourseItemAdapter.instructorObj.getLname(), time, semester.getSelectedItem().toString(), Integer.parseInt(radioButton.getText().toString()), CourseItemAdapter.instructorObj.getImage(), uname);
                }
            }
        });

        DatabaseDataManager dm  = new DatabaseDataManager(getActivity());
        ArrayList<Instructor> instructorArrayList = (ArrayList<Instructor>) dm.getAllInstructor();
        RecyclerView recyclerView = (RecyclerView)getActivity().findViewById(R.id.courseRecyclerView);
        if (instructorArrayList.size()<1)
        {
            recyclerView.setVisibility(View.GONE);
            btnCreate.setVisibility(View.GONE);
        }
        else
        {
            txtNoInstructorMsg.setVisibility(View.GONE);
        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        CourseItemAdapter courseItemAdapter = new CourseItemAdapter(instructorArrayList);
        recyclerView.setAdapter(courseItemAdapter);

        List<String> dayList = new ArrayList<String>();
        dayList.add("Monday");
        dayList.add("Tuesday");
        dayList.add("Wednesday");
        dayList.add("Thursday");
        dayList.add("Friday");
        dayList.add("Saturday");
        dayList.add("Sunday");

        List<String> ampmList = new ArrayList<String>();
        ampmList.add("AM");
        ampmList.add("PM");

        List<String> semesterList = new ArrayList<String>();
        semesterList.add("Fall");
        semesterList.add("Spring");
        semesterList.add("Summer");

        ArrayAdapter<String> dayArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,dayList);
        ArrayAdapter<String> ampmArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,ampmList);
        ArrayAdapter<String> semesterArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,semesterList);

        day.setAdapter(dayArrayAdapter);
        ampm.setAdapter(ampmArrayAdapter);
        semester.setAdapter(semesterArrayAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_course, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (OnFragmentInteractionListener)context;
        }
        catch(ClassCastException e){

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void saveCourse(String title,String instructor,String time,String semester,int credit,byte[] image, String uname);
        void reloadCreateCourse();
    }
}
