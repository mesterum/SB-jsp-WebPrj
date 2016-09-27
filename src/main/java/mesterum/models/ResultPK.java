/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesterum.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 */
@MappedSuperclass
public class ResultPK implements Serializable{
    @Id //@JoinColumn(foreignKey = @ForeignKey(name = "RESULT_FKS")) 
    @Column(name = "STUDENTID", precision = 4) 
    protected short studentId;
    @Id //@JoinColumn(foreignKey = @ForeignKey(name = "RESULT_FKC"))
    @Column(name = "COURSEID", length=4)
    protected String courseId;

    public ResultPK(int studentId, String courseId) {
        this.studentId = (short) studentId;
        this.courseId = courseId;//.substring(0, 4);
    }

    public ResultPK() {
    }

    public short getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = (short) studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;//.substring(0, 4);
    }

    @Override
    public int hashCode() {
        return 29 * this.studentId + Objects.hashCode(this.courseId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ResultPK other = (ResultPK) obj;
        return this.studentId == other.studentId && Objects.equals(this.courseId, other.courseId);
        
    }
}
