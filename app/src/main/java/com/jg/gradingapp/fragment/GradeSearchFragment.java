package com.jg.gradingapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.jg.gradingapp.Adapter.MyAdapter;
import com.jg.gradingapp.DB.DatabaseHelper;
import com.jg.gradingapp.Model.Students;
import com.jg.gradingapp.R;

import java.util.ArrayList;

public class GradeSearchFragment extends Fragment {
    //variable and class object declaration
    RadioButton radioButtonID,radioButtonCode;
    ListView listView;
    LinearLayout linearLayoutSearch;
    RecyclerView recyclerView;
    ArrayList<Students> students;
    DatabaseHelper databaseHelper;
    MyAdapter myAdapter;
    TextInputLayout searchTIL;
    Button buttonSearch;

    //fragment class
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_grade_search,container,false);
      radioButtonID = view.findViewById(R.id.search_fragment_id_radio);
      radioButtonCode = view.findViewById(R.id.search_fragment_code_radio);
      listView = view.findViewById(R.id.search_fragment_listview);
      linearLayoutSearch = view.findViewById(R.id.linearSearchView);
      searchTIL = view.findViewById(R.id.search_fragment_text_field);
      buttonSearch = view.findViewById(R.id.search_fragment_button);
      final String[] programItem = {"Python","Kotlin","Swift","Java"};
      recyclerView = view.findViewById(R.id.search_fragment_recycleView);
      databaseHelper = new DatabaseHelper(getActivity());



//to show program code list view
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                programItem
        );
        listView.setAdapter(listViewAdapter);

        //switching to search by id option
      radioButtonID.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              //clearing search by program code view
              listView.setVisibility(View.GONE);
              //showing search by Id view
              linearLayoutSearch.setVisibility(View.VISIBLE);

              // if students array list has any data, clearing for fresh use.
              if(students!=null){
                  students.clear();
                  myAdapter.notifyDataSetChanged();
              }

          }
      });

      //switching to search by program code
      radioButtonCode.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              //clearing search by Id view
            linearLayoutSearch.setVisibility(View.GONE);
              //showing search by program code view
            listView.setVisibility(View.VISIBLE);

              // if students array list has any data, clearing for fresh use.
              if(students!=null){
                  students.clear();
                  myAdapter.notifyDataSetChanged();
              }

          }
      });

      // search by Id button
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // geting data from search edit text
                String searchText = searchTIL.getEditText().getText().toString();

//validation
                if(searchText.isEmpty()){
                    Toast.makeText(getActivity(), "Please Input a text in the search Field", Toast.LENGTH_SHORT).show();
                }
                else{
                    //fetching data by Id, to student array list
                    students= databaseHelper.getIDData(searchText);
//checking null to avoid null pointer exception
                    if(students.isEmpty()){
                        Toast.makeText(getActivity(), "Data not Found", Toast.LENGTH_SHORT).show();
                    }else{
//calling MyAdapter, sending student array list
                        myAdapter = new MyAdapter(getActivity(),students);
                        recyclerView.setAdapter(myAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }

                }
            }
        });
        //showing search data by taping program code list view item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String searchText = programItem[i];
                students= databaseHelper.getCodeData(searchText);

                if(students.isEmpty()){
                    Toast.makeText(getActivity(), "Data not Found", Toast.LENGTH_SHORT).show();
                }else{
                    myAdapter = new MyAdapter(getActivity(),students);
                    recyclerView.setAdapter(myAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            }
        });

        return view;
    }
}
