package com.example.maallik_com;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class NewPropertyFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private View v;
    private ArrayList<Uri> uriList = new ArrayList<Uri>();

    // Values holders
    private String propertyPurpose;
    private String propertyType;
    private String category;
    private String propertyTitle;
    private String city;
    private String location;
    private String description;
    private String price;
    private String area;
    private String Unit;
    private String minimumContractPeriod;
    private String periodUnit;
    private String rentalPrice;
    private String advancePrice;
    private String maintainceCharges;
    private String BedRooms;
    private String BathRooms;
    private String features;
    private String urlField;
    // END

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_new_property, container, false);

        Spinner propertyPurposeS = (Spinner) v.findViewById(R.id.propertyPurposeS);
        propertyPurposeS.setOnItemSelectedListener(this);

        Spinner propertyTypeS = (Spinner) v.findViewById(R.id.propertyTypeS);
        propertyTypeS.setOnItemSelectedListener(this);

        Spinner categoryS = (Spinner) v.findViewById(R.id.categoryS);
        categoryS.setOnItemSelectedListener(this);

        Spinner periodUnitS = (Spinner) v.findViewById(R.id.periodUnitS);
        periodUnitS.setOnItemSelectedListener(this);

        Spinner UnitS = (Spinner) v.findViewById(R.id.UnitS);
        UnitS.setOnItemSelectedListener(this);

        Spinner maintainceChargesS = (Spinner) v.findViewById(R.id.maintainceChargesS);
        maintainceChargesS.setOnItemSelectedListener(this);

        Spinner featuresS = (Spinner) v.findViewById(R.id.featuresS);
        featuresS.setOnItemSelectedListener(this);


        Button btnSubmit = (Button) v.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(v.getContext(), "btn Submit", Toast.LENGTH_SHORT).show();
                TextInputLayout textInputLayout;

                textInputLayout = v.findViewById(R.id.propertyTitle);
                propertyTitle = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();

                textInputLayout = v.findViewById(R.id.city);
                city = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();

                textInputLayout = v.findViewById(R.id.location);
                location = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();

                textInputLayout = v.findViewById(R.id.description);
                description = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();

                textInputLayout = v.findViewById(R.id.price);
                price = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();

                textInputLayout = v.findViewById(R.id.area);
                area = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();

                textInputLayout = v.findViewById(R.id.minimumContractPeriod);
                minimumContractPeriod = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();

                textInputLayout = v.findViewById(R.id.rentalPrice);
                rentalPrice = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();

                textInputLayout = v.findViewById(R.id.advancePrice);
                advancePrice = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();

                textInputLayout = v.findViewById(R.id.BedRooms);
                BedRooms = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();

                textInputLayout = v.findViewById(R.id.BathRooms);
                BathRooms = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();

                textInputLayout = v.findViewById(R.id.urlField);
                urlField = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString().trim();

                Intent intent = new Intent(getContext(), PropertyDetails.class);
                intent.putExtra("propertyTitle", propertyTitle);
                intent.putExtra("city", city);
                intent.putExtra("location", location);
                intent.putExtra("description", description);
                intent.putExtra("price", price);
                intent.putExtra("area", area);
                intent.putExtra("minimumContractPeriod", minimumContractPeriod);
                intent.putExtra("rentalPrice", rentalPrice);
                intent.putExtra("advancePrice", advancePrice);
                intent.putExtra("BedRooms", BedRooms);
                intent.putExtra("BathRooms", BathRooms);
                intent.putExtra("urlField", urlField);
                
                intent.putExtra("propertyPurpose", propertyPurpose);
                intent.putExtra("propertyType", propertyType);
                intent.putExtra("category", category);
                intent.putExtra("periodUnit", periodUnit);
                intent.putExtra("Unit", Unit);
                intent.putExtra("features", features);
                intent.putExtra("maintainceCharges", maintainceCharges);

                intent.putExtra("images", uriList);

                startActivity(intent);

            }
        });


        Button btnChooseFile = (Button) v.findViewById(R.id.btnChooseFile);
        btnChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(v.getContext(), "btn Choose File", Toast.LENGTH_SHORT).show();

                if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    return;
                }
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent, "Pick An Image"), 100);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {


            ClipData clipData = data.getClipData();

            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {

                    Uri uri = clipData.getItemAt(i).getUri();

                    uriList.add(uri);
                }

            } else {

                Uri uri = data.getData();
                uriList.add(uri);
            }

            for (Uri uri : uriList) {
//                ImageView image = new ImageView(getContext());
//                LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
////                LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(200, 200);
//                image.setLayoutParams(vp);
//                image.setMinimumHeight(100);
//                image.setMinimumWidth(100);
//                image.setImageURI(uri);
//                image.setPadding(10, 10, 10, 10);
//                LinearLayout images_layout = v.findViewById(R.id.images_view);
//
//                images_layout.addView(image);

                TextView textView = new TextView(getContext());
                textView.setText(uri.toString());
//                textView.setTextColor(Color.parseColor("#ffffff"));
                textView.setTextColor(Color.parseColor("#aaaaaa"));

                textView.setPadding(10, 10, 10, 10);
                LinearLayout theLayout = v.findViewById(R.id.files);
                theLayout.addView(textView);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] categories;
        ArrayAdapter<String> adapter;
        Spinner spinner;

        String selectedText = parent.getItemAtPosition(position).toString();
        switch (selectedText) {
            case "Rent":
                propertyPurpose = selectedText;
                v.findViewById(R.id.maintainceCharges).setVisibility(View.VISIBLE);
                v.findViewById(R.id.advancePrice).setVisibility(View.VISIBLE);
                v.findViewById(R.id.rentalPrice).setVisibility(View.VISIBLE);
                v.findViewById(R.id.periodUnit).setVisibility(View.VISIBLE);
                v.findViewById(R.id.minimumContractPeriod).setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Rent", Toast.LENGTH_SHORT).show();
                break;
            case "Sale":
                propertyPurpose = selectedText;
                Toast.makeText(getContext(), "Sale", Toast.LENGTH_SHORT).show();
                v.findViewById(R.id.maintainceCharges).setVisibility(View.GONE);
                v.findViewById(R.id.advancePrice).setVisibility(View.GONE);
                v.findViewById(R.id.rentalPrice).setVisibility(View.GONE);
                v.findViewById(R.id.periodUnit).setVisibility(View.GONE);
                v.findViewById(R.id.minimumContractPeriod).setVisibility(View.GONE);

                break;


            case "Home":
                propertyType = selectedText;
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
                propertyType = selectedText;

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
                propertyType = selectedText;

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


            case "House":
                category = selectedText;
                break;
            case "Flat":
                category = selectedText;
                break;
            case "Upper Portion":
                category = selectedText;
                break;
            case "Lower Portion":
                category = selectedText;
                break;
            case "Farm House":
                category = selectedText;
                //both Home and commercial types have this case
                break;
            case "Pent House":
                category = selectedText;
                break;
            case "Room":
                category = selectedText;
                break;


            case "Residential Plot":
                category = selectedText;
                break;
            case "Commercial Plot":
                category = selectedText;
                break;
            case "Agricultural Land":
                category = selectedText;
                break;
            case "Industrial Land":
                category = selectedText;
                break;
            case "Plot File":
                category = selectedText;
                break;
            case "Plot Form":
                category = selectedText;
                break;


            case "Office":
                category = selectedText;
                break;
            case "Shop":
                category = selectedText;
                break;
            case "Warehouse":
                category = selectedText;
                break;
            case "Factory":
                category = selectedText;
                break;
//            case "Farm House":
//                category=selectedText;
//                //both Home and commercial types have this case
//                break;
            case "Building":
                category = selectedText;
                break;
            case "Other":
                category = selectedText;
                break;


            case "Marla":
                Unit = selectedText;
                break;
            case "Kanal":
                Unit = selectedText;
                break;
            case "Square Feet":
                Unit = selectedText;
                break;
            case "Square Meters":
                Unit = selectedText;
                break;
            case "Square Yards":
                Unit = selectedText;
                break;


            case "Month":
                periodUnit = selectedText;
                break;
            case "Year":
                periodUnit = selectedText;
                break;


            case "Tenant":
                maintainceCharges = selectedText;
                break;
            case "LandLord":
                maintainceCharges = selectedText;
                break;


            case "Kitchen":
                features = selectedText;
                break;
            case "Furnished":
                features = selectedText;
                break;
            case "Marble":
                features = selectedText;
                break;
            case "Wood Work":
                features = selectedText;
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
