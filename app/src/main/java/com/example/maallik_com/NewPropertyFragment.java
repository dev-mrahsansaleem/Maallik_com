package com.example.maallik_com;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class NewPropertyFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_new_property, container, false);
        Spinner propertyPurpose = (Spinner) v.findViewById(R.id.propertyPurposeS);
        Spinner propertyType = (Spinner) v.findViewById(R.id.propertyTypeS);

        propertyPurpose.setOnItemSelectedListener(this);
        propertyType.setOnItemSelectedListener(this);
        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] categories;
        ArrayAdapter<String> adapter;
        Spinner spinner;

        String selectedText = parent.getItemAtPosition(position).toString();
        switch (selectedText) {
            case "Rent":
                v.findViewById(R.id.maintainceCharges).setVisibility(View.VISIBLE);
                v.findViewById(R.id.advancePrice).setVisibility(View.VISIBLE);
                v.findViewById(R.id.rentalPrice).setVisibility(View.VISIBLE);
                v.findViewById(R.id.periodUnit).setVisibility(View.VISIBLE);
                v.findViewById(R.id.minimumContractPeriod).setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Rent", Toast.LENGTH_SHORT).show();
                break;
            case "Sale":
                Toast.makeText(getContext(), "Sale", Toast.LENGTH_SHORT).show();
                v.findViewById(R.id.maintainceCharges).setVisibility(View.GONE);
                v.findViewById(R.id.advancePrice).setVisibility(View.GONE);
                v.findViewById(R.id.rentalPrice).setVisibility(View.GONE);
                v.findViewById(R.id.periodUnit).setVisibility(View.GONE);
                v.findViewById(R.id.minimumContractPeriod).setVisibility(View.GONE);

                break;


            case "Home":
                v.findViewById(R.id.category).setVisibility(View.VISIBLE);
                v.findViewById(R.id.BathRooms).setVisibility(View.VISIBLE);
                v.findViewById(R.id.BedRooms).setVisibility(View.VISIBLE);
                v.findViewById(R.id.features).setVisibility(View.VISIBLE);


                categories = getResources().getStringArray(R.array.Homecategory);
                adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, categories);
                spinner = v.findViewById(R.id.categoryS);
                spinner.setAdapter(adapter);

                Toast.makeText(getContext(), "Home", Toast.LENGTH_SHORT).show();
                break;
            case "Plot":
                v.findViewById(R.id.category).setVisibility(View.VISIBLE);

                categories = getResources().getStringArray(R.array.PlotCategory);
                adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, categories);
                spinner = v.findViewById(R.id.categoryS);
                spinner.setAdapter(adapter);


                Toast.makeText(getContext(), "Plot", Toast.LENGTH_SHORT).show();


                v.findViewById(R.id.BathRooms).setVisibility(View.GONE);
                v.findViewById(R.id.BedRooms).setVisibility(View.GONE);
                v.findViewById(R.id.features).setVisibility(View.GONE);
                break;
            case "Commercial":

                v.findViewById(R.id.category).setVisibility(View.VISIBLE);
                v.findViewById(R.id.BathRooms).setVisibility(View.VISIBLE);
                v.findViewById(R.id.BedRooms).setVisibility(View.VISIBLE);
                v.findViewById(R.id.features).setVisibility(View.VISIBLE);


                categories = getResources().getStringArray(R.array.CommercialCategory);
                adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, categories);
                spinner = v.findViewById(R.id.categoryS);
                spinner.setAdapter(adapter);
                Toast.makeText(getContext(), "Commercial", Toast.LENGTH_SHORT).show();
                break;
            default:
                v.findViewById(R.id.maintainceCharges).setVisibility(View.GONE);
                v.findViewById(R.id.advancePrice).setVisibility(View.GONE);
                v.findViewById(R.id.rentalPrice).setVisibility(View.GONE);
                v.findViewById(R.id.periodUnit).setVisibility(View.GONE);
                v.findViewById(R.id.minimumContractPeriod).setVisibility(View.GONE);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
