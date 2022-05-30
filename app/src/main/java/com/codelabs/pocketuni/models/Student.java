package com.codelabs.pocketuni.models;

public class Student {
    String studentBatch, studentBatchType, studentCourse, studentEmail, studentName, studentPassword;

    public Student(String studentBatch, String studentBatchType, String studentCourse, String studentEmail, String studentName, String studentPassword) {
        this.studentBatch = studentBatch;
        this.studentBatchType = studentBatchType;
        this.studentCourse = studentCourse;
        this.studentEmail = studentEmail;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
    }

    public String getStudentBatch() {
        return studentBatch;
    }

    public void setStudentBatch(String studentBatch) {
        this.studentBatch = studentBatch;
    }

    public String getStudentBatchType() {
        return studentBatchType;
    }

    public void setStudentBatchType(String studentBatchType) {
        this.studentBatchType = studentBatchType;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }
}
