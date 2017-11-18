package com.example.nhan.calculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RectangleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RectangleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RectangleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RectangleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RectangleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RectangleFragment newInstance(String param1, String param2) {
        RectangleFragment fragment = new RectangleFragment();
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rectangle, container, false);
        final EditText Side_a = view.findViewById(R.id.edt_side_a);
        final EditText Side_b = view.findViewById(R.id.edt_side_b);
        final EditText Area = view.findViewById(R.id.edt_area);
        final EditText Perimeter = view.findViewById(R.id.edt_perimeter);
        final Button Solve = view.findViewById(R.id.bt_solve_rectangle);
        final Button Clean = view.findViewById(R.id.bt_clean);

        Solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double side_a, side_b, area, perimeter;
                if(Side_a.length()!=0)
                {
                    side_a = Double.parseDouble(Side_a.getText().toString());
                    if (Side_b.length() != 0){
                        side_b = Double.parseDouble(Side_b.getText().toString());
                        area = side_a*side_b;
                        perimeter = 2*(side_a+side_b);

                        if(Area.length() != 0)
                        {
                            if (Perimeter.length() == 0)
                            {
                                if(Double.parseDouble(Area.getText().toString()) - area == 0)
                                    Perimeter.setText(String.valueOf(perimeter));
                                else
                                    Toast.makeText(getActivity(), "Sir, you have to clean something first", Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(getActivity(), "Sir, you have to clean something first", Toast.LENGTH_LONG).show();
                        }
                        else {
                            if (Perimeter.length() == 0)
                            {
                                Area.setText(String.valueOf(area));
                                Perimeter.setText(String.valueOf(perimeter));
                            }
                            else{
                                if(Double.parseDouble(Perimeter.getText().toString()) - perimeter == 0)
                                {
                                    Area.setText(String.valueOf(area));
                                }else
                                    Toast.makeText(getActivity(), "Sir, you have to clean something first", Toast.LENGTH_LONG).show();
                            }
                        }
                    }else {
                        if(Area.length() != 0)
                        {
                            area = Double.parseDouble(Area.getText().toString());
                            side_b = area/side_a;
                            perimeter = 2*(side_a+side_b);

                            if(Perimeter.length() == 0)
                            {
                                Perimeter.setText(String.valueOf(perimeter));
                                Side_b.setText(String.valueOf(side_b));
                            }
                            else
                            {
                                if(Double.parseDouble(Perimeter.getText().toString()) - perimeter != 0)
                                    Toast.makeText(getActivity(), "Sir, you have to clean something first", Toast.LENGTH_LONG).show();
                                else
                                    Side_b.setText(String.valueOf(side_b));
                            }
                        }else{
                            if(Perimeter.length() == 0)
                                Toast.makeText(getActivity(), "Sir, Did you forgot something ?", Toast.LENGTH_LONG).show();
                            else{
                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                side_b = perimeter/2 - side_a;
                                area = side_a*side_b;
                                Side_b.setText(String.valueOf(side_b));
                                Area.setText(String.valueOf(area));
                            }
                        }
                    }
                }
                else {
                    if(Side_b.length() == 0)
                    {
                        if(Area.length() == 0)
                        {
                            if (Perimeter.length() == 0)
                                Toast.makeText(getActivity(), "Sir, you didn't include anything to solve!", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getActivity(), "Sir, Did you forgot something ?", Toast.LENGTH_LONG).show();

                        }else
                        {
                            if (Perimeter.length() == 0)
                                Toast.makeText(getActivity(), "Sir, Did you forgot something ?", Toast.LENGTH_LONG).show();
                            else
                            {
                                area = Double.parseDouble(Area.getText().toString());
                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                side_a = perimeter/4 + Math.sqrt( (perimeter*perimeter)/16 - area);
                                side_b = perimeter/4 - Math.sqrt( (perimeter*perimeter)/16 - area);

                                Side_a.setText(String.valueOf(side_a));
                                Side_b.setText(String.valueOf(side_b));
                            }
                        }
                    }else {
                        side_b = Double.parseDouble(Side_b.getText().toString());
                        if(Area.length() == 0 )
                        {
                            if(Perimeter.length() == 0)
                                Toast.makeText(getActivity(), "Sir, Did you forgot something ?", Toast.LENGTH_LONG).show();
                            else{
                                perimeter = Double.parseDouble(Perimeter.getText().toString());

                                side_a = perimeter/2 - side_b;
                                area = side_a*side_b;
                                Side_a.setText(String.valueOf(side_a));
                                Area.setText(String.valueOf(area));
                            }
                        }else {
                            area = Double.parseDouble(Area.getText().toString());
                            if(Perimeter.length() == 0)
                            {
                                side_a = area/side_b;
                                perimeter = 2*(side_a+side_b);

                                Side_a.setText(String.valueOf(side_a));
                                Perimeter.setText(String.valueOf(perimeter));
                            }
                            else{
                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                if(Double.parseDouble(Perimeter.getText().toString()) - perimeter != 0)
                                    Toast.makeText(getActivity(), "Sir, you have to clean something first", Toast.LENGTH_LONG).show();
                                else{
                                    side_b = Double.parseDouble(Side_b.getText().toString());
                                    area = Double.parseDouble(Area.getText().toString());
                                    side_a = area/side_b;
                                    Side_a.setText(String.valueOf(side_a));
                                }
                            }
                        }
                    }
                }
            }
        });
        Clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Side_a.setText("0");
                Side_b.setText("0");
                Area.setText("0");
                Perimeter.setText("0");
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
            Toast.makeText(getActivity(),"Rectangle", Toast.LENGTH_SHORT).show();
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
