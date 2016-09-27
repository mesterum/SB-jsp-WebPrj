package mesterum.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import mesterum.models.Student;
//import mesterum.models.StudentPK;

public interface StudentRepository extends JpaRepository<Student, Short> {

 Stream<Student> readByDel(char c);

}
