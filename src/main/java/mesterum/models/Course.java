/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesterum.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 */
@Entity @Table(name = "COURSES")
public class Course extends Del {
    @Id @Column(name = "COURSEID", length=4)
    @Size(min=1, max=4)
    private String id;
    @Column(name = "COURSENAME", length=30)
    @Size(min=1, max=30)
    private String name;
    @OneToMany(mappedBy = "courseId", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Result> students = new ArrayList<>();//attendances 
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Result> getStudents() {
        return students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("'"+del+"ID: "+id
                + "\tName: "+name);
    }

    public Course(String id) {
        this.id = id;
    }

    public Course() {
    }
}
