package com.jg.gradingapp.Model;

public class Students {
    //Student Model Class
    int ID;
    String Name , Program_Code, Grade, Duration,  Fees;

    //Getter Setter
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProgram_Code() {
        return Program_Code;
    }

    public void setProgram_Code(String program_Code) {
        Program_Code = program_Code;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getFees() {
        return Fees;
    }

    public void setFees(String fees) {
        Fees = fees;
    }

    //Constructor
    public Students() {
    }

    public Students(int ID, String name, String program_Code, String grade, String duration, String fees) {
        this.ID = ID;
        Name = name;
        Program_Code = program_Code;
        Grade = grade;
        Duration = duration;
        Fees = fees;
    }
}
