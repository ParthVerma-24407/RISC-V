package edu.univ.erp.domain;


public class Section {
    private int section_id;
    private String course_id;
    private int instructor_id;
    private String day_time;
    private String room;
    private int capacity;
    private String semester;
    private int year;
    private int enrolled=0;
    public Section(int section_id, String course_id, int instructor_id, String day_time, String room, int capacity, String semester, int year){
        this.section_id=section_id;
        this.course_id=course_id;
        this.instructor_id=instructor_id;
        this.day_time=day_time;
        this.room=room;
        this.capacity=capacity;
        this.semester=semester;
        this.year=year;
    }
    public int getSection_id(){
        return section_id;
    }
    public void setSection_id(int section_id){
        this.section_id=section_id;
    }
    public String getCourse_id(){
        return course_id;
    }
    public void setCourse_id(String course_id){
        this.course_id=course_id;
    }
    public int getInstructor_id(){
        return instructor_id;
    }
    public void setInstructor_id(int instructor_id){
        this.instructor_id=instructor_id;
    }
    public String getDay_time(){
        return day_time;
    }
    public void setDay_time(String day_time){
        this.day_time=day_time;
    }
    public String getRoom(){
        return room;
    }
    public void setRoom(String room){
        this.room=room;
    }
    public int getCapacity(){
        return capacity;
    }
    public void setCapacity(int capacity){
        this.capacity=capacity;
    }
    public String getSemester(){
        return semester;
    }
    public void setSemester(String semester){
        this.semester=semester;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year=year;
    }
    public int getEnrolled(){
        return enrolled;
    }
    public void setEnrolled(int enrolled){
        this.enrolled=enrolled;
    }
    public boolean occupied(){
        if (enrolled>=capacity){
            return true;
        }else{
        return false;
    }
}}
