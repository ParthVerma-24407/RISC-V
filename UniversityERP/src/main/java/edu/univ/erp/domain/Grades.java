package edu.univ.erp.domain;

import java.math.BigDecimal;

public class Grades {
    private int enrollment_id;
    private double QuizGrade;
    private double Mid_Sem_grade;
    private double End_sem_grade;
    private String final_grade;
    public Grades(int enrollment_id,double quizGrade,double mid_Sem_grade,double end_sem_grade,String final_grade){
        this.enrollment_id=enrollment_id;
        this.QuizGrade=quizGrade;
        this.Mid_Sem_grade=mid_Sem_grade;
        this.End_sem_grade=end_sem_grade;
        this.final_grade=final_grade;
    }


    public int getEnrollment_id() {
        return enrollment_id;
    }
    public void setEnrollment_id(int enrollment_id){
        this.enrollment_id=enrollment_id;
    }
    public double getQuizGrade(){
        return QuizGrade;
    }
    public void setQuizGrade(double quizGrade){
        this.QuizGrade=quizGrade;
    }
    public double getMid_Sem_grade(){
        return Mid_Sem_grade;
    }
    public void setMid_Sem_grade(double mid_Sem_grade){
        this.Mid_Sem_grade=mid_Sem_grade;
    }

    public double getEnd_sem_grade() {
        return End_sem_grade;
    }

    public void setEnd_sem_grade(double end_sem_grade) {
        this.End_sem_grade = end_sem_grade;
    }

    public String getFinal_grade() {
        return final_grade;
    }

    public void setFinal_grade(String final_grade) {
        this.final_grade = final_grade;
    }
}
