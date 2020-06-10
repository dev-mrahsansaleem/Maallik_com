package com.example.maallik_com;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class MultipleImageFragment extends Fragment {
    private static final int requestCodeGallery = 123;

    private View v;
    private ArrayList<Uri> uriList = new ArrayList<Uri>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_multiple_images, container, false);


        ImageView imageView;
        Button btnOpenGallery;
        Button btnShareImages;
        imageView = v.findViewById(R.id.image_view);

        btnOpenGallery = v.findViewById(R.id.btnOpenGallery);

        btnOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    return;
                }
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent, "Pick An Image"), requestCodeGallery);
            }
        });
        btnShareImages = v.findViewById(R.id.btnShareImages);
        btnShareImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uriList.size() > 0) {
                    Toast.makeText(v.getContext(), uriList.size() + " image selected to share", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Here are some files.");
                    intent.setType("image/*");
                    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
                    startActivity(intent);
                } else {
                    Toast.makeText(v.getContext(), "no image selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == requestCodeGallery && resultCode == RESULT_OK && data != null) {


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
                ImageView image = new ImageView(getContext());
                LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//                LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(200, 200);
                image.setLayoutParams(vp);
                image.setMinimumHeight(100);
                image.setMinimumWidth(100);
                image.setImageURI(uri);
                image.setPadding(10, 10, 10, 10);
                LinearLayout images_layout = v.findViewById(R.id.images_view);

                images_layout.addView(image);

                TextView textView = new TextView(getContext());
                textView.setText(uri.toString());
                textView.setTextColor(Color.parseColor("#ffffff"));
                LinearLayout theLayout = v.findViewById(R.id.names_view);
                theLayout.addView(textView);
            }
        }
    }
}
