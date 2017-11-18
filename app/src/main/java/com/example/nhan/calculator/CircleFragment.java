package com.example.nhan.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CircleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CircleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CircleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public CircleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CircleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CircleFragment newInstance(String param1, String param2) {
        CircleFragment fragment = new CircleFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circle, container,false);
        //TODO: init
        final EditText Radius = view.findViewById(R.id.edt_radius_circle);
        final EditText Area = view.findViewById(R.id.edt_area_circle);
        final EditText Perimeter  = view.findViewById(R.id.edt_perimeter_circle);

        Radius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Radius.length()!=0 && Radius.isFocused() == true)
                {
                    float radius = Float.parseFloat(Radius.getText().toString());
                    float area = (float) (radius*radius*Math.PI);
                    float perimeter = (float) (2*radius*Math.PI);
                    Area.setText(String.valueOf(area));
                    Perimeter.setText(String.valueOf(perimeter));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Radius.length() == 0  && Radius.isFocused()==true)
                {
                    Area.setText("0.0");
                    Perimeter.setText("0.0");
                }
            }
        });
        Area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Area.length()!=0 && Area.isFocused() == true)
                {
                    float area = Float.parseFloat(Area.getText().toString());
                    float radius = (float) Math.sqrt(area/Math.PI);
                    float perimeter = (float) (2*radius*Math.PI);

                    Radius.setText(String.valueOf(radius));
                    Perimeter.setText((String.valueOf(perimeter)));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Area.length() == 0 && Area.isFocused()==true)
                {
                    Radius.setText("0.0");
                    Perimeter.setText("0.0");
                }
            }
        });
        Perimeter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Perimeter.length()!=0 && Perimeter.isFocused()==true)
                {
                    float perimeter = Float.parseFloat(Perimeter.getText().toString());
                    float radius = (float) (perimeter/(2*Math.PI));
                    float area = (float) (radius*radius*Math.PI);

                    Radius.setText(String.valueOf(radius));
                    Area.setText(String.valueOf(area));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Perimeter.length()==0 && Perimeter.isFocused() == true)
                {
                    Area.setText("0.0");
                    Radius.setText("0.0");
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Toast.makeText(getActivity(),"Circle", Toast.LENGTH_SHORT).show();
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
        void onFragmentInteraction(Uri uri);
    }
}
