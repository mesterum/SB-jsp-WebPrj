package mesterum.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 */
@Entity @Table(name = "STUDENTS")
public class Student extends Del{
    /*STUDENT_ID	NUMBER(4,0)
FIRST_NAME	VARCHAR2(20 BYTE)
LAST_NAME	VARCHAR2(20 BYTE)
GENDER	CHAR(1 BYTE)
START_DATE	DATE*/
    @Id @GeneratedValue @Column(name = "STUDENT_ID")//, precision = 4)=5
    private short id;
    
    @Column(name = "FIRST_NAME", length=20)
    private String firstName;
    @Column(name = "LAST_NAME", length=20)
    private String lastName;
    private char gender;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    static private final SimpleDateFormat dtFmt=new SimpleDateFormat("yyyy-MM-dd");
    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    //@OrderBy("courseId")
    private List<Result> courses = new ArrayList<>();
    
//    public void addCourse(Course course){
//        Result result=new Result(id, course.getId());
//        courses.add(result);
//        course.getStudents().add(result);
//    }
    
    public Student(int id) {
        this.id = (short) id;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return ""+gender;
    }

    public String getStartDate() {
        if(startDate==null)return null;
        synchronized(dtFmt){
        return dtFmt.format(startDate);}
    }

    public List<Result> getCourses() {
        return courses;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.length()>20?lastName.substring(0, 20):lastName;
    }

    public void setGender(String gender) {
        this.gender = gender.toUpperCase().charAt(0);
        if("MF".indexOf(this.gender)<0)this.gender='-';
    }

    public void setStartDate(String startDate) {
        try {synchronized(dtFmt){
            this.startDate = dtFmt.parse(startDate);
        }} catch (ParseException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void display() {
        System.out.println("'"+del+"ID: "+id
                + "\tFirst name: "+firstName
                + "\tLast name: "+lastName
                + "\tGender: "+gender
                + " Start date: "+getStartDate());
    }
}
