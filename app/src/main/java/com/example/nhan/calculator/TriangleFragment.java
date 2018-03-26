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

import java.util.BitSet;
import java.util.Vector;
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

    private double side_a, side_b, side_c, angel_A, angel_B, angel_C, area, perimeter, ha;
    private static Vector<Integer> BinaryCheckList = new Vector<>(9);


    private OnFragmentInteractionListener mListener;

    public TriangleFragment() {
        // Required empty public constructor
        BinaryCheckList = new Vector<>(9);
        for (int i =0; i <9; i++)
            BinaryCheckList.add(i, 0);
    }

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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
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

        Clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i =0; i <9; i++)
                    BinaryCheckList.add(i, 0);
                Side_a.setText("");
                Side_b.setText("");
                Side_c.setText("");
                Angle_a.setText("");
                Angle_b.setText("");
                Angle_c.setText("");
                Area.setText("");
                Perimeter.setText("");
                Ha.setText("");
            }
        });

        Solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loop = true;
                while(loop){
                    loop = false;
                    //A + B + C = 180
                    if (BinaryCheckList.get(3) + BinaryCheckList.get(4) + BinaryCheckList.get(5) == 2){
                        loop = true;
                        if (BinaryCheckList.get(3) == 0){
                            angel_A = 180 - angel_B - angel_C;
                            if (angel_A <= 0 || angel_A > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that A+B+C = 180", Toast.LENGTH_LONG).show();
                            else{
                                Angle_a.setText(String.valueOf(angel_A));
                            }
                            BinaryCheckList.set(3, 1);
                        }else if(BinaryCheckList.get(4) == 0){
                            angel_B = 180 - angel_A - angel_C;
                            if (angel_B <= 0 || angel_B > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that A+B+C = 180", Toast.LENGTH_LONG).show();
                            else{
                                Angle_b.setText(String.valueOf((angel_B*180)/Math.PI));
                            }
                            BinaryCheckList.set(4, 1);
                        }else{
                            angel_C = 180 - angel_A - angel_B;
                            if (angel_C <= 0 || angel_C > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that A+B+C = 180", Toast.LENGTH_LONG).show();
                            else{
                                Angle_c.setText(String.valueOf(angel_C));
                            }
                            BinaryCheckList.set(5, 1);
                        }
                    }
                    //a2 = b2 + c2 -2bccosA
                    if (BinaryCheckList.get(0) + BinaryCheckList.get(1) + BinaryCheckList.get(2) + BinaryCheckList.get(3) == 3){
                        loop = true;
                        if (BinaryCheckList.get(0) == 0){
                            side_a = Math.sqrt(side_b*side_b + side_c*side_c - 2*side_b*side_c*Math.cos(Math.toRadians(angel_A)));
                            if (side_a <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_a.setText(String.valueOf(side_a));
                            BinaryCheckList.set(0, 1);
                        }else if (BinaryCheckList.get(1) == 0){
                            side_b = Math.sqrt(side_a*side_a - side_c*side_c + 2*side_b*side_c*Math.cos(Math.toRadians(angel_A)));
                            if (side_b <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_b.setText(String.valueOf(side_b));
                            BinaryCheckList.set(1, 1);
                        }else if (BinaryCheckList.get(2) == 0){
                            side_c = Math.sqrt(side_a*side_a - side_b*side_b + 2*side_b*side_c*Math.cos(Math.toRadians(angel_A)));
                            if (side_c <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_c.setText(String.valueOf(side_c));
                            BinaryCheckList.set(2, 1);
                        }else{
                            angel_A = Math.toDegrees(Math.acos((side_c*side_c + side_b*side_b - side_a*side_a) / (2*side_b*side_c)));
                            if (angel_A <= 0 || angel_A > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Angle_a.setText(String.valueOf(angel_A));
                            BinaryCheckList.set(3, 1);
                        }
                    }
                    //b2 = a2 + c2 - 2accosB
                    if (BinaryCheckList.get(0) + BinaryCheckList.get(1) + BinaryCheckList.get(2) + BinaryCheckList.get(4) == 3){
                        loop = true;
                        if (BinaryCheckList.get(0) == 0){
                            side_a = Math.sqrt(side_b*side_b - side_c*side_c + 2*side_a*side_c*Math.cos(Math.toRadians(angel_B)));
                            if (side_a <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_a.setText(String.valueOf(side_a));
                            BinaryCheckList.set(0, 1);
                        }else if (BinaryCheckList.get(1) == 0){
                            side_b = Math.sqrt(side_a*side_a + side_c*side_c - 2*side_a*side_c*Math.cos(Math.toRadians(angel_B)));
                            if (side_b <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_b.setText(String.valueOf(side_b));
                            BinaryCheckList.set(1, 1);
                        }else if (BinaryCheckList.get(2) == 0){
                            side_c = Math.sqrt(side_b*side_b - side_a*side_a + 2*side_a*side_c*Math.cos(Math.toRadians(angel_B)));
                            if (side_c <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_c.setText(String.valueOf(side_c));
                            BinaryCheckList.set(2, 1);
                        }else{
                            angel_B = Math.toDegrees(Math.acos((side_c*side_c + side_a*side_a - side_b*side_b) / (2*side_a*side_c)));
                            if (angel_B <= 0 || angel_B > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Angle_b.setText(String.valueOf(angel_B));
                            BinaryCheckList.set(4, 1);
                        }
                    }
                    //c2 = a2 + b2 - 2abcosC
                    if (BinaryCheckList.get(0) + BinaryCheckList.get(1) + BinaryCheckList.get(2) + BinaryCheckList.get(5) == 3){
                        loop = true;
                        if (BinaryCheckList.get(0) == 0){
                            side_a = Math.sqrt(side_c*side_c - side_b*side_b + 2*side_a*side_b*Math.cos(Math.toRadians(angel_C)));
                            if (side_a <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_a.setText(String.valueOf(side_a));
                            BinaryCheckList.set(0, 1);
                        }else if (BinaryCheckList.get(1) == 0){
                            side_b = Math.sqrt(side_c*side_c - side_a*side_a + 2*side_a*side_b*Math.cos(Math.toRadians(angel_C)));
                            if (side_b <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_b.setText(String.valueOf(side_b));
                            BinaryCheckList.set(1, 1);
                        }else if (BinaryCheckList.get(2) == 0){
                            side_c = Math.sqrt(side_b*side_b + side_a*side_a - 2*side_a*side_b*Math.cos(Math.toRadians(angel_C)));
                            if (side_c <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_c.setText(String.valueOf(side_c));
                            BinaryCheckList.set(2, 1);
                        }else{
                            angel_C = Math.toDegrees(Math.acos((side_a*side_a + side_b*side_b - side_c*side_c) / (2*side_a*side_b)));
                            if (angel_C <= 0 || angel_C > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Angle_c.setText(String.valueOf(angel_C));
                            BinaryCheckList.set(5, 1);
                        }
                    }
                    //a/sinA = b/sinB
                    if (BinaryCheckList.get(0) + BinaryCheckList.get(1) + BinaryCheckList.get(3) + BinaryCheckList.get(4) == 3){
                        loop = true;
                        if (BinaryCheckList.get(0) == 0){
                            side_a = (side_b*Math.sin(Math.toRadians(angel_A))) / Math.sin(Math.toRadians(angel_B));
                            if (side_a <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_a.setText(String.valueOf(side_a));
                            BinaryCheckList.set(0, 1);
                        }else if (BinaryCheckList.get(1) == 0){
                            side_b = (side_a*Math.sin(Math.toRadians(angel_B))) / Math.sin(Math.toRadians(angel_A));
                            if (side_b <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_b.setText(String.valueOf(side_b));
                            BinaryCheckList.set(1, 1);
                        }else if (BinaryCheckList.get(3) == 0){
                            angel_A = Math.toDegrees(Math.asin((side_a*Math.sin(Math.toRadians(angel_B))) / side_b));
                            if (angel_A <= 0 || angel_A > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Angle_a.setText(String.valueOf(angel_A));
                            BinaryCheckList.set(3, 1);
                        }else{
                            angel_B = Math.toDegrees(Math.asin((side_b*Math.sin(Math.toRadians(angel_A))) / side_a));
                            if (angel_B <= 0 || angel_B > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Angle_b.setText(String.valueOf((angel_B*180)/Math.PI));
                            BinaryCheckList.set(4, 1);
                        }
                    }
                    // b/sinB = c/sinC
                    if (BinaryCheckList.get(1) + BinaryCheckList.get(2) + BinaryCheckList.get(4) + BinaryCheckList.get(5) == 3){
                        loop = true;
                        if (BinaryCheckList.get(1) == 0){
                            side_b = (side_c*Math.sin(Math.toRadians(angel_B))) / Math.sin(Math.toRadians(angel_C));
                            if (side_b <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_b.setText(String.valueOf(side_b));
                            BinaryCheckList.set(1, 1);
                        }else if (BinaryCheckList.get(2) == 0){
                            side_c = (side_b*Math.sin(Math.toRadians(angel_C))) / Math.sin(Math.toRadians(angel_B));
                            if (side_c <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_c.setText(String.valueOf(side_c));
                            BinaryCheckList.set(2, 1);
                        }else if (BinaryCheckList.get(4) == 0){
                            angel_B = Math.toDegrees(Math.asin((side_b*Math.sin(Math.toRadians(angel_C))) / side_c));
                            if (angel_B <= 0 || angel_B > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Angle_b.setText(String.valueOf((angel_B*180)/Math.PI));
                            BinaryCheckList.set(4, 1);
                        }else{
                            angel_C = Math.toDegrees(Math.asin((side_c*Math.sin(Math.toRadians(angel_B))) / side_b));
                            if (angel_C <= 0 || angel_C > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Angle_c.setText(String.valueOf(angel_C));
                            BinaryCheckList.set(5, 1);
                        }
                    }
                    // a/sinA = c/sinC
                    if (BinaryCheckList.get(0) + BinaryCheckList.get(2) + BinaryCheckList.get(3) + BinaryCheckList.get(5) == 3){
                        loop = true;
                        if (BinaryCheckList.get(0) == 0){
                            side_a = (side_c*Math.sin(Math.toRadians(angel_A))) / Math.sin(Math.toRadians(angel_C));
                            if (side_a <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_a.setText(String.valueOf(side_a));
                            BinaryCheckList.set(0, 1);
                        }else if (BinaryCheckList.get(2) == 0){
                            side_c = (side_a*Math.sin(Math.toRadians(angel_C))) / Math.sin(Math.toRadians(angel_A));
                            if (side_b <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_b.setText(String.valueOf(side_b));
                            BinaryCheckList.set(2, 1);
                        }else if (BinaryCheckList.get(3) == 0){
                            angel_A = Math.toDegrees(Math.asin((side_a*Math.sin(Math.toRadians(angel_C))) / side_c));
                            if (angel_A <= 0 || angel_A > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Angle_a.setText(String.valueOf(angel_A));
                            BinaryCheckList.set(3, 1);
                        }else{
                            angel_C = Math.toDegrees(Math.asin((side_c*Math.sin(Math.toRadians(angel_A))) / side_a));
                            if (angel_C <= 0 || angel_C > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Angle_c.setText(String.valueOf(angel_C));
                            BinaryCheckList.set(5, 1);
                        }
                    }
                    // Area = 1/2.a.b.sinC
                    if (BinaryCheckList.get(0) + BinaryCheckList.get(1) + BinaryCheckList.get(5) + BinaryCheckList.get(6) == 3){
                        loop = true;
                        if (BinaryCheckList.get(0) == 0){
                            side_a = (2*area) / (side_b*Math.sin(Math.toRadians(angel_C)));
                            if (side_a <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_a.setText(String.valueOf(side_a));
                            BinaryCheckList.set(0, 1);
                        }else if (BinaryCheckList.get(1) == 0){
                            side_b = (2*area) / (side_a*Math.sin(Math.toRadians(angel_C)));
                            if (side_b <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_b.setText(String.valueOf(side_b));
                            BinaryCheckList.set(1, 1);
                        }else if (BinaryCheckList.get(5) == 0){
                            angel_C = Math.toDegrees(Math.asin((2*area) / (side_a*side_b)));
                            if (angel_C <= 0 || angel_C > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Angle_c.setText(String.valueOf(angel_C));
                            BinaryCheckList.set(5, 1);
                        }else{
                            area = 0.5*side_a*side_b*Math.sin(Math.toRadians(angel_C));

                            if (area <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Area.setText(String.valueOf(area));
                            BinaryCheckList.set(6, 1);
                        }
                    }
                    // Area = 1/2.a.c.sinB
                    if (BinaryCheckList.get(0) + BinaryCheckList.get(2) + BinaryCheckList.get(4) + BinaryCheckList.get(6) == 3){
                        loop = true;
                        if (BinaryCheckList.get(0) == 0){
                            side_a = (2*area) / (side_c*Math.sin(Math.toRadians(angel_B)));
                            if (side_a <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_a.setText(String.valueOf(side_a));
                            BinaryCheckList.set(0, 1);
                        }else if (BinaryCheckList.get(2) == 0){
                            side_c = (2*area) / (side_a*Math.sin(Math.toRadians(angel_B)));
                            if (side_c <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_c.setText(String.valueOf(side_c));
                            BinaryCheckList.set(2, 1);
                        }else if (BinaryCheckList.get(4) == 0){
                            angel_B = Math.toDegrees(Math.asin((2*area) / (side_a*side_c)));
                            if (angel_B <= 0 || angel_B > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Angle_b.setText(String.valueOf((angel_B*180)/Math.PI));
                            BinaryCheckList.set(4, 1);
                        }else{
                            area = 0.5*side_a*side_c*Math.sin(Math.toRadians(angel_B));
                            if (area <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Area.setText(String.valueOf(area));
                            BinaryCheckList.set(6, 1);
                        }
                    }
                    // Area = 1/2.b.c.sinA
                    if (BinaryCheckList.get(1) + BinaryCheckList.get(2) + BinaryCheckList.get(3) + BinaryCheckList.get(6) == 3){
                        loop = true;
                        if (BinaryCheckList.get(1) == 0){
                            side_b = (2*area) / (side_c*Math.sin(Math.toRadians(angel_A)));
                            if (side_b <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_b.setText(String.valueOf(side_b));
                            BinaryCheckList.set(1, 1);
                        }else if (BinaryCheckList.get(2) == 0){
                            side_c = (2*area) / (side_b*Math.sin(Math.toRadians(angel_A)));
                            if (side_c <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_c.setText(String.valueOf(side_c));
                            BinaryCheckList.set(2, 1);
                        }else if (BinaryCheckList.get(3) == 0){
                            angel_A = Math.toDegrees(Math.asin((2*area) / (side_b*side_c)));
                            if (angel_A <= 0 || angel_A > 180)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Angle_a.setText(String.valueOf(angel_A));
                            BinaryCheckList.set(3, 1);
                        }else{
                            area = 0.5*side_b*side_c*Math.sin(Math.toRadians(angel_A));
                            if (area <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Area.setText(String.valueOf(area));
                            BinaryCheckList.set(6, 1);
                        }
                    }
                    // Area = 1/2.a.ha
                    if (BinaryCheckList.get(0) + BinaryCheckList.get(6) + BinaryCheckList.get(8) == 2){
                        loop = true;
                        if (BinaryCheckList.get(0) == 0){
                            side_a = (2*area) / ha;
                            if (side_a <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_a.setText(String.valueOf(side_a));
                            BinaryCheckList.set(0, 1);
                        }else if (BinaryCheckList.get(6) == 0){
                            area = 0.5*side_a*ha;
                            if (area <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Area.setText(String.valueOf(area));
                            BinaryCheckList.set(6, 1);
                        }else{
                            ha = (2*area)/side_a;
                            if (ha <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Ha.setText(String.valueOf(ha));
                            BinaryCheckList.set(8, 1);
                        }
                    }
                    // P = a+b+c
                    if (BinaryCheckList.get(0) + BinaryCheckList.get(1) + BinaryCheckList.get(2) + BinaryCheckList.get(7) == 3){
                        loop = true;
                        if (BinaryCheckList.get(0) == 0){
                            side_a = perimeter - side_b - side_c;
                            if (side_a <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_a.setText(String.valueOf(side_a));
                            BinaryCheckList.set(0, 1);
                        }else if (BinaryCheckList.get(1) == 0) {
                            side_b = perimeter - side_c - side_a;
                            if (side_b <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_b.setText(String.valueOf(side_b));
                            BinaryCheckList.set(1, 1);
                        }else if (BinaryCheckList.get(2) == 0){
                            side_c = perimeter - side_a - side_b;
                            if (side_c <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Side_c.setText(String.valueOf(side_c));
                            BinaryCheckList.set(2, 1);
                        }else{
                            perimeter = side_a + side_b + side_c;
                            if (perimeter <= 0)
                                Toast.makeText(getActivity(), "Sir, make sure that all variable you set is right!!", Toast.LENGTH_LONG).show();
                            else
                                Perimeter.setText(String.valueOf(perimeter));
                            BinaryCheckList.set(7, 1);
                        }
                    }
                }
            }
        });

        //0a, 1b, 2c, 3A, 4B, 5C, 6Area, 7Perimeter, 8Ha
        Side_a.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Side_a.length() == 0){
                    BinaryCheckList.set(0, 0);
                }else{
                    side_a = Double.parseDouble(String.valueOf(Side_a.getText()));
                    if(side_a > 0){
                        BinaryCheckList.set(0, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0", Toast.LENGTH_LONG).show();
                        Side_a.setText("0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Side_a.length() == 0){
                    BinaryCheckList.set(0, 0);
                }else{
                    side_a = Double.parseDouble(String.valueOf(Side_a.getText()));
                    if(side_a > 0){
                        BinaryCheckList.set(0, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0", Toast.LENGTH_LONG).show();
                        Side_a.setText("0.0");
                    }
                }
            }
        });
        Side_b.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Side_b.length() == 0){
                    BinaryCheckList.set(1, 0);
                }else{
                    side_b = Double.parseDouble(String.valueOf(Side_b.getText()));
                    if(side_b > 0){
                        BinaryCheckList.set(1, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0", Toast.LENGTH_LONG).show();
                        Side_b.setText("0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Side_b.length() == 0){
                    BinaryCheckList.set(1, 0);
                }else{
                    side_b = Double.parseDouble(String.valueOf(Side_b.getText()));
                    if(side_b > 0){
                        BinaryCheckList.set(1, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0", Toast.LENGTH_LONG).show();
                        Side_b.setText("0.0");
                    }
                }
            }
        });
        Side_c.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Side_c.length() == 0){
                    BinaryCheckList.set(2, 0);
                }else{
                    side_c = Double.parseDouble(String.valueOf(Side_c.getText()));
                    if(side_c > 0){
                        BinaryCheckList.set(2, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0", Toast.LENGTH_LONG).show();
                        Side_c.setText("0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Side_c.length() == 0){
                    BinaryCheckList.set(2, 0);
                }else{
                    side_c = Double.parseDouble(String.valueOf(Side_c.getText()));
                    if(side_c > 0){
                        BinaryCheckList.set(2, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0", Toast.LENGTH_LONG).show();
                        Side_c.setText("0.0");
                    }
                }
            }
        });
        Angle_a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Angle_a.length() == 0){
                    BinaryCheckList.set(3, 0);
                }else{
                    angel_A = Double.parseDouble(String.valueOf(Angle_a.getText()));
                    if(angel_A > 0 && angel_A < 180){
                        BinaryCheckList.set(3, 1);
                    }else{

                        Toast.makeText(getActivity(), "Sir, the value must biger than 0 or smaller than 180", Toast.LENGTH_LONG).show();
                        Angle_a.setText("0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Angle_a.length() == 0){
                    BinaryCheckList.set(3, 0);
                }else{
                    angel_A = Double.parseDouble(String.valueOf(Angle_a.getText()));
                    if(angel_A > 0 && angel_A < 180){
                        BinaryCheckList.set(3, 1);
                    }else{

                        Toast.makeText(getActivity(), "Sir, the value must biger than 0 or smaller than 180", Toast.LENGTH_LONG).show();
                        Angle_a.setText("0.0");
                    }
                }
            }
        });
        Angle_b.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Angle_b.length() == 0){
                    BinaryCheckList.set(4, 0);
                }else{
                    angel_B = Double.parseDouble(String.valueOf(Angle_b.getText()));
                    if (angel_B > 0 && angel_B < 180){
                        BinaryCheckList.set(4, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0 or smaller than 180", Toast.LENGTH_LONG).show();
                        Angle_b.setText("0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Angle_b.length() == 0){
                    BinaryCheckList.set(4, 0);
                }else{
                    angel_B = Double.parseDouble(String.valueOf(Angle_b.getText()));
                    if (angel_B > 0 && angel_B < 180){
                        BinaryCheckList.set(4, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0 or smaller than 180", Toast.LENGTH_LONG).show();
                        Angle_b.setText("0.0");
                    }
                }
            }
        });
        Angle_c.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Angle_c.length() == 0){
                    BinaryCheckList.set(5, 0);
                }else{
                    angel_C = Double.parseDouble(String.valueOf(Angle_c.getText()));
                    if (angel_C > 0 && angel_C < 180){
                        BinaryCheckList.set(5, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0 or smaller than 180", Toast.LENGTH_LONG).show();
                        Angle_c.setText("0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Angle_c.length() == 0){
                    BinaryCheckList.set(5, 0);
                }else{
                    angel_C = Double.parseDouble(String.valueOf(Angle_c.getText()));
                    if (angel_C > 0 && angel_C < 180){
                        BinaryCheckList.set(5, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0 or smaller than 180", Toast.LENGTH_LONG).show();
                        Angle_c.setText("0.0");
                    }
                }
            }
        });
        Area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Area.length() == 0){
                    BinaryCheckList.set(6, 0);
                }else{
                    area = Double.parseDouble(String.valueOf(Area.getText()));
                    if (area > 0){
                        BinaryCheckList.set(6, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0 or smaller than 180", Toast.LENGTH_LONG).show();
                        Area.setText("0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Area.length() == 0){
                    BinaryCheckList.set(6, 0);
                }else{
                    area = Double.parseDouble(String.valueOf(Area.getText()));
                    if (area > 0){
                        BinaryCheckList.set(6, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0 or smaller than 180", Toast.LENGTH_LONG).show();
                        Area.setText("0.0");
                    }
                }
            }
        });
        Perimeter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Perimeter.length() == 0){
                    BinaryCheckList.set(7, 0);
                }else{
                    perimeter = Double.parseDouble(String.valueOf(Perimeter.getText()));
                    if (perimeter > 0){
                        BinaryCheckList.set(7, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0 or smaller than 180", Toast.LENGTH_LONG).show();
                        Perimeter.setText("0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Perimeter.length() == 0){
                    BinaryCheckList.set(7, 0);
                }else{
                    perimeter = Double.parseDouble(String.valueOf(Perimeter.getText()));
                    if (perimeter > 0){
                        BinaryCheckList.set(7, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0 or smaller than 180", Toast.LENGTH_LONG).show();
                        Perimeter.setText("0.0");
                    }
                }
            }
        });
        Ha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Ha.length() == 0){
                    BinaryCheckList.set(8, 0);
                }else{
                    ha = Double.parseDouble(String.valueOf(Ha.getText()));
                    if (ha > 0){
                        BinaryCheckList.set(8, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0 or smaller than 180", Toast.LENGTH_LONG).show();
                        Ha.setText("0.0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Ha.length() == 0){
                    BinaryCheckList.set(8, 0);
                }else{
                    ha = Double.parseDouble(String.valueOf(Ha.getText()));
                    if (ha > 0){
                        BinaryCheckList.set(8, 1);
                    }else{
                        Toast.makeText(getActivity(), "Sir, the value must biger than 0 or smaller than 180", Toast.LENGTH_LONG).show();
                        Ha.setText("0.0");
                    }
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
            Toast.makeText(getActivity(),"Triangle", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
