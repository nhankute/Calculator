package com.example.nhan.calculator;

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

import java.util.zip.DeflaterOutputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TriangleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TriangleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TriangleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TriangleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TriangleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TriangleFragment newInstance(String param1, String param2) {
        TriangleFragment fragment = new TriangleFragment();
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
        View view = inflater.inflate(R.layout.fragment_triangle, container, false);
        final EditText Side_a = view.findViewById(R.id.edt_side_a_triangle);
        final EditText Side_b = view.findViewById(R.id.edt_side_b_triangle);
        final EditText Side_c = view.findViewById(R.id.edt_side_c_triangle);
        final EditText Angle_a = view.findViewById(R.id.edt_angle_a);
        final EditText Angle_b = view.findViewById(R.id.edt_angle_b);
        final EditText Angle_c = view.findViewById(R.id.edt_angle_c);
        final EditText Area = view.findViewById(R.id.edt_area_triangle);
        final EditText Perimeter = view.findViewById(R.id.edt_perimeter_triangle);
        final EditText Ha = view.findViewById(R.id.edt_ha);

        final Button Solve = view.findViewById(R.id.bt_solve_triangle);
        final Button Clean = view.findViewById(R.id.bt_clean_triangle);

        Solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double side_a, side_b, side_c, angle_A, angle_B, angle_C, area, perimeter, ha;
                if(Side_a.length() == 0)
                {
                    if(Side_b.length() == 0)
                    {
                        if(Side_c.length() == 0)
                        {
                            if (Angle_a.length() == 0)
                            {
                                if (Angle_b.length() == 0)
                                {
                                    if (Angle_c.length() == 0)
                                    {
                                        if (Area.length() == 0)
                                        {
                                            if(Perimeter.length() == 0 && Ha.length() == 0)
                                                Toast.makeText(getActivity(), "Sir, we got nothing to solve !", Toast.LENGTH_LONG).show();
                                            else
                                                Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            area = Double.parseDouble(Area.getText().toString());
                                            if (Perimeter.length() == 0)
                                            {
                                                if(Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else{
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    Side_a.setText(String.valueOf(side_a));
                                                }
                                            }
                                            else{
                                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                                if (Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else {
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    Side_a.setText(String.valueOf(side_a));
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        angle_C = Double.parseDouble(Angle_c.getText().toString());
                                        if (Area.length() == 0)
                                        {
                                            Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            area = Double.parseDouble(Area.getText().toString());
                                            if (Perimeter.length() == 0)
                                            {
                                                if(Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else{
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    side_b = (2*area)/(side_a*Math.sin(angle_C));
                                                    side_c = Math.sqrt( side_a*side_a + side_b*side_b - 2*side_a*side_b*Math.cos(angle_C));
                                                    perimeter = side_a + side_b + side_c;
                                                    angle_B = Math.asin( (side_b*Math.sin(angle_C)) / side_c);
                                                    angle_A = Math.PI - angle_B - angle_C;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Angle_b.setText(String.valueOf(angle_B));
                                                    Perimeter.setText(String.valueOf(perimeter));
                                                }
                                            }
                                            else{
                                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                                if (Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else {
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    side_b = (2*area)/(side_a*Math.sin(angle_C));
                                                    side_c = Math.sqrt( side_a*side_a + side_b*side_b - 2*side_a*side_b*Math.cos(angle_C));
                                                    angle_B = Math.asin( (side_b*Math.sin(angle_C)) / side_c);
                                                    angle_A = Math.PI - angle_B - angle_C;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Angle_b.setText(String.valueOf(angle_B));
                                                }
                                            }
                                        }
                                    }//Angle_C
                                }
                                else {
                                    angle_B = Double.parseDouble(Angle_b.getText().toString());
                                    if (Angle_c.length() == 0)
                                    {
                                        if (Area.length() == 0)
                                        {
                                                Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            area = Double.parseDouble(Area.getText().toString());
                                            if (Perimeter.length() == 0)
                                            {
                                                if(Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else{
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    side_c = (2*area)/(side_a*Math.sin(angle_B));
                                                    side_b = Math.sqrt( side_a*side_a + side_c*side_c - 2*side_a*side_c*Math.cos(angle_B));
                                                    perimeter = side_a + side_b + side_c;
                                                    angle_A = Math.asin( (side_a*Math.sin(angle_B)) / side_b);
                                                    angle_C = Math.PI - angle_A - angle_B;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Perimeter.setText(String.valueOf(perimeter));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Angle_c.setText(String.valueOf(angle_C));
                                                }
                                            }
                                            else{
                                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                                if(Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else{
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    side_c = (2*area)/(side_a*Math.sin(angle_B));
                                                    side_b = Math.sqrt( side_a*side_a + side_c*side_c - 2*side_a*side_c*Math.cos(angle_B));
                                                    angle_A = Math.asin( (side_a*Math.sin(angle_B)) / side_b);
                                                    angle_C = Math.PI - angle_A - angle_B;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Angle_c.setText(String.valueOf(angle_C));
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        angle_C = Double.parseDouble(Angle_c.getText().toString());
                                        if (Area.length() == 0)
                                        {
                                            if (Perimeter.length() == 0)
                                            {
                                                if (Ha.length() == 0)
                                                {
                                                    angle_A = Math.PI - angle_B - angle_C;
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                }
                                                else {
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    angle_A = Math.PI - angle_B - angle_C;
                                                    side_a = ha/Math.tan(angle_B) + ha/Math.tan(angle_C);
                                                    area = 1/2 * side_a*ha;
                                                    side_c = (2*area)/(side_a*Math.sin(angle_B));
                                                    side_b = Math.sqrt( side_a*side_a + side_c*side_c - 2*side_a*side_c*Math.cos(angle_B));
                                                    perimeter = side_a + side_b + side_c;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Area.setText(String.valueOf(area));
                                                    Perimeter.setText(String .valueOf(perimeter));
                                                }
                                            }
                                            else {
                                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                                if (Ha.length() == 0)
                                                {
                                                    angle_A = Math.PI - angle_B - angle_C;
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                }
                                                else {
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    angle_A = Math.PI - angle_B - angle_C;
                                                    side_a = ha/Math.tan(angle_B) + ha/Math.tan(angle_C);
                                                    area = 1/2 * side_a*ha;
                                                    side_c = (2*area)/(side_a*Math.sin(angle_B));
                                                    side_b = Math.sqrt( side_a*side_a + side_c*side_c - 2*side_a*side_c*Math.cos(angle_B));

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Area.setText(String.valueOf(area));
                                                }
                                            }
                                        }
                                        else {
                                            area = Double.parseDouble(Area.getText().toString());
                                            if (Perimeter.length() == 0)
                                            {
                                                if(Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else{
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    side_b = (2*area)/(side_a*Math.sin(angle_C));
                                                    side_c = Math.sqrt( side_a*side_a + side_b*side_b - 2*side_a*side_b*Math.cos(angle_C));
                                                    perimeter = side_a + side_b + side_c;
                                                    angle_B = Math.asin( (side_b*Math.sin(angle_C)) / side_c);
                                                    angle_A = Math.PI - angle_B - angle_C;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Angle_b.setText(String.valueOf(angle_B));
                                                    Perimeter.setText(String.valueOf(perimeter));
                                                }
                                            }
                                            else{
                                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                                if (Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else {
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    side_b = (2*area)/(side_a*Math.sin(angle_C));
                                                    side_c = Math.sqrt( side_a*side_a + side_b*side_b - 2*side_a*side_b*Math.cos(angle_C));
                                                    angle_B = Math.asin( (side_b*Math.sin(angle_C)) / side_c);
                                                    angle_A = Math.PI - angle_B - angle_C;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Angle_b.setText(String.valueOf(angle_B));
                                                }
                                            }
                                        }
                                    }//Angle_C
                                }
                            }
                            else {
                                angle_A = Double.parseDouble(Angle_a.getText().toString());
                                if (Angle_b.length() == 0)
                                {
                                    if (Angle_c.length() == 0)
                                    {
                                        if (Area.length() == 0)
                                        {
                                            Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            area = Double.parseDouble(Area.getText().toString());
                                            if (Perimeter.length() == 0)
                                            {
                                                if(Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else{
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    Side_a.setText(String.valueOf(side_a));
                                                }
                                            }
                                            else{
                                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                                if (Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else {
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    Side_a.setText(String.valueOf(side_a));
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        angle_C = Double.parseDouble(Angle_c.getText().toString());
                                        if (Area.length() == 0)
                                        {
                                            Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            area = Double.parseDouble(Area.getText().toString());
                                            if (Perimeter.length() == 0)
                                            {
                                                if(Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else{
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    side_b = (2*area)/(side_a*Math.sin(angle_C));
                                                    side_c = Math.sqrt( side_a*side_a + side_b*side_b - 2*side_a*side_b*Math.cos(angle_C));
                                                    perimeter = side_a + side_b + side_c;
                                                    angle_B = Math.asin( (side_b*Math.sin(angle_C)) / side_c);
                                                    angle_A = Math.PI - angle_B - angle_C;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Angle_b.setText(String.valueOf(angle_B));
                                                    Perimeter.setText(String.valueOf(perimeter));
                                                }
                                            }
                                            else{
                                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                                if (Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else {
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    side_b = (2*area)/(side_a*Math.sin(angle_C));
                                                    side_c = Math.sqrt( side_a*side_a + side_b*side_b - 2*side_a*side_b*Math.cos(angle_C));
                                                    angle_B = Math.asin( (side_b*Math.sin(angle_C)) / side_c);
                                                    angle_A = Math.PI - angle_B - angle_C;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Angle_b.setText(String.valueOf(angle_B));
                                                }
                                            }
                                        }
                                    }//Angle_C
                                }
                                else {
                                    angle_B = Double.parseDouble(Angle_b.getText().toString());
                                    if (Angle_c.length() == 0)
                                    {
                                        if (Area.length() == 0)
                                        {
                                            Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            area = Double.parseDouble(Area.getText().toString());
                                            if (Perimeter.length() == 0)
                                            {
                                                if(Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else{
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    side_c = (2*area)/(side_a*Math.sin(angle_B));
                                                    side_b = Math.sqrt( side_a*side_a + side_c*side_c - 2*side_a*side_c*Math.cos(angle_B));
                                                    perimeter = side_a + side_b + side_c;
                                                    angle_A = Math.asin( (side_a*Math.sin(angle_B)) / side_b);
                                                    angle_C = Math.PI - angle_A - angle_B;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Perimeter.setText(String.valueOf(perimeter));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Angle_c.setText(String.valueOf(angle_C));
                                                }
                                            }
                                            else{
                                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                                if(Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else{
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    side_c = (2*area)/(side_a*Math.sin(angle_B));
                                                    side_b = Math.sqrt( side_a*side_a + side_c*side_c - 2*side_a*side_c*Math.cos(angle_B));
                                                    angle_A = Math.asin( (side_a*Math.sin(angle_B)) / side_b);
                                                    angle_C = Math.PI - angle_A - angle_B;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Angle_c.setText(String.valueOf(angle_C));
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        angle_C = Double.parseDouble(Angle_c.getText().toString());
                                        if (Area.length() == 0)
                                        {
                                            if (Perimeter.length() == 0)
                                            {
                                                if (Ha.length() == 0)
                                                {
                                                    angle_A = Math.PI - angle_B - angle_C;
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                }
                                                else {
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    angle_A = Math.PI - angle_B - angle_C;
                                                    side_a = ha/Math.tan(angle_B) + ha/Math.tan(angle_C);
                                                    area = 1/2 * side_a*ha;
                                                    side_c = (2*area)/(side_a*Math.sin(angle_B));
                                                    side_b = Math.sqrt( side_a*side_a + side_c*side_c - 2*side_a*side_c*Math.cos(angle_B));
                                                    perimeter = side_a + side_b + side_c;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Area.setText(String.valueOf(area));
                                                    Perimeter.setText(String .valueOf(perimeter));
                                                }
                                            }
                                            else {
                                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                                if (Ha.length() == 0)
                                                {
                                                    angle_A = Math.PI - angle_B - angle_C;
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                }
                                                else {
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    angle_A = Math.PI - angle_B - angle_C;
                                                    side_a = ha/Math.tan(angle_B) + ha/Math.tan(angle_C);
                                                    area = 1/2 * side_a*ha;
                                                    side_c = (2*area)/(side_a*Math.sin(angle_B));
                                                    side_b = Math.sqrt( side_a*side_a + side_c*side_c - 2*side_a*side_c*Math.cos(angle_B));

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Area.setText(String.valueOf(area));
                                                }
                                            }
                                        }
                                        else {
                                            area = Double.parseDouble(Area.getText().toString());
                                            if (Perimeter.length() == 0)
                                            {
                                                if(Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else{
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    side_b = (2*area)/(side_a*Math.sin(angle_C));
                                                    side_c = Math.sqrt( side_a*side_a + side_b*side_b - 2*side_a*side_b*Math.cos(angle_C));
                                                    perimeter = side_a + side_b + side_c;
                                                    angle_B = Math.asin( (side_b*Math.sin(angle_C)) / side_c);
                                                    angle_A = Math.PI - angle_B - angle_C;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Angle_b.setText(String.valueOf(angle_B));
                                                    Perimeter.setText(String.valueOf(perimeter));
                                                }
                                            }
                                            else{
                                                perimeter = Double.parseDouble(Perimeter.getText().toString());
                                                if (Ha.length() == 0)
                                                    Toast.makeText(getActivity(), "Sir, did you forgot something ?", Toast.LENGTH_LONG).show();
                                                else {
                                                    ha = Double.parseDouble(Ha.getText().toString());
                                                    side_a = (2*area)/ha;
                                                    side_b = (2*area)/(side_a*Math.sin(angle_C));
                                                    side_c = Math.sqrt( side_a*side_a + side_b*side_b - 2*side_a*side_b*Math.cos(angle_C));
                                                    angle_B = Math.asin( (side_b*Math.sin(angle_C)) / side_c);
                                                    angle_A = Math.PI - angle_B - angle_C;

                                                    Side_a.setText(String.valueOf(side_a));
                                                    Side_b.setText(String.valueOf(side_b));
                                                    Side_c.setText(String.valueOf(side_c));
                                                    Angle_a.setText(String.valueOf(angle_A));
                                                    Angle_b.setText(String.valueOf(angle_B));
                                                }
                                            }
                                        }
                                    }//Angle_C
                                }//Angle_B
                            }//Angle_c
                        }
                        else {
                            side_c = Double.parseDouble(Side_c.getText().toString());
                        }
                    }
                    else{
                        side_b = Double.parseDouble(Side_b.getText().toString());

                    }
                }else{
                    side_a = Double.parseDouble(Side_a.getText().toString());

                }
            }
        });

        Side_a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Side_a.length()>0 && Side_a.isFocused()==true)
                {
                    boolean loop = true;
                    while(loop)
                    {
                        loop = false;
                        String check;
                        if (Angle_a.length()>0 && Side_b.length()>0) {
                            double side_a = Double.parseDouble(Side_a.getText().toString());
                            double side_b = Double.parseDouble(Side_b.getText().toString());
                            double angle_a = Double.parseDouble(Angle_a.getText().toString());
                            double angle_b = Math.asin((side_b*Math.sin(angle_a))/side_a);
                            check = String.valueOf(angle_b);

                            if(Angle_b.length() == 0)
                            {
                                loop = true;
                                Angle_b.setText(check);
                            }
                            else   if(Math.abs(Double.parseDouble(Angle_b.getText().toString()) - angle_b) > 0.00001)
                            {
                                loop = true;
                                Angle_b.setText(check);
                            }
                        }
                        if(Angle_a.length()>0 && Side_c.length()>0) {
                            double side_a = Double.parseDouble(Side_a.getText().toString());
                            double side_c = Double.parseDouble(Side_c.getText().toString());
                            double angle_a = Double.parseDouble(Angle_a.getText().toString());
                            double angle_c = Math.asin((side_c * Math.sin(angle_a)) / side_a);
                            check = String.valueOf(angle_c);

                            if(Angle_c.length() == 0){
                                loop = true;
                                Angle_c.setText(check);
                            }
                            else    if (Math.abs(Double.parseDouble(Angle_c.getText().toString()) - angle_c) > 0.00001) {
                                loop = true;
                                Angle_c.setText(check);
                            }
                        }
                        if(Angle_b.length()>0 && Side_b.length() > 0)
                        {
                            double side_a = Double.parseDouble(Side_a.getText().toString());
                            double side_b = Double.parseDouble(Side_b.getText().toString());
                            double angle_b = Double.parseDouble(Angle_b.getText().toString());
                            double angle_a = Math.asin((side_a * Math.sin(angle_b)) / side_b);
                            check = String.valueOf(angle_a);

                            if(Angle_a.length() == 0)
                            {
                                loop = true;
                                Angle_a.setText(check);
                            }
                            else if(Math.abs(Double.parseDouble(Angle_a.getText().toString()) - angle_a) > 0.00001)
                            {
                                loop = true;
                                Angle_a.setText(check);
                            }
                        }
                        if(Angle_c.length()>0 && Side_c.length() > 0)
                        {
                            double side_a = Double.parseDouble(Side_a.getText().toString());
                            double side_c = Double.parseDouble(Side_c.getText().toString());
                            double angle_c = Double.parseDouble(Angle_c.getText().toString());
                            double angle_a = Math.asin((side_a * Math.sin(angle_c)) / side_c);
                            check = String.valueOf(angle_a);

                            if(Angle_a.length() == 0)
                            {
                                loop = true;
                                Angle_a.setText(check);
                            }
                            else if(Math.abs(Double.parseDouble(Angle_a.getText().toString()) - angle_a) > 0.00001)
                            {
                                loop = true;
                                Angle_a.setText(check);
                            }
                        }

                        if(Perimeter.length()>0 && Side_b.length()>0 )
                        {
                            double side_a = Double.parseDouble(Side_a.getText().toString());
                            double side_b = Double.parseDouble(Side_b.getText().toString());
                            double perimeter = Double.parseDouble(Perimeter.getText().toString());
                            double side_c = perimeter - side_a - side_b;
                            check = String.valueOf(side_c);

                            if(Side_c.length() == 0)
                            {
                                loop = true;
                                Side_c.setText(check);
                            }
                            else if(Math.abs(Double.parseDouble(Side_c.getText().toString()) - side_c) > 0.00001)
                            {
                                loop = true;
                                Side_c.setText(check);
                            }
                        }
                        if(Perimeter.length()>0 && Side_c.length()>0 )
                        {
                            double side_a = Double.parseDouble(Side_a.getText().toString());
                            double side_c = Double.parseDouble(Side_c.getText().toString());
                            double perimeter = Double.parseDouble(Perimeter.getText().toString());
                            double side_b = perimeter - side_a - side_c;
                            check = String.valueOf(side_b);

                            if(Side_b.length() == 0)
                            {
                                loop = true;
                                Side_b.setText(check);
                            }
                            else if(Math.abs(Double.parseDouble(Side_b.getText().toString()) - side_b) > 0.00001)
                            {
                                loop = true;
                                Side_b.setText(check);
                            }
                        }
                        if (Side_b.length()>0 && Side_c.length()>0) {
                            double side_a = Double.parseDouble(Side_a.getText().toString());
                            double side_b = Double.parseDouble(Side_b.getText().toString());
                            double side_c = Double.parseDouble(Side_c.getText().toString());
                            double perimeter = side_a + side_b + side_c;
                            double area = Math.sqrt( ( perimeter*(perimeter-2*side_a) * (perimeter-2*side_b) * (perimeter-2*side_c)) / 16);
                            check = String.valueOf(perimeter);

                            if(Perimeter.length()==0)
                            {
                                loop = true;
                                Perimeter.setText(check);
                            }
                            else    if (Math.abs(Double.parseDouble(Perimeter.getText().toString()) - perimeter) > 0.00001) {
                                loop = true;
                                Perimeter.setText(check);
                            }
                            check = String.valueOf(area);
                            if(Area.length() == 0){
                                loop = true;
                                Area.setText(check);
                            }
                            else if(Math.abs(Double.parseDouble(Area.getText().toString()) - area) > 0.00001){
                                loop = true;
                                Area.setText(check);
                            }
                        }

                        if (Side_c.length()>0 && Angle_b.length()>0)
                        {
                            double side_a = Double.parseDouble(Side_a.getText().toString());
                            double side_c = Double.parseDouble(Side_c.getText().toString());
                            double angle_b = Double.parseDouble(Angle_b.getText().toString());
                            double side_b = Math.sqrt(side_a*side_a + side_c*side_c - 2*side_a*side_c*Math.cos(angle_b));
                            check = String.valueOf(side_c);

                            if(Side_b.length() == 0)
                            {
                                loop = true;
                                Side_b.setText(check);
                            }
                            else if(Math.abs(Double.parseDouble(Side_b.getText().toString()) - side_b) > 0.00001)
                            {
                                loop = true;
                                Side_b.setText(check);
                            }
                        }
                        if (Side_b.length()>0 && Angle_c.length()>0)
                        {
                            double side_a = Double.parseDouble(Side_a.getText().toString());
                            double side_b = Double.parseDouble(Side_b.getText().toString());
                            double angle_c = Double.parseDouble(Angle_c.getText().toString());
                            double side_c = Math.sqrt(side_a*side_a + side_b*side_b - 2*side_a*side_b*Math.cos(angle_c));
                            check = String.valueOf(side_c);

                            if(Side_c.length() == 0)
                            {
                                loop = true;
                                Side_c.setText(check);
                            }
                            else if (Math.abs(Double.parseDouble(Side_c.getText().toString()) - side_c) > 0.00001)
                            {
                                loop = true;
                                Side_c.setText(check);
                            }
                        }

                        if (Side_b.length()>0 && Area.length()>0)
                        {
                            double side_a = Double.parseDouble(Side_a.getText().toString());
                            double side_b = Double.parseDouble(Side_b.getText().toString());
                            double area = Double.parseDouble(Area.getText().toString());
                            double angle_c = Math.asin((2*area)/(side_a*side_b));
                            check = String.valueOf(angle_c);

                            if (Angle_c.length() == 0)
                            {
                                loop =true;
                                Angle_c.setText(check);
                            }
                            else if(Math.abs(Double.parseDouble(Angle_c.getText().toString()) - angle_c) > 0.00001)
                            {
                                loop =true;
                                Angle_c.setText(check);
                            }
                        }
                        if (Side_c.length()>0 && Area.length()>0)
                        {
                            double side_a = Double.parseDouble(Side_a.getText().toString());
                            double side_c = Double.parseDouble(Side_c.getText().toString());
                            double area = Double.parseDouble(Area.getText().toString());
                            double angle_b = Math.asin((2*area)/(side_a*side_c));
                            check = String.valueOf(angle_b);

                            if(Angle_b.length() == 0)
                            {
                                loop = true;
                                Angle_b.setText(check);
                            }
                            else if(Math.abs(Double.parseDouble(Angle_b.getText().toString()) - angle_b) > 0.0001)
                            {
                                loop = true;
                                Angle_b.setText(check);
                            }
                        }
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Side_b.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Angle_a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Angle_b.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Angle_c.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Perimeter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

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
            Toast.makeText(getActivity(),"Triangle", Toast.LENGTH_SHORT).show();
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
