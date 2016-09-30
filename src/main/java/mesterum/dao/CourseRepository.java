package mesterum.dao;

import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import mesterum.models.Course;

public interface CourseRepository extends JpaRepository<Course, String> {

 Stream<Course> readByDelOrderById(char c);

}
