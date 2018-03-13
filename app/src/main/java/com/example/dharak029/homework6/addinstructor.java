/*
HomeWork06
addinstructor.java
Viranchi Deshpande, Dharak Shah
 */

package com.example.dharak029.homework6;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link addinstructor.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link addinstructor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addinstructor extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public addinstructor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addinstructor.
     */
    // TODO: Rename and change types and number of parameters
    public static addinstructor newInstance(String param1, String param2) {
        addinstructor fragment = new addinstructor();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ImageView imageView = (ImageView)getActivity().findViewById(R.id.imgInstructor);
        final Bitmap bitmap1 = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

        getActivity().findViewById(R.id.btnInstructorRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fname = (EditText)getActivity().findViewById(R.id.editIntrusctorName);
                EditText lname = (EditText)getActivity().findViewById(R.id.editInstructorLname);
                EditText email = (EditText)getActivity().findViewById(R.id.editEmail);
                EditText website = (EditText)getActivity().findViewById(R.id.editWebsite);

                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                if (fname.getText().toString().trim().equals("") ||
                        lname.getText().toString().trim().equals("") ||
                        email.getText().toString().trim().equals("") ||
                        website.getText().toString().trim().equals("") ||
                        bitmap.equals(bitmap1))
                {
                    Toast.makeText(getActivity(), "All inputs are mandatory", Toast.LENGTH_SHORT).show();
                }
                else {
                    mListener.saveInstructor(fname.getText().toString(), lname.getText().toString(), email.getText().toString(), website.getText().toString(), byteArrayOutputStream.toByteArray());
                }
            }
        });

        getActivity().findViewById(R.id.imgInstructor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] items = {"Camara","Gallery"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Choose Option").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePicture, 0);
                        }
                        else{
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto , 1);
                        }
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 0:
                if(resultCode == getActivity().RESULT_OK){
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    ((ImageView)getActivity().findViewById(R.id.imgInstructor)).setImageBitmap(Bitmap.createScaledBitmap(photo, 600, 600, false));
                }

                break;
            case 1:
                if(resultCode == getActivity().RESULT_OK){
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    ((ImageView)getActivity().findViewById(R.id.imgInstructor)).setImageBitmap(Bitmap.createScaledBitmap(photo, 600, 600, false));
                }
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addinstructor, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
           // mListener.onFragmentInteraction(uri);
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
        void saveInstructor(String fname,String lname, String email, String website, byte[] image);
    }
}
