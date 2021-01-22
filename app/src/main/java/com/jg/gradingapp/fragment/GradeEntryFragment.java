package com.jg.gradingapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jg.gradingapp.DB.DatabaseHelper;
import com.jg.gradingapp.R;

public class GradeEntryFragment extends Fragment {
    //Variable class declareation
    TextInputLayout name,grade,duration,fees;
    private Button submit;
    String gradeName =null;
    String gradeGrade= null;
    String gradeDuration= null;
    String gradeFees = null;
    String gradeProgram = "";

    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grade_entry,container,false);
        final String[] programItem = {"Python","Kotlin","Swift","Java"};

//List view to show program code
        final ListView programCodelistView = view.findViewById(R.id.Program_Code_ListView);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                programItem
        );

        programCodelistView.setAdapter(listViewAdapter);


        name = view.findViewById(R.id.grade_name_text_field);
        grade = view.findViewById(R.id.grade_grade_text_field);
        duration = view.findViewById(R.id.grade_Duration_Text_field);
        fees = view.findViewById(R.id.grade_fees_Text_field);

        submit = view.findViewById(R.id.grade_submit_button);
        databaseHelper = new DatabaseHelper(getActivity());
//list view item click listener
programCodelistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            gradeProgram= programItem[i];

    }
});
//Submit button to save data on SQlite database
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting data from edittext
                 gradeName=  name.getEditText().getText().toString();
                 gradeGrade=  grade.getEditText().getText().toString();
                 gradeDuration=  duration.getEditText().getText().toString();
                 gradeFees=  fees.getEditText().getText().toString();

                 //Validation Null checking
                 if(gradeName.isEmpty()){
                     Toast.makeText(getActivity(), "Please Input Name!", Toast.LENGTH_SHORT).show();
                 }else if(gradeProgram ==""){
                     Toast.makeText(getActivity(), "Please a tap Proagram code!", Toast.LENGTH_SHORT).show();
                 }else if(gradeGrade.isEmpty()){
                     Toast.makeText(getActivity(), "Please Input Grade!", Toast.LENGTH_SHORT).show();
                 }else if(gradeDuration.isEmpty()){
                     Toast.makeText(getActivity(), "Please Input Duration!", Toast.LENGTH_SHORT).show();
                 }else if(gradeFees.isEmpty()){
                     Toast.makeText(getActivity(), "Please Input fees!", Toast.LENGTH_SHORT).show();
                 }else{

                     //add data to sqlite database
                     Boolean addData= databaseHelper.addData(gradeName,gradeProgram,gradeGrade,gradeDuration,gradeFees);
                     if(addData){

                         Toast.makeText(getActivity(), "Successful", Toast.LENGTH_LONG).show();
                         //clearing edittext for another entry
                         name.getEditText().setText("");
                         grade.getEditText().setText("");
                         duration.getEditText().setText("");
                         fees.getEditText().setText("");

                     }
                     else{
                         Toast.makeText(getActivity(), "Something Wrong!", Toast.LENGTH_LONG).show();
                     }
                 }


            }
        });

        return view;
    }
}
