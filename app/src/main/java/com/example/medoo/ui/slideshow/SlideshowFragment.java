package com.example.medoo.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.medoo.R;
import com.example.medoo.maphospitals;
import com.example.medoo.hospitallist;
import com.example.medoo.confirmlocation;
import com.example.medoo.questions;
public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);


        Button b=(Button) root.findViewById(R.id.map) ;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getActivity(), confirmlocation.class);

                startActivity(intent);
            }
        });
        Button registeredhospitals=(Button) root.findViewById(R.id.registeredhospitals) ;
        registeredhospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getActivity(),hospitallist.class);


                startActivity(intent);
            }
        });
        Button report=(Button) root.findViewById(R.id.report) ;
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getActivity(),questions.class);


                startActivity(intent);
            }
        });

        return root;
    }
}