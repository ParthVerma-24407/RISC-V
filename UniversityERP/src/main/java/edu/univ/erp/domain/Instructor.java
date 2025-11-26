package edu.univ.erp.domain;

public class Instructor {
    private int instructor_id;
    private String department;
    public Instructor(int instructor_id, String department){
        this.instructor_id=instructor_id;
        this.department=department;
    }
    public int getInstructor_id(){
        return instructor_id;
    }
    public void setInstructor_id(int instructor_id){
        this.instructor_id=instructor_id;
    }


    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department){
        this.department=department;
    }
}

