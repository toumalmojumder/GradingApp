package com.jg.gradingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jg.gradingapp.Model.Students;
import com.jg.gradingapp.R;

import java.util.ArrayList;
//recycler view adapter
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Students> students;
    public MyAdapter(Context context, ArrayList<Students> students){
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Students stu = this.students.get(position);

        //seting data in each row
        holder.stID.setText(String.valueOf(stu.getID()));
        holder.stName.setText(stu.getName());
        holder.stcode.setText(stu.getProgram_Code());
        holder.stGrade.setText(stu.getGrade());
        holder.stDuration.setText(stu.getDuration());
        holder.stFees.setText(stu.getFees());
    }

    @Override
    public int getItemCount() {
        //table row size
        return this.students.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
TextView stID,stName,stcode,stGrade,stDuration,stFees;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           //pointing place holder
            stID = itemView.findViewById(R.id.recycle_row_ID);
            stName = itemView.findViewById(R.id.recycle_row_Name);
            stcode = itemView.findViewById(R.id.recycle_row_code);
            stGrade = itemView.findViewById(R.id.recycle_row_grade);
            stDuration = itemView.findViewById(R.id.recycle_row_Duration);
            stFees = itemView.findViewById(R.id.recycle_row_Fees);


        }
    }
}
