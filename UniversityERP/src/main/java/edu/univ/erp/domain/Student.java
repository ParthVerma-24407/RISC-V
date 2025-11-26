package edu.univ.erp.domain;

public class Student {
    private String roll_no;
    private String program;
    private int year;
    public Student(String roll_no, String program, int year){
        this.roll_no=roll_no;
        this.program=program;
        this.year=year;
    }
    public String getRoll_no(){
        return roll_no;
    }
    public void setRoll_no(String roll_no){
        this.roll_no=roll_no;
    }

    public String getProgram(){
        return program;
    }
    public void setProgram(String program){
        this.program=program;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year=year;
    }
}

