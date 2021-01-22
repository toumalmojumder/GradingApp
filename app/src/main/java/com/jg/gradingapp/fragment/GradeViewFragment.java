package com.jg.gradingapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jg.gradingapp.Adapter.MyAdapter;
import com.jg.gradingapp.DB.DatabaseHelper;
import com.jg.gradingapp.MainActivity;
import com.jg.gradingapp.Model.Students;
import com.jg.gradingapp.R;

import java.util.ArrayList;

public class GradeViewFragment extends Fragment {
    RecyclerView recyclerView;
ArrayList<Students> students;
DatabaseHelper databaseHelper;
MyAdapter myAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_grade,container,false);

        //showing all data in recycle view
        recyclerView = view.findViewById(R.id.gradeRecycleView);
        databaseHelper = new DatabaseHelper(getActivity());
        students= databaseHelper.getAllData();

        myAdapter = new MyAdapter(getActivity(),students);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
