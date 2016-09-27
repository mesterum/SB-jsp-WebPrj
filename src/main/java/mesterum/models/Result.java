/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesterum.models;

import javax.persistence.*;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 */
@Entity @Table(name = "RESULTS")
@IdClass( ResultPK.class )
public class Result extends ResultPK{
    private byte marks;

    public Result(int studentId, String courseId) {
        super(studentId, courseId);
    }

    Result(ResultPK id) {
        super(id.studentId, id.courseId);
    }

    public Result() {
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        if(marks<0)this.marks = 0;
        else if(marks>100)this.marks = 100;
        else this.marks = (byte) marks;
    }

    public void display() {
        System.out.println("Student ID: "+studentId
                + "\tCourse ID: '"+courseId
                + "'\tMarks: "+marks);
    }
}
